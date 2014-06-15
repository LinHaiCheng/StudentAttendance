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

    private String getMaxId() {
        String id = "0";
        String sql = "select max(id) from course";
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

    /**
     * 判断课程是否已经存在
     * @param courseName
     * @return 存在返回false,否则返回true
     */
    private boolean isTheCourseExist(String courseName) {
        Cursor cursor = db.query("classes", null, "classname = ?", new String[]{courseName}, null, null, null, null);
        if (cursor != null && cursor.getCount() == 1) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }

    public Course addCourse(Course course) {
        if (isTheCourseExist(course.getName())) {
            course.setId(this.getMaxId());
            ContentValues cv = new ContentValues();
            cv.put("id", course.getId());
            cv.put("name", course.getName());
            cv.put("courseTime", course.getCourseTime());
            cv.put("timeLength", course.getTimeLength());
            cv.put("teacher", course.getTeacher());
            cv.put("classes", course.getClasses());
            db.insert("course", null, cv);
            return course;
        } else {
            return null;
        }
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
                course.setClasses(cursor.getString(5));
                courseList.add(course);
                cursor.moveToNext();
            }
            cursor.close();
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

}
