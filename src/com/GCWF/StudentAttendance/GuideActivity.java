package com.GCWF.StudentAttendance;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.GCWF.Adapter.ViewPagerAdapter;

/**
 * Created by 程海林 on 2014/6/7.
 */
public class GuideActivity extends Activity {
    private ViewPager viewPager;    //define ViewPager object
    private ViewPagerAdapter vpAdapter; //  define an Adapter by self
    private List<View> views;   //define a List to save all views

    private View view1, view2, view3;   //  define all View
    private ImageView point0, point1, point2; //define all pointImage
    private Button startBtn;    //define the start Button

    private int currIndex;  //define current idnex

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        initView();

        initData();
    }
    private void initView() {
        //instance all View
        LayoutInflater li = LayoutInflater.from(this);
        view1 = li.inflate(R.layout.guide_view01, null);
        view2 = li.inflate(R.layout.guide_view02, null);
        view3 = li.inflate(R.layout.guide_view03, null);
        //attach all View to views
        views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        //instance the ViewPager
        viewPager = (ViewPager)this.findViewById(R.id.viewpager);
        //instance the ViewPagerAdapter
        vpAdapter = new ViewPagerAdapter(views);
        //instance all point
        point0 = (ImageView)this.findViewById(R.id.page0);
        point1 = (ImageView)this.findViewById(R.id.page1);
        point2 = (ImageView)this.findViewById(R.id.page2);
        //instance the startButton
        startBtn = (Button)view3.findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void initData() {
        //set Listener for the ViewPager
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        point0.setImageDrawable(getResources().getDrawable(R.drawable.select));
                        point1.setImageDrawable(getResources().getDrawable(R.drawable.normal));
                        break;
                    case 1:
                        point1.setImageDrawable(getResources().getDrawable(R.drawable.select));
                        point0.setImageDrawable(getResources().getDrawable(R.drawable.normal));
                        point2.setImageDrawable(getResources().getDrawable(R.drawable.normal));
                        break;
                    case 2:
                        point2.setImageDrawable(getResources().getDrawable(R.drawable.select));
                        point1.setImageDrawable(getResources().getDrawable(R.drawable.normal));
                        break;
                }
                currIndex = i;
            }

            @Override
            public void onPageScrolled(int i, float v, int i2) {}

            @Override
            public void onPageScrollStateChanged(int i) {}
        });
        //set Adapter for the ViewPager
        viewPager.setAdapter(vpAdapter);

    }
}