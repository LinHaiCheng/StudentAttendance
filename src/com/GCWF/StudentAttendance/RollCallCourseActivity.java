package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.GCWF.Adapter.CourseAdapter;
import com.GCWF.Model.Course;

import java.util.ArrayList;

/**
 * Created by Chuan on 6/9/14.
 */
public class RollCallCourseActivity extends Activity {
    private ListView listView = null;
    private ArrayList<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_listview);

        courses = new ArrayList<Course>();
        for (int i = 0; i < 10; i ++) {
            Course c = new Course();
            courses.add(c);
        }
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new CourseAdapter(this, courses));
        listView.setOnItemClickListener(itemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return true;
    }

    private AdapterView.OnItemClickListener itemSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(RollCallCourseActivity.this, ClassesActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("courseInfo", courses.get(i));
            intent.putExtras(mBundle);
            startActivity(intent);
        }
    };

}
