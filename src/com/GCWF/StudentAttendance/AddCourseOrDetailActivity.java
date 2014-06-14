package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.GCWF.Dao.ClassDao;
import com.GCWF.Model.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chuan on 6/8/14.
 */
public class AddCourseOrDetailActivity extends Activity {
    private EditText courseNameText;
    private EditText courseTeacherText;
    private EditText courseTimeText;
    private Course course;
    private Spinner timeFrom;
    private Spinner timeTo;
    private ArrayAdapter<CharSequence> adapter;

    private Button classesBtn;
    private TextView classesSelected;
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course_view);
        Dialog dialog=null;
        classesBtn = (Button)this.findViewById(R.id.classBtn);
        classesSelected = (TextView)this.findViewById(R.id.classesSelected);
        classesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });

        course = (Course)getIntent().getSerializableExtra("courseInfo");
        courseNameText = (EditText)findViewById(R.id.courseName);
        courseTeacherText = (EditText)findViewById(R.id.teacherName);
        courseTimeText = (EditText)findViewById(R.id.courseTime);
        timeFrom = (Spinner)findViewById(R.id.timeFrom);
        timeTo = (Spinner)findViewById(R.id.timeTo);
        spinner_set();

        configUI();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case 1:
                ClassDao classDao = new ClassDao();
                final String[] names = classDao.getAllClassName().toArray(new String[]{});
                final boolean [] flags = new boolean[names.length];
                for (int i = 0; i < names.length; i++) {
                    flags[i] = false;
                }
                classesSelected.setText("");
                AlertDialog.Builder builder = new android.app.AlertDialog.Builder(AddCourseOrDetailActivity.this);
                //设置对话框的标题
                builder.setTitle("请选择该课程的班级：");
                builder.setMultiChoiceItems(names, flags, new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                      flags[which] = isChecked;
                        for (int i = 0; i < flags.length; i++) {
                            if(flags[i]){
                                result = result + names[i] + "、";
                            }
                        }
                    }
                });
                builder.setPositiveButton(" 确 定 ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        classesSelected.setText(result);
                    }
                });
                //创建一个复选框对话框
                dialog = builder.create();
                break;
            }
        return dialog;
    }

    private void configUI() {
        if (course != null) {
            courseNameText.setText(course.getName());
            courseTeacherText.setText(course.getTeacher());
            courseTimeText.setText(course.getCourseTime());
        }
    }

    private void spinner_set() {
        adapter = ArrayAdapter.createFromResource(this, R.array.times,
                android.R.layout.simple_spinner_dropdown_item);
        timeFrom.setAdapter(adapter);
        timeTo.setAdapter(adapter);

        timeFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }
}
