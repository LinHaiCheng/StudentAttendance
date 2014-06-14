package com.GCWF.Dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.GCWF.Model.Student;
import com.GCWF.Utils.SQLiteDB;

import java.util.List;

/**
 * Created by 程海林 on 2014/6/11.
 */
public class StudentDao {
    private SQLiteDatabase db = SQLiteDB.getDb();

    public void addStudents(List<Student> students) {
        for (Student student : students) {
        System.out.println(student.getName());
            ContentValues cv = new ContentValues();
            cv.put("id", student.getId());
            cv.put("name", student.getName());
        System.out.println(student.getName());
            cv.put("sex", student.getSex());
            cv.put("phone", student.getPhone());
            cv.put("duty", student.getDuty());
            cv.put("classid", student.getaClass().getId());
            db.insert("student", null, cv);
        }
    }
}
