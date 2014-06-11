package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.GCWF.Model.Course;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course_view);

        course = (Course)getIntent().getSerializableExtra("courseInfo");
        courseNameText = (EditText)findViewById(R.id.courseName);
        courseTeacherText = (EditText)findViewById(R.id.teacherName);
        courseTimeText = (EditText)findViewById(R.id.courseTime);
        timeFrom = (Spinner)findViewById(R.id.timeFrom);
        timeTo = (Spinner)findViewById(R.id.timeTo);
        spinner_set();

        configUI();
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
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
