package com.GCWF.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.GCWF.Model.Course;
import com.GCWF.StudentAttendance.R;

import java.util.List;

/**
 * Created by Administrator on 2014/6/14.
 */
public class CourseAdapter extends BaseAdapter {
    Context context;
    View[] views = null;
    List<Course> coursesInside;

    public CourseAdapter(Context context, List<Course> c) {
        this.context = context;
        if (c != null) {
            views = new View[c.size()];
            this.coursesInside = c;
        }
    }

    @Override
    public int getCount() {
        if (views != null) {
            return views.length;
        } else {
            return 0;
        }
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
            view = makeItemView(coursesInside.get(i));
        }
        return view;
    }

    private View makeItemView(Course c) {
        LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
