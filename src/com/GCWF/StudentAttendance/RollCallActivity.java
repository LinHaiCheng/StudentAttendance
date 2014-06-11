package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.GCWF.Model.Class;
import com.GCWF.Model.Student;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Chuan on 6/5/14.
 */
public class RollCallActivity extends Activity {

    private int currentStuIndex;
    private ImageView imageView = null;
    private EditText editText = null;
    private Button comeButton = null;
    private Button leaveButton = null;
    private Button absenceButton = null;
    private Button fuckOffButton = null;

    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rollcall);

        Student stu = new Student();
        students = new LinkedList<Student>();
        students.add(stu);

        imageView = (ImageView)findViewById(R.id.stuhead);
        imageView.setImageResource(R.drawable.head);
        editText = (EditText)findViewById(R.id.stuinfo);
        comeButton = (Button)findViewById(R.id.come);
        leaveButton = (Button)findViewById(R.id.leave);
        absenceButton = (Button)findViewById(R.id.absence);
        fuckOffButton = (Button)findViewById(R.id.fuckoff);

        currentStuIndex = 0;
        configUI();    // Display the information of first student in the class

        // Configure the listeners
        comeButton.setOnClickListener(comeButtonListener);
        leaveButton.setOnClickListener(leaveButtonListener);
        absenceButton.setOnClickListener(absenceButtonListener);
        fuckOffButton.setOnClickListener(fuckOffButotnListener);
    }

    private void configUI() {
        if (currentStuIndex <= students.size()) {
            Student currentStu = students.get(currentStuIndex);

            String stuInfo = "学号: " + currentStu.getId() + "\n"
                    + "姓名: " + currentStu.getName() + "\n"
                    + currentStu.getaClass().getAcademy()
                    + currentStu.getaClass().getClassName() + "\n"
                    + "请假次数: " + "0" + "\n"
                    + "旷课次数: " + "0";

            editText.setText(stuInfo);
        } else {

        }
    }

    private View.OnClickListener comeButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Student currentStu = students.get(currentStuIndex);
    //        currentStu.setCome(true);
    //        aClass.setComeStuNum(aClass.getComeStuNum() + 1);
            ++ currentStuIndex;
            configUI();
        }
    };

    private View.OnClickListener leaveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Student currentStu = students.get(currentStuIndex);
    //        currentStu.setLeave(true);
    //        aClass.setLeaveStuNum(aClass.getLeaveStuNum() + 1);
            ++ currentStuIndex;
            configUI();
        }
    };

    private View.OnClickListener absenceButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Student currentStu = students.get(currentStuIndex);
    //        currentStu.setAbsence(true);
    //        aClass.setAbsenceStuNum(aClass.getAbsenceStuNum() + 1);
            ++ currentStuIndex;
            configUI();
        }
    };

    private View.OnClickListener fuckOffButotnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Student currentStu = students.get(currentStuIndex);
    //        currentStu.setAbsence(true);
    //        aClass.setAbsenceStuNum(aClass.getAbsenceStuNum() + 1);
    //        currentStu.setAllowToExam(true);
            ++ currentStuIndex;
            configUI();
        }
    };
}
