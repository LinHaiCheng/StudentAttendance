package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Chuan on 6/8/14.
 */
public class MainActivity extends Activity {
    private Button courseBtn;
    private Button classeBtn;
    private Button rollcallBtn;
    private Button exportBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.features_view);

        courseBtn = (Button)findViewById(R.id.courseBtn);
        classeBtn = (Button)findViewById(R.id.classesBtn);
        rollcallBtn = (Button)findViewById(R.id.rollcallBtn);
        exportBtn = (Button)findViewById(R.id.exportBtn);

        courseBtn.setOnClickListener(onCourseBtnClickListener);
        classeBtn.setOnClickListener(onClassesBtnClickListener);
        rollcallBtn.setOnClickListener(onRollcallBtnClickListener);
        exportBtn.setOnClickListener(onExportBtnClickListener);
    }

    private View.OnClickListener onCourseBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, CourseAvtivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener onClassesBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, ClassesActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("formWhere", "MainActivity");
            intent.putExtras(mBundle);
            startActivity(intent);
        }
    };

    private View.OnClickListener onRollcallBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, RollCallCourseActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener onExportBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, GlowPadActivity.class);
            startActivity(intent);
        }
    };
}
