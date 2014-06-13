package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.glowpadview.GlowPadView;
import com.glowpadview.GlowPadView.OnTriggerListener;

/**
 * Created by Chuan on 6/13/14.
 */
public class GlowPadActivity extends Activity implements OnTriggerListener {

    private GlowPadView mGlowPadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glow_pad_view);

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
                Intent classIntent = new Intent(GlowPadActivity.this, ClassesActivity.class);
                startActivity(classIntent);
                break;
            case R.drawable.ic_item_attendance:
                Intent aIntent = new Intent(GlowPadActivity.this, RollCallCourseActivity.class);
                startActivity(aIntent);
                break;
            case R.drawable.ic_item_course:
                Intent courseIntent = new Intent(GlowPadActivity.this, CourseAvtivity.class);
                startActivity(courseIntent);
                break;
            case R.drawable.ic_item_kaoqin:

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