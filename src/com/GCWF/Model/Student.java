package com.GCWF.Model;


import java.io.Serializable;

/**
 * Created by Chuan on 6/5/14.
 */
public class Student implements Serializable {
    private String id;      //学号
    private String name;        //姓名
    private String phone;       //联系方式
    private String sex;         //性别
    private String academy;     //学院
    private String classOfAcademy;  //班级
    private String duty;            //职务

    private int leaveNum;
    private int absenceNum;
    private boolean isCome;
    private boolean isLeave; //是否请假
    private boolean isAbsence; //是否旷课
    private boolean isAllowToExam;

    public Student() {
        id = "2011051139";
        name = "郭川磊";
        academy = "计算机学院";
        classOfAcademy = "应用113班";
        isCome = true;
        isLeave = false;
        isAbsence = false;
        isAllowToExam = false;
    }
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.isCome = true;
        this.isLeave = false;
        this.isAbsence = false;
        this.isAllowToExam = true;
    }
    public Student(String id, String name, String phone, String sex,
                   String academy, String classOfAcademy, String duty) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.academy = academy;
        this.classOfAcademy = classOfAcademy;
        this.duty = duty;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public boolean isCome() {
        return isCome;
    }

    public void setCome(boolean isCome) {
        this.isCome = isCome;
        if (isCome) {
            this.isLeave = false;
            this.isAbsence = false;
        }
    }

    public boolean isLeave() {
        return isLeave;
    }

    public void setLeave(boolean isLeave) {
        this.isLeave = isLeave;
        if (isLeave) {
            this.isAbsence = false;
            this.isCome = false;
        }
    }

    public boolean isAbsence() {
        return isAbsence;
    }

    public void setAbsence(boolean isAbsence) {
        this.isAbsence = isAbsence;
        if (isAbsence) {
            this.isLeave = false;
            this.isCome = false;
        }
    }

    public boolean isAllowToExam() {
        return isAllowToExam;
    }

    public void setAllowToExam(boolean isAllowToExam) {
        this.isAllowToExam = isAllowToExam;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getClassOfAcademy() {
        return classOfAcademy;
    }

    public void setClassOfAcademy(String classOfAcademy) {
        this.classOfAcademy = classOfAcademy;
    }

    public int getLeaveNum() {
        return leaveNum;
    }

    public void setLeaveNum(int leaveNum) {
        this.leaveNum = leaveNum;
    }

    public int getAbsenceNum() {
        return absenceNum;
    }

    public void setAbsenceNum(int absenceNum) {
        this.absenceNum = absenceNum;
    }

}
