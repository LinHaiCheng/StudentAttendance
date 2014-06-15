package com.GCWF.StudentAttendance;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.GCWF.Dao.RecordDao;
import com.GCWF.Dao.StudentDao;
import com.GCWF.Model.*;
import com.GCWF.Model.Class;

import java.util.ArrayList;
import java.util.List;

public class RollCallActivity extends Activity {
    private ViewPager viewPager;
    private PagerTitleStrip pagerTitleStrip;
    private LayoutInflater inflater;

    private MyAdapter adapter;

    private TextView stuInfo;
    private ImageView head;

    private List<View> list;
/*

    private TextView prefixStudent;
    private TextView currentStudent;
    private TextView nextStudent;
*/

    private Button comeBtn;
    private Button leaveBtn; //请假
    private Button lateBtn;
    private Button absenceBtn;

    private List<Student> students;
    private Student lastStudent;
    private int index = 0;

    private List<AttendanceDetail> details;
    private AttendanceDetail detail;

    private Course course;
    private String courseClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = this.getIntent().getExtras();
        course = (Course)bundle.getSerializable("course");
        Class aClass = (Class)bundle.getSerializable("aClass");
        courseClasses = bundle.getString("courseClasses");
        StudentDao studentDao = new StudentDao();
        students = studentDao.getStudentsByClass(aClass);
        lastStudent = students.get(students.size() - 1);
        details = new ArrayList<AttendanceDetail>();

        viewPager = (ViewPager) this.findViewById(R.id.viewPager);
        pagerTitleStrip = (PagerTitleStrip)this.findViewById(R.id.title);

        inflater = LayoutInflater.from(RollCallActivity.this);
        View view = inflater.inflate(R.layout.rollcall, null);
        initPage(view, index);
        list = new ArrayList<View>();
        list.add(view);

        adapter = new MyAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int arg0) {  }
            public void onPageScrolled(int arg0, float arg1, int arg2) { }
            public void onPageScrollStateChanged(int arg0) {
                if (index < students.size()) {
                    View view = inflater.inflate(R.layout.rollcall, null);
                    index++;
                    initPage(view, index);
                    list.add(view);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0==arg1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position < students.size()) {
                return students.get(position).getName();
            } else {
                return null;
            }
        }
        @Override
        public Object instantiateItem(View container, int position) {
            // TODO Auto-generated method stub
        System.out.println(position);
            ((ViewPager)container).addView(list.get(position));
            return list.get(position);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(list.get(position));
        }
    }

    private void initPage(View view, final int index) {
     /*   prefixStudent = (TextView)view.findViewById(R.id.prefix);
        currentStudent = (TextView)view.findViewById(R.id.current);
        nextStudent = (TextView)view.findViewById(R.id.next);
*/
        // Buttons
        comeBtn = (Button)view.findViewById(R.id.come);
        leaveBtn = (Button)view.findViewById(R.id.leave);
        lateBtn = (Button)view.findViewById(R.id.late);
        absenceBtn = (Button)view.findViewById(R.id.absence);

        String result = "-1";
        stuInfo = (TextView)view.findViewById(R.id.stuinfo);
        if (index < students.size()) {
           // prefixStudent.setText(students.get(index - 1).getName());
           // currentStudent.setText(students.get(index).getName());
          //  nextStudent.setText(students.get(index + 1).getName());
            String stuInfos = "学号: " + students.get(index).getId() + "\n"
                    + students.get(index).getaClass().getAcademy()
                    + students.get(index).getaClass().getClassName() + "\n"
                    + "请假次数: " + "0" + "\n"
                    + "旷课次数: " + "0";
            stuInfo.setText(stuInfos);
            head = (ImageView)view.findViewById(R.id.stuhead);
            head.setImageResource(R.drawable.no_head);
            comeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RollCallActivity.this, students.get(index).getName() + ":  到", Toast.LENGTH_SHORT).show();
                    addAttendance(students.get(index), "1", index);
                }
            });
            leaveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RollCallActivity.this, students.get(index).getName() + ":请假", Toast.LENGTH_SHORT).show();
                    addAttendance(students.get(index), "2", index);
                }
            });
            lateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RollCallActivity.this, students.get(index).getName() + ":迟到", Toast.LENGTH_SHORT).show();
                    addAttendance(students.get(index), "3", index);
                }
            });
            absenceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RollCallActivity.this, students.get(index).getName() + ": 旷课", Toast.LENGTH_SHORT).show();
                    addAttendance(students.get(index), "4", index);
                }
            });
        }
    }

    private void addAttendance(Student aStudent, String result, int i) {
        detail = new AttendanceDetail();
        detail.setStudent(aStudent);
        detail.setResult(result);
        details.add(detail);
        if (students.get(i).getId().equals(lastStudent.getId())) {
            RecordDao recordDao = new RecordDao();
            AttendanceRecord record = recordDao.addRecord(course);
            if (recordDao.addRecordDetail(details, record)) {
                //添加所有记录
                new AlertDialog.Builder(RollCallActivity.this)
                        .setTitle("完成点名")
                        .setMessage("已保存考勤记录")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent classIntent = new Intent();
                                Bundle classBundle = new Bundle();
                                classBundle.putString("op", "rollcall");
                                classBundle.putSerializable("course", course);
                                classBundle.putString("courseClasses", courseClasses);
                                classIntent.putExtras(classBundle);
                                classIntent.setClass(RollCallActivity.this, ClassesActivity.class);
                                startActivity(classIntent);
                                finish();
                            }
                        }).show();
            }
        }
    }

}
