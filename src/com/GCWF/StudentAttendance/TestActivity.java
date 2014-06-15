package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.os.Bundle;
import com.GCWF.Dao.RecordDao;

/**
 * Created by Administrator on 2014/6/15.
 */
public class TestActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        RecordDao recordDao = new RecordDao();
        recordDao.getDetails();
    }
}