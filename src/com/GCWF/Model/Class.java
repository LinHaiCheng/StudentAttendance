package com.GCWF.Model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Chuan on 6/5/14.
 */
public class Class implements Serializable {
    private String id;
    private String classTime;
    private Student classMonitor;
    private Student studyMonitor;
    private int stuNum;
    private int comeStuNum;
    private int leaveStuNum; //请假
    private int absenceStuNum; //旷课
    private LinkedList<Student> students;

    public Class() {
        id = "应用三班";
        classMonitor = new Student("2011051151", "程海林");
        studyMonitor = new Student("2011051136", "吴云鹏");
        stuNum = 40;
        leaveStuNum = 0;
        absenceStuNum = 0;
        Date date = new Date();
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        classTime = dft.format(date);

        // fake data
        students = new LinkedList<Student>();
        for (int i = 0; i <= stuNum; i ++) {
            Student student = new Student();
            students.add(student);

        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getClassMonitor() {
        return classMonitor;
    }

    public void setClassMonitor(Student classMonitor) {
        this.classMonitor = classMonitor;
    }

    public Student getStudyMonitor() {
        return studyMonitor;
    }

    public void setStudyMonitor(Student lifeMonitor) {
        this.studyMonitor = lifeMonitor;
    }

    public int getStuNum() {
        return stuNum;
    }

    public void setStuNum(int stuNum) {
        this.stuNum = stuNum;
    }

    public int getComeStuNum() {
        return comeStuNum;
    }

    public void setComeStuNum(int comeStuNum) {
        this.comeStuNum = comeStuNum;
    }

    public int getLeaveStuNum() {
        return leaveStuNum;
    }

    public void setLeaveStuNum(int leaveStuNum) {
        this.leaveStuNum = leaveStuNum;
    }

    public int getAbsenceStuNum() {
        return absenceStuNum;
    }

    public void setAbsenceStuNum(int absenceStuNum) {
        this.absenceStuNum = absenceStuNum;
    }


    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(Date classTime) {
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        this.classTime = dft.format(classTime);
    }

    public void setStudents(LinkedList<Student> students) {
        this.students = students;
    }

    public LinkedList<Student> getStudents() {
        return students;
    }

}