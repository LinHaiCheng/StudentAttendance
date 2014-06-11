package com.GCWF.Model;


import java.io.Serializable;

/**
 * Created by 程海林 on 6/5/14.
 */
public class Student implements Serializable {
    private String id;          //学号
    private String name;        //姓名
    private String sex;         //性别
    private String phone;       //联系方式
    private String duty;        //职务

    private Class aClass;       //班级

    public Student() {
        id = "2011051139";
        name = "郭川磊";
        sex = "男";
        phone = "18635005795";
        duty = null;
        aClass = new Class();
    }

    public Student(String id, String name, String sex, String phone, String duty, Class aClass) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.duty = duty;
        this.aClass = aClass;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }
}
