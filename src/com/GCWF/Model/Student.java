package com.GCWF.Model;


import java.io.Serializable;

/**
 * Created by Chuan on 6/5/14.
 */
public class Student implements Serializable {
    private String id;
    private String name;
    private String academy;
    private String classOfAcademy;
    private int leaveNum;
    private int absenceNum;
    private boolean isCome;
    private boolean isLeave; //鏄惁璇峰亣
    private boolean isAbsence; //鏄惁鏃疯
    private boolean isAllowToExam;

    public Student() {
        id = "2011051139";
        name = "郭川磊";
        academy = "计算机学院";
        classOfAcademy = "应用3班";
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
