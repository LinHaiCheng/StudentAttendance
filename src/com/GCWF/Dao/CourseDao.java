package com.GCWF.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.GCWF.Model.*;
import com.GCWF.Model.Class;
import com.GCWF.Utils.SQLiteDB;

import java.util.ArrayList;
import java.util.List;

public class CourseDao {
	private SQLiteDatabase db = SQLiteDB.getDb();
	
	public Course addCourse(Course course) {
		course.setId(this.generateID());
        ContentValues cv = new ContentValues();
        cv.put("id", course.getId());
        cv.put("name", course.getName());
        cv.put("courseTime", course.getCourseTime());
        cv.put("timeLength", course.getTimeLength());
        cv.put("teacher", course.getTeacher());
        db.insert("course", null, cv);
        
        return course;
    }

    public List<Course> getAllCourse() {
        if (db != null) {
            java.util.List<Course> courseList = new ArrayList<Course>();
            String sql = "select * from course order by id asc";
            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Course course = new Course();
                course.setId(cursor.getString(0));
                course.setName(cursor.getString(1));
                course.setCourseTime(cursor.getString(2));
                course.setTimeLength(cursor.getString(3));
                course.setTeacher(cursor.getString(4));
                courseList.add(course);
                cursor.moveToNext();
            }
            return courseList;
        } else {
            return null;
        }
    }
	
	public Course getCourse (String courseid) {
    	String sql = "select * from course  where id ='" + courseid + "')";
    	Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst(); 
        Course course = new Course();
        course.setId(cursor.getString(0));
        course.setName(cursor.getString(1));
        course.setCourseTime(cursor.getString(2));
        course.setTimeLength(cursor.getString(3));
        course.setTeacher(cursor.getString(4));
        
        return course;
    }
	
	public String generateID ()  {
        String id;
        String sql = "select max(id) from course";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToNext();
        if (cursor != null && cursor.getCount() == 1) {
            id = cursor.getString(0);
        } else {
            id = 0 + "";
        }
        cursor.close();
        int newId = Integer.parseInt(id) + 1;
        
        return "" + newId;
    }
}
