package com.GCWF.Model;

import java.io.Serializable;


/**
 * Created by 程海林 on 6/5/14.
 */
public class Class implements Serializable {
    private String id;
    private String className;
    private String academy;         //学院
    private String monitor;         //班长
    private String commissary;      //学习委员
    private int total;              //总人数

    public Class() {
        id = "1";
        className = "应用三班";
        academy = "计算机学院";
        monitor = "程海林";
        commissary = "吴云鹏";
        total = 40;
    }

    public Class(String id, String className, String academy, String monitor, int total, String commissary) {
        this.id = id;
        this.className = className;
        this.academy = academy;
        this.monitor = monitor;
        this.total = total;
        this.commissary = commissary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getCommissary() {
        return commissary;
    }

    public void setCommissary(String commissary) {
        this.commissary = commissary;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}