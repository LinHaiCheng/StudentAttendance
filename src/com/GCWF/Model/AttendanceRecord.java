package com.GCWF.Model;

import java.io.Serializable;

/**
 * Created by 程海林 on 2014/6/11.
 */
public class AttendanceRecord implements Serializable {
    private String id;
    private String time;
    private Course course;

    public AttendanceRecord() {
    }

    public AttendanceRecord(String id, String time, Course course) {
        this.id = id;
        this.time = time;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
