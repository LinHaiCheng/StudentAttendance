package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.GCWF.Adapter.CourseAdapter;
import com.GCWF.Dao.CourseDao;
import com.GCWF.Model.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chuan on 6/9/14.
 */
public class ShowCourseActivity extends Activity {
    private ListView listView = null;
    private List<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_listview);

        Bundle bundle = this.getIntent().getExtras();
        String op = bundle.getString("op");

        CourseDao courseDao = new CourseDao();
        courseList = courseDao.getAllCourse();

        if (courseList != null) {
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(new CourseAdapter(this, courseList));

            if ("rollcall".equals(op)) {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ShowCourseActivity.this, ClassesActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("op", "rollcall");
                        bundle.putSerializable("course", courseList.get(position));
                        bundle.putString("courseClasses", courseList.get(position).getClasses());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                });
            } else {
                //导出考勤表
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
            }
        } else {
            Toast.makeText(null, "还没有任何班级记录！", Toast.LENGTH_LONG).show();
        }
    }

}
