package com.GCWF.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.GCWF.Utils.SQLiteDB;
import com.GCWF.Model.Class;

/**
 * Created by 程海林 on 2014/6/11.
 */
public class ClassDao {
    private SQLiteDatabase db = SQLiteDB.getDb();

    private String getMaxId() {
        String id;
        String sql = "select max(id) from classes";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToNext();
        if (cursor != null && cursor.getCount() == 1) {
            id = cursor.getString(0);
        } else {
            id = 0 + "";
        }
        cursor.close();
        int maxId = Integer.parseInt(id) + 1;
        return maxId + "";
    }

    public Class addClass(Class classes) {
        classes.setId(this.getMaxId());
        ContentValues cv = new ContentValues();
        cv.put("id", classes.getId());
        cv.put("classname", classes.getClassName());
        cv.put("academy", classes.getAcademy());
        cv.put("monitor", classes.getMonitor());
        cv.put("commissary", classes.getCommissary());
        cv.put("total", classes.getTotal());
        db.insert("classes", null, cv);
        return classes;
    }
}
