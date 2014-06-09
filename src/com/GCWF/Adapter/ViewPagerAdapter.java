package com.GCWF.Adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 程海林 on 2014/6/7.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<View> views; //define a List to save all views
    public ViewPagerAdapter(List<View> views) {
        this.views = views;
    }

    /**
     * get count of the views
     * @return count of the views
     */
    @Override
    public int getCount() {
        if (views != null){
            return views.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return (view == o);
    }

    @Override
    public Object instantiateItem(View view, int position) {
        ((ViewPager) view).addView(views.get(position), 0);
        return views.get(position);
    }

    @Override
    public void destroyItem(View view, int position, Object object) {
        ((ViewPager) view).removeView(views.get(position));
    }
}
