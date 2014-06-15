package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import com.GCWF.Dao.ClassDao;
import com.GCWF.Dao.CourseDao;
import com.GCWF.Model.Course;

/**
 * Created by Chuan on 6/8/14.
 */
public class AddCourseActivity extends Activity {
    private EditText courseNameText;
    private EditText courseTeacherText;
    private EditText courseTimeText;
    private Spinner timeFrom;
    private Spinner timeTo;
    private ArrayAdapter<CharSequence> adapter;

    private Button classesBtn;
    private TextView classesSelected;
    private String[] courseNames;
    private boolean [] flags;
    private String result = "";

    private Button addCourseBtn;
    private String timeFromText;
    private String timeToText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course_view);

        //设置学时只能输入数字
        EditText editText = (EditText)findViewById(R.id.time);
        editText.setInputType(EditorInfo.TYPE_CLASS_PHONE);

        initSpinner();

        ClassDao classDao = new ClassDao();
        courseNames = classDao.getAllClassName().toArray(new String[]{});
        if (courseNames == null || courseNames.length == 0) {  //如何获取的班级名称为空，则不能添加课程，需要先添加班级
            new AlertDialog.Builder(AddCourseActivity.this)
                .setTitle("提示:")
                .setMessage("还没有任何班级，请先添加班级！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AddCourseActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).show();
        } else {
            classesBtn = (Button)this.findViewById(R.id.classBtn);
            classesSelected = (TextView)this.findViewById(R.id.classesSelected);
            classesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(1);
                }
            });

            addCourseBtn = (Button)this.findViewById(R.id.addCourseBtn);
            addCourseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Course course = addCourse();
                    CourseDao courseDao = new CourseDao();
                    course = courseDao.addCourse(course);
                    if (course != null) {
                        Intent courseIntent = new Intent();
                        Bundle courseBundle = new Bundle();
                        courseBundle.putString("op", "course");
                        courseIntent.putExtras(courseBundle);
                        courseIntent.setClass(AddCourseActivity.this, CourseAvtivity.class);
                        startActivity(courseIntent);
                        finish();
                    } else {
                        Toast.makeText(null, "该课程已存在，不能添加名字相同的课程！", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    /**
     * 为课程指定班级,自定义了一个Dialog，获取所有的班级名称，创建一个复选框对话框
     * @param id
     * @return
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case 1:
                flags = new boolean[courseNames.length];
                for (int i = 0; i < courseNames.length; i++) {
                    flags[i] = false;
                }
                AlertDialog.Builder builder = new android.app.AlertDialog.Builder(AddCourseActivity.this);
                builder.setTitle("请选择该课程的班级：");
                builder.setMultiChoiceItems(courseNames, flags, new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        flags[which] = isChecked;
                        result = "";
                        for (int i = 0; i < flags.length; i++) {
                            if(flags[i]){
                                result = result + courseNames[i] + "、";
                            }
                        }
                    }
                });
                builder.setPositiveButton(" 确 定 ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        classesSelected.setText(result);
                    }
                });
                dialog = builder.create();
                break;
            }
        return dialog;
    }
/*

    private void configUI() {
        if (course != null) {
            courseNameText.setText(course.getName());
            courseTeacherText.setText(course.getTeacher());
            courseTimeText.setText(course.getCourseTime());
        }
    }
*/

    /**
     * 初始化自定义下拉框
     */
    private void initSpinner() {
        timeFrom = (Spinner)findViewById(R.id.timeFrom);
        timeTo = (Spinner)findViewById(R.id.timeTo);
        adapter = ArrayAdapter.createFromResource(this, R.array.times,
                android.R.layout.simple_spinner_dropdown_item);
        timeFrom.setAdapter(adapter);
        timeTo.setAdapter(adapter);

        timeFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                timeFromText = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        timeTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                timeToText = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * 获取添加课程的信息
     * @return
     */
    private Course addCourse() {
        courseNameText = (EditText)findViewById(R.id.name);
        courseTeacherText = (EditText)findViewById(R.id.teacher);
        courseTimeText = (EditText)findViewById(R.id.time);
        timeFrom = (Spinner)findViewById(R.id.timeFrom);
        timeTo = (Spinner)findViewById(R.id.timeTo);
        classesSelected = (TextView)findViewById(R.id.classesSelected);

        String courseName = courseNameText.getText().toString();
        String courseTeacher = courseTeacherText.getText().toString();
        String courseTime = courseTimeText.getText().toString();
        String timeToTime = "第" + timeFromText + "周"
                + "至第" + timeToText + "周";
        Course course = new Course();
        course.setName(courseName);
        course.setTeacher(courseTeacher);
        course.setCourseTime(courseTime);
        course.setTimeLength(timeToTime);
        course.setClasses(result);

        return course;
    }
}
