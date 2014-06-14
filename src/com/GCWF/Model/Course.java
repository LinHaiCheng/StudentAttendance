package com.GCWF.Model;

import java.io.Serializable;

/**
 * Created by 程海林 on 6/8/14.
 */
public class Course implements Serializable {
    private String id;
    private String name;
    private String courseTime;      //课时
    private String timeLength;      //课长（XX周--XX周）
    private String teacher;
    private String classes;

    public Course() {
        id = "1";
        name = "移动终端开发";
        courseTime = "32课时";
        timeLength = "从：8周 到：10周";
        teacher = "张元茂";
    }

    public Course(String name, String id, String courseTime, String timeLength, String teacher, String classes) {
        this.name = name;
        this.id = id;
        this.courseTime = courseTime;
        this.timeLength = timeLength;
        this.teacher = teacher;
        this.classes = classes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(String timeLength) {
        this.timeLength = timeLength;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }
}
