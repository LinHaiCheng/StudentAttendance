package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.GCWF.Model.Class;

import java.util.ArrayList;

/**
 * Created by Chuan on 6/5/14.
 */
public class MainActivity extends Activity {

    private ListView listView;
    private ArrayList<Class> classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        classes = new ArrayList<Class>();
        for (int i = 0; i < 10; i ++) {
            Class c = new Class();
            classes.add(c);
        }
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new ClassAdapter(classes));
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
            case R.id.addClass:
                Intent intent = new Intent(MainActivity.this, FileActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private AdapterView.OnItemClickListener itemSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(MainActivity.this, RollCallActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("classInfo", classes.get(i));
            intent.putExtras(mBundle);
            startActivity(intent);
        }
    };

    public class ClassAdapter extends BaseAdapter {

        View[] views = null;
        ArrayList<Class> classesInside;

        public ClassAdapter(ArrayList<Class> c) {
            views = new View[c.size()];

            this.classesInside = c;
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
                view = makeItemView(classes.get(i));
            }
            return view;
        }

        private View makeItemView(Class c) {
            LayoutInflater inflater = (LayoutInflater)MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View itemView = inflater.inflate(R.layout.class_listview_item, null);

            TextView classId = (TextView)itemView.findViewById(R.id.classId);
            classId.setText(c.getId());
            TextView classTime = (TextView)itemView.findViewById(R.id.classTime);
            classTime.setText(c.getClassTime());
            ImageView detailView = (ImageView)itemView.findViewById(R.id.detailView);
            detailView.setImageResource(R.drawable.detailarrow);
            TextView classBosses = (TextView)itemView.findViewById(R.id.classBosses);
            String bossesString = "班长: " + c.getClassMonitor().getName() + "  学习委员: " + c.getStudyMonitor().getName();
            classBosses.setText(bossesString);
            TextView classNum = (TextView)itemView.findViewById(R.id.classNum);
            String shouldComeString = "应到: " + c.getStuNum() + "人";
            classNum.setText(shouldComeString);
            TextView attendance = (TextView)itemView.findViewById(R.id.attendance);
            StringBuffer anntendanceString = new StringBuffer("点名情况: ");
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
            attendance.setText(anntendanceString);
            return itemView;
        }
    }
}
