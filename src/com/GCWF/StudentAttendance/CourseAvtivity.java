package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.GCWF.Adapter.CourseAdapter;
import com.GCWF.Dao.CourseDao;
import com.GCWF.Model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chuan on 6/8/14.
 */
public class CourseAvtivity extends Activity {

    private ListView listView = null;
    private List<Course> courseList;
    private String op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_listview);
        Bundle bundle = this.getIntent().getExtras();
        op = bundle.getString("op");

        CourseDao courseDao = new CourseDao();
        courseList = courseDao.getAllCourse();

        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new CourseAdapter(this, courseList));
        if (!"course".equals(op)) {
            listView.setOnItemClickListener(itemSelectedListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        switch (item_id) {
            case R.id.add:
                Intent intent = new Intent(CourseAvtivity.this, AddCourseOrDetailActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private AdapterView.OnItemClickListener itemSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(CourseAvtivity.this, AddCourseOrDetailActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("courseInfo", courseList.get(i));
            intent.putExtras(mBundle);
            startActivity(intent);
        }
    };


}
