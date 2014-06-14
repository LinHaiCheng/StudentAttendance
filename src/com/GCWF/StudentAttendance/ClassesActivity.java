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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chuan on 6/5/14.
 */
public class ClassesActivity extends Activity {

    private ListView listView;
    private List<Class> classList;
    private String op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_listview);

        ClassDao classDao = new ClassDao();
        classList = classDao.getAllClasses();

        Bundle bundle = this.getIntent().getExtras();
        op = bundle.getString("op");

        // 判断上一个Activity
       // fromWhere = (String)getIntent().getSerializableExtra("formWhere");
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new ClassAdapter(this, classList, op));
        if (!"classes".equals(op)) {
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
                Intent intent = new Intent(ClassesActivity.this, FileActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private AdapterView.OnItemClickListener itemSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(ClassesActivity.this, RollCallActivity.class);
            startActivity(intent);
            finish();
        }
    };


}
