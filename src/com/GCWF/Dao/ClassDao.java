package com.GCWF.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.GCWF.Utils.SQLiteDB;
import com.GCWF.Model.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 程海林 on 2014/6/11.
 */
public class ClassDao {
    private SQLiteDatabase db;
    public ClassDao() {
        db = SQLiteDB.getDb();
    }

    private String getMaxId() {
        String id = "0";
        String sql = "select max(id) from classes";
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

    private boolean isTheClassExist(String className) {
        String sql = "select * from classes where classname="+className;
       // Cursor cursor = db.rawQuery(sql, null);
        Cursor cursor = db.query("classes", null, "classname = ?", new String[]{className}, null, null, null, null);
        if (cursor != null && cursor.getCount() == 1) {
            return false;
        } else {
            return true;
        }
    }

    public Class addClass(Class classes) {
        if (this.isTheClassExist(classes.getClassName())) {
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
        } else {
            return null;
        }
    }

    public List<Class> getAllClasses() {
        if (db != null) {
            List<Class> classList = new ArrayList<Class>();
            String sql = "select * from classes order by id asc";
            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Class aClass = new Class();
                aClass.setId(cursor.getString(0));
                aClass.setClassName(cursor.getString(1));
                aClass.setAcademy(cursor.getString(2));
                aClass.setMonitor(cursor.getString(3));
                aClass.setCommissary(cursor.getString(4));
                aClass.setTotal(cursor.getInt(5));
                classList.add(aClass);
                cursor.moveToNext();
            }
            return classList;
        } else {
            return null;
        }
    }

    public List<String> getAllClassName() {
        if (db != null) {
            List<String> nameList = new ArrayList<String>();
            String sql = "select classname from classes";
            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                nameList.add(cursor.getString(0));
                cursor.moveToNext();
            }
            return nameList;
        } else {
            return null;
        }
    }
}
