package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.GCWF.Adapter.ClassAdapter;
import com.GCWF.Dao.ClassDao;
import com.GCWF.Model.Class;
import com.GCWF.Model.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chuan on 6/5/14.
 */
public class ClassesActivity extends Activity {

    private ListView listView;
    private List<Class> classList;
    private String op;
    private Course course;
    private String courseClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_listview);

        ClassDao classDao = new ClassDao();


        Bundle bundle = this.getIntent().getExtras();
        op = bundle.getString("op");

        listView = (ListView)findViewById(R.id.listView);
        if ("classes".equals(op)) {
            classList = classDao.getAllClasses();
            listView.setAdapter(new ClassAdapter(this, classList, op));
        }else if ("rollcall".equals(op)) {
            course = (Course)bundle.getSerializable("course");
            courseClasses = bundle.getString("courseClasses");
            String [] classes = courseClasses.split("、");
            classList = classDao.getClassesByName(classes);
            listView.setAdapter(new ClassAdapter(this, classList, op));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ClassesActivity.this, RollCallActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putString("courseClasses", courseClasses);
                    mBundle.putSerializable("aClass", classList.get(position));
                    mBundle.putSerializable("course", course);
                    intent.putExtras(mBundle);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if ("classes".equals(op)) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_activity_actions, menu);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ("classes".equals(op)) {
            int item_id = item.getItemId();
            switch (item_id) {
                case R.id.add:
                    Intent intent = new Intent(ClassesActivity.this, FileActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
