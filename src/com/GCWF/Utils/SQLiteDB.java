package com.GCWF.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by 程海林 on 2014/6/11.
 */
public class SQLiteDB extends SQLiteOpenHelper {
	private static final String DBNAME = "attendance";
    private static SQLiteDatabase db = null;
	/*
	 * 构造方法，创建数据库
	 * context 
	 * name	     数据库名
	 * factory  游标类
	 * version   数据库版本
	 */
	public SQLiteDB(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	public SQLiteDB(Context context, String name, int version) {
		this(context, name, null, version);
	}
	public SQLiteDB(Context context, String name) {
		this(context, name, 1);
        db = this.getWritableDatabase();
	}

    public static SQLiteDatabase getDb () {
        return db;
    }

	@Override
	public void onCreate(SQLiteDatabase db) {

        initClassTable(db);     //创建班级表

        initStudentTable(db);   //创建学生表

        initCourseTable(db);    //创建课程表

        initAttendanceRecordTable(db);  //创建考勤记录表

        initAttendanceDetailTable(db);  //创建考勤详细表
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

    private void initClassTable(SQLiteDatabase db) {
        String sql = "create table if not exists classes(" +
                "id varchar(8) primary key," +
                "classname varchar(20) UNIQUE," +
                "academy varchar(20)," +
                "monitor varchar(20)," +
                "commissary varchar(20)," +
                "total int)";
        db.execSQL(sql);
    }
    private void initStudentTable(SQLiteDatabase db) {
        String sql = "create table if not exists student(" +
                "id varchar(11) primary key," +
                "name varchar(20)," +
                "sex varchar(4)," +
                "phone varchar(11)," +
                "duty varchar(20)," +
                "classid varchar(8)," +
                "FOREIGN KEY (classid) REFERENCES classes(id))";
        db.execSQL(sql);
    }
    private void initCourseTable(SQLiteDatabase db) {
        String sql = "create table if not exists course(" +
                "id varchar(8) primary key," +
                "name varchar(20) UNIQUE," +
                "courseTime int," +
                "timeLength varchar(16)," +
                "teacher varchar(20)," +
                "classes varchar(64))";
        db.execSQL(sql);
    }
    private void initAttendanceRecordTable(SQLiteDatabase db) {
        String sql = "create table if not exists record(" +
                "id varchar(8) primary key," +
                "time varchar(32)," +
                "courseid varchar(8)," +
                "foreign key (courseid) references course(id))";
        db.execSQL(sql);
    }
    private void initAttendanceDetailTable(SQLiteDatabase db) {
        String sql = "create table if not exists detail(" +
                "studentid varchar(11)," +
                "recordid varchar(8)," +
                "result varchar(1)," +
              //  "primary key (studentid, recordid)," +
                "foreign key (studentid) references student(id)," +
                "foreign key (recordid) references record(id))";
        db.execSQL(sql);
    }
}
