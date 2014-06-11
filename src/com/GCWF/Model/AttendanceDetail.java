package com.GCWF.Model;

import java.io.Serializable;

/**
 * Created by 程海林 on 2014/6/11.
 */
public class AttendanceDetail implements Serializable {
    private Student student;            //学生
    private AttendanceRecord record;    //考勤记录，即属于那一次考勤
    private String result;              //考勤结果

    public AttendanceDetail() {
    }

    public AttendanceDetail(Student student, AttendanceRecord record, String result) {
        this.student = student;
        this.record = record;
        this.result = result;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AttendanceRecord getRecord() {
        return record;
    }

    public void setRecord(AttendanceRecord record) {
        this.record = record;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
