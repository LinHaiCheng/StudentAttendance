package com.GCWF.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.GCWF.Model.AttendanceDetail;
import com.GCWF.Model.AttendanceRecord;
import com.GCWF.Model.Course;
import com.GCWF.Utils.SQLiteDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Chuan on 6/15/14.
 */
public class RecordDao {
    private SQLiteDatabase db;

    public RecordDao() {
        db = SQLiteDB.getDb();
    }

    private String getMaxId() {
        String id = "0";
        String sql = "select max(id) from record";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToNext();
        if (cursor != null && cursor.getCount() == 1) {
            id = cursor.getString(0);
        }
        if (id == null || "null".equals(id)) {
            id = "0";
        }
        int maxId = Integer.parseInt(id) + 1;
        cursor.close();
        return maxId + "";
    }

    public AttendanceRecord addRecord(Course course) {
        AttendanceRecord record = new AttendanceRecord();
        record.setId(this.getMaxId());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime = sdf.format(new Date());
        record.setTime(currentTime);
        record.setCourse(course);

        ContentValues cv = new ContentValues();
        cv.put("id", record.getId());
        cv.put("time", record.getTime());
        cv.put("courseid", record.getCourse().getId());
        db.insert("record", null, cv);

        return record;
    }

    public boolean addRecordDetail(List<AttendanceDetail> detailList, AttendanceRecord record) {
        boolean flag = true;
        try {
            for (AttendanceDetail detail : detailList) {
                ContentValues cv = new ContentValues();
                cv.put("studentid", detail.getStudent().getId());
                cv.put("recordid", record.getId());
                cv.put("result", detail.getResult());
                db.insert("detail", null, cv);
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public List<AttendanceDetail> getDetails() {
        List<AttendanceDetail> details = new ArrayList<AttendanceDetail>();
        String sql = "select * from detail";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String studentId = cursor.getString(0);
            String courseId = cursor.getString(1);
            String result = cursor.getString(2);

            System.out.println(studentId + ":" + courseId + ":" + result);

            cursor.moveToNext();
        }
        return null;
    }
}
