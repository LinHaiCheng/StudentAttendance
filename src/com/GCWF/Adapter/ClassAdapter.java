package com.GCWF.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.GCWF.Model.*;
import com.GCWF.Model.Class;
import com.GCWF.StudentAttendance.R;

import java.util.List;

/**
 * Created by Administrator on 2014/6/14.
 */
public class ClassAdapter extends BaseAdapter {
    Context context;
    String op;
    View[] views = null;
    List<Class> classesInside = null;

    public ClassAdapter(Context context, List<Class> c, String op) {
        this.context = context;
        this.op = op;
        if (c != null) {
            views = new View[c.size()];
            this.classesInside = c;
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
            view = makeItemView(classesInside.get(i));
        }
        return view;
    }

    private View makeItemView(Class c) {
        LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.class_listview_item, null);

        TextView className = (TextView)itemView.findViewById(R.id.classId);
        className.setText(c.getClassName());
        TextView classTime = (TextView)itemView.findViewById(R.id.classTime);
        classTime.setText("2014-6-11");
        // 如果上一个Activity是MainActivity那么，不加载详细箭头

        if (!"classes".equals(op)) {
            ImageView detailView = (ImageView)itemView.findViewById(R.id.detailView);
            detailView.setImageResource(R.drawable.detailarrow);
        }

        TextView classBosses = (TextView)itemView.findViewById(R.id.classBosses);
        String bossesString = "班长: " + c.getMonitor() + "  学习委员: " + c.getCommissary();
        classBosses.setText(bossesString);
        TextView classNum = (TextView)itemView.findViewById(R.id.classNum);
        String shouldComeString = "应到: " + c.getTotal() + "人";
        classNum.setText(shouldComeString);
        TextView attendance = (TextView)itemView.findViewById(R.id.attendance);
        StringBuffer anntendanceString = new StringBuffer("点名情况: ");

            /*
            if (c.getAbsenceStuNum() == 0) {
                anntendanceString = anntendanceString.append("全勤");
            } else if (c.getLeaveStuNum() == 0) {
                int num = c.getStuNum() - c.getAbsenceStuNum() - c.getLeaveStuNum();
                anntendanceString = anntendanceString.append("实到" + num + "人, 旷课" +
                        c.getAbsenceStuNum() + "人");
            } else {
                int num = c.getStuNum() - c.getAbsenceStuNum() - c.getLeaveStuNum();
                anntendanceString = anntendanceString.append("实到" + num + "人, 请假" + c.getLeaveStuNum() + "人, 旷课"
                        + c.getAbsenceStuNum() + "人");
            }
            */
        attendance.setText(anntendanceString.append("实到32人， 请假4人，旷课4人"));
        return itemView;
    }
}
