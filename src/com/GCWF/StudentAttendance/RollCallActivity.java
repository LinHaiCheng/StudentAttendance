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

/**
 * Created by Chuan on 6/5/14.
 */
public class RollCallActivity extends Activity {

    private Class aClass;
    private int currentStuIndex;
    private ImageView imageView = null;
    private EditText editText = null;
    private Button comeButton = null;
    private Button leaveButton = null;
    private Button absenceButton = null;
    private Button fuckOffButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rollcall);

        Intent intent = getIntent();
        aClass = (Class)getIntent().getSerializableExtra("classInfo");

        imageView = (ImageView)findViewById(R.id.stuhead);
        imageView.setImageResource(R.drawable.head);
        editText = (EditText)findViewById(R.id.stuinfo);
        comeButton = (Button)findViewById(R.id.come);
        leaveButton = (Button)findViewById(R.id.leave);
        absenceButton = (Button)findViewById(R.id.absence);
        fuckOffButton = (Button)findViewById(R.id.fuckoff);

        currentStuIndex = 0;
        configUI(0);    // Display the information of first student in the class

        // Configure the listeners
        comeButton.setOnClickListener(comeButtonListener);
        leaveButton.setOnClickListener(leaveButtonListener);
        absenceButton.setOnClickListener(absenceButtonListener);
        fuckOffButton.setOnClickListener(fuckOffButotnListener);
    }

    private void configUI(int stuIndex) {
        Student currentStu = aClass.getStudents().get(currentStuIndex);

        String stuInfo = "学号: " + currentStu.getId() + "\n"
                + "姓名: " + currentStu.getName() + "\n"
                + currentStu.getAcademy()
                + currentStu.getClassOfAcademy() + "\n"
                + "请假次数: " + currentStu.getLeaveNum() + "\n"
                + "旷课次数: " + currentStu.getAbsenceNum();

        editText.setText(stuInfo);
    }

    private View.OnClickListener comeButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Student currentStu = aClass.getStudents().get(currentStuIndex);
            currentStu.setCome(true);
            aClass.setComeStuNum(aClass.getComeStuNum() + 1);
            configUI(++ currentStuIndex);
        }
    };

    private View.OnClickListener leaveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Student currentStu = aClass.getStudents().get(currentStuIndex);
            currentStu.setLeave(true);
            aClass.setLeaveStuNum(aClass.getLeaveStuNum() + 1);
            configUI(++ currentStuIndex);
        }
    };

    private View.OnClickListener absenceButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Student currentStu = aClass.getStudents().get(currentStuIndex);
            currentStu.setAbsence(true);
            aClass.setAbsenceStuNum(aClass.getAbsenceStuNum() + 1);
            configUI(++ currentStuIndex);
        }
    };

    private View.OnClickListener fuckOffButotnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Student currentStu = aClass.getStudents().get(currentStuIndex);
            currentStu.setAbsence(true);
            aClass.setAbsenceStuNum(aClass.getAbsenceStuNum() + 1);
            currentStu.setAllowToExam(true);
            configUI(++ currentStuIndex);
        }
    };
}
