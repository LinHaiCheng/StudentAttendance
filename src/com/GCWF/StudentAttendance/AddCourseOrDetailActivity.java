package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import com.GCWF.Model.Course;

/**
 * Created by Chuan on 6/8/14.
 */
public class AddCourseOrDetailActivity extends Activity {
    private EditText courseNameText;
    private EditText courseTeacherText;
    private EditText courseTimeText;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course_view);

        course = (Course)getIntent().getSerializableExtra("courseInfo");
        courseNameText = (EditText)findViewById(R.id.courseName);
        courseTeacherText = (EditText)findViewById(R.id.teacherName);
        courseTimeText = (EditText)findViewById(R.id.courseTime);

        configUI();
    }

    private void configUI() {
        courseNameText.setText(course.getName());
        courseTeacherText.setText(course.getTeacher());
        courseTimeText.setText(course.getCourseTime());
    }
}
