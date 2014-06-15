package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.GCWF.Utils.SQLiteDB;
import com.glowpadview.GlowPadView;
import com.glowpadview.GlowPadView.OnTriggerListener;

/**
 * Created by Chuan on 6/13/14.
 */
public class MainActivity extends Activity implements OnTriggerListener {

    private GlowPadView mGlowPadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SQLiteDB sqLiteDB = new SQLiteDB(this, "attendance");

        mGlowPadView = (GlowPadView) findViewById(R.id.glow_pad_view);

        mGlowPadView.setOnTriggerListener(this);

        mGlowPadView.setShowTargetsOnIdle(true);
    }

    @Override
    public void onGrabbed(View v, int handle) { }

    @Override
    public void onReleased(View v, int handle) {
        mGlowPadView.ping();
    }

    @Override
    public void onTrigger(View v, int target) {
        final int resId = mGlowPadView.getResourceIdForTarget(target);
        switch (resId) {
            case R.drawable.ic_item_classes:
                Intent classIntent = new Intent();
                Bundle classBundle = new Bundle();
                classBundle.putString("op", "classes");
                classIntent.putExtras(classBundle);
                classIntent.setClass(MainActivity.this, ClassesActivity.class);
                startActivity(classIntent);
                break;
            case R.drawable.ic_item_rollcall:
                Intent aIntent = new Intent();
                Bundle aBundle = new Bundle();
                aBundle.putString("op", "rollcall");
                aIntent.putExtras(aBundle);
                aIntent.setClass(MainActivity.this, ShowCourseActivity.class);
                startActivity(aIntent);
                break;
            case R.drawable.ic_item_course:
                Intent courseIntent = new Intent();
                Bundle courseBundle = new Bundle();
                courseBundle.putString("op", "course");
                courseIntent.putExtras(courseBundle);
                courseIntent.setClass(MainActivity.this, CourseAvtivity.class);
                startActivity(courseIntent);
                break;
            case R.drawable.ic_item_kaoqin:
                Intent kaoqinIntent = new Intent();
                Bundle kaoqinBundle = new Bundle();
                kaoqinBundle.putString("op", "kaoqin");
                kaoqinIntent.putExtras(kaoqinBundle);
                //kaoqinIntent.setClass(MainActivity.this, ShowCourseActivity.class);
               // kaoqinIntent.setClass(MainActivity.this, TestActivity.class);
                startActivity(kaoqinIntent);
                break;
            default:
                // Code should never reach here.
        }

    }

    @Override
    public void onGrabbedStateChange(View v, int handle) { }

    @Override
    public void onFinishFinalAnimation() { }

}