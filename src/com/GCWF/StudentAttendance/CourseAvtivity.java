package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.GCWF.Model.*;

import java.util.ArrayList;

/**
 * Created by Chuan on 6/8/14.
 */
public class CourseAvtivity extends Activity {

    private ListView listView = null;
    private ArrayList<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        courses = new ArrayList<Course>();
        for (int i = 0; i < 10; i ++) {
            Course c = new Course();
            courses.add(c);
        }
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new CourseAdapter(courses));
        listView.setOnItemClickListener(itemSelectedListener);
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
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private AdapterView.OnItemClickListener itemSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(CourseAvtivity.this, AddCourseOrDetailActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("courseInfo", courses.get(i));
            intent.putExtras(mBundle);
            startActivity(intent);
        }
    };

    public class CourseAdapter extends BaseAdapter {

        View[] views = null;
        ArrayList<Course> coursesInside;

        public CourseAdapter(ArrayList<Course> c) {
            views = new View[c.size()];

            this.coursesInside = c;
        }

        @Override
        public int getCount() {
            return views.length;
        }

        @Override
        public View getItem(int i) {
            return views[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = makeItemView(courses.get(i));
            }
            return view;
        }

        private View makeItemView(Course c) {
            LayoutInflater inflater = (LayoutInflater)CourseAvtivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View itemView = inflater.inflate(R.layout.course_listview_item, null);

            TextView courseName = (TextView)itemView.findViewById(R.id.courseName);
            courseName.setText(c.getName());
            ImageView detailView = (ImageView)itemView.findViewById(R.id.detailView);
            detailView.setImageResource(R.drawable.detailarrow);
            TextView courseTeacher = (TextView)itemView.findViewById(R.id.courseTeacher);
            String bossesString = "代课教师: " + c.getTeacher();
            courseTeacher.setText(bossesString);
            TextView courseTime = (TextView)itemView.findViewById(R.id.courseTime);
            String timeString = "学时: " + c.getCourseTime();
            courseTime.setText(timeString);

            return itemView;
        }
    }
}
