package com.GCWF.Model;

import java.io.Serializable;

/**
 * Created by Chuan on 6/8/14.
 */
public class Course implements Serializable {
    private String name;
    private String teacher;
    private String courseTime;

    public Course() {
        this.name = "Java";
        this.teacher = "张元茂";
        this.courseTime = "64";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }
}
