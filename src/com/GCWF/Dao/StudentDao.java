package com.GCWF.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.GCWF.Model.*;
import com.GCWF.Model.Class;
import com.GCWF.Utils.SQLiteDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 程海林 on 2014/6/11.
 */
public class StudentDao {
    private SQLiteDatabase db;
    public StudentDao() {
        db = SQLiteDB.getDb();
    }

    public void addStudents(List<Student> students) {
        for (Student student : students) {
            ContentValues cv = new ContentValues();
            cv.put("id", student.getId());
            cv.put("name", student.getName());
            cv.put("sex", student.getSex());
            cv.put("phone", student.getPhone());
            cv.put("duty", student.getDuty());
            cv.put("classid", student.getaClass().getId());
            db.insert("student", null, cv);
        }
    }

    public List<Student> getStudentsByClass(Class aClass) {
        List<Student> studentList = new ArrayList<Student>();
        Cursor cursor = db.query("student", null, "classid = ?", new String []{aClass.getId()}, null, null, "id", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = new Student();
            student.setId(cursor.getString(0));
            student.setName(cursor.getString(1));
            student.setSex(cursor.getString(2));
            student.setPhone(cursor.getString(3));
            student.setDuty(cursor.getString(4));
            student.setaClass(aClass);
            studentList.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return studentList;
    }
}
