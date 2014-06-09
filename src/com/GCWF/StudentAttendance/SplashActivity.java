package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 1500;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("count", 0); // 存在则打开它，否则创建新的Preferences
                int count = preferences.getInt("count", 0); // 取出数据
        System.out.println(count);
                Intent mainIntent = new Intent();
                if (count == 0) {
                    mainIntent.setClass(SplashActivity.this,GuideActivity.class);
                    SharedPreferences.Editor editor = preferences.edit(); // 让preferences处于编辑状态
                    editor.putInt("count", 1); // 存入数据
                    editor.commit(); // 提交修改
                } else {
                    mainIntent.setClass(SplashActivity.this, MainActivity.class);
                }
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}
