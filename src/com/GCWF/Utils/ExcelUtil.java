package com.GCWF.Utils;

import android.database.sqlite.SQLiteDatabase;
import com.GCWF.Dao.ClassDao;
import com.GCWF.Dao.StudentDao;
import com.GCWF.Model.*;
import com.GCWF.Model.Class;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 程海林 on 2014/6/11.
 */
public class ExcelUtil {
    public boolean exportStudent(File file) {
        Workbook readwb = null;
        List<Student> students;
        boolean flog = false;
        try {
            InputStream input = new FileInputStream(file);
            readwb = Workbook.getWorkbook(input);
            Sheet readsheet = readwb.getSheet(0);
            int rsRows = readsheet.getRows();//总行数
            if ("学号".equals(readsheet.getCell(0, 0).getContents()) &&
                    "姓名".equals(readsheet.getCell(1, 0).getContents()) &&
                    "性别".equals(readsheet.getCell(2, 0).getContents()) &&
                    "联系方式".equals(readsheet.getCell(3, 0).getContents()) &&
                    "学院".equals(readsheet.getCell(4, 0).getContents()) &&
                    "班级".equals(readsheet.getCell(5, 0).getContents()) &&
                    "职务".equals(readsheet.getCell(6, 0).getContents())) {
                //获取班级信息，将信息存入数据库
                Class classes = new Class();
                classes.setClassName(readsheet.getCell(5, 1).getContents());
                classes.setAcademy(readsheet.getCell(4, 1).getContents());
                classes.setTotal(rsRows - 1);
                for (int i = 1; i < rsRows; i++) {
                    String duty = readsheet.getCell(6, i).getContents();
                    if (duty != null && "班长".equals(duty)) {
                        classes.setMonitor(duty);
                    }
                    if (duty != null && "学习委员".equals(duty)) {
                        classes.setCommissary(duty);
                    }
                }
                ClassDao classDao = new ClassDao();
                classes = classDao.addClass(classes);
                //获取所有学生信息，将信息存入数据库
                students = new ArrayList<Student>();
                for (int i = 1; i < rsRows; i++) {
                    Student student = new Student();
                    student.setId(readsheet.getCell(0, i).getContents());
                    student.setName(readsheet.getCell(1, i).getContents());
                    student.setSex(readsheet.getCell(2, i).getContents());
                    student.setPhone(readsheet.getCell(3, i).getContents());
                    String duty = readsheet.getCell(6, i).getContents();
                    if (duty == null) {duty = "";}
                    student.setDuty(duty);

                    student.setaClass(classes);

                    students.add(student);
                }
                StudentDao studentDao = new StudentDao();
                studentDao.addStudents(students);
                flog = true;
            } else {
                flog = false;
            }
        } catch (Exception e) {
            flog = false;
            e.printStackTrace();
        } finally {
            readwb.close();
        }
        return flog;
    }
}
