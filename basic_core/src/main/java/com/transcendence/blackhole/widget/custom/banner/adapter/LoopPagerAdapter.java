package com.transcendence.blackhole.widget.custom.banner.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Joephone on 2018/4/9 14:54
 * E-Mail Address：joephonechen@gmail.com
 * banner 无限循环view adapter
 * @author Administrator
 */

public class LoopPagerAdapter extends PagerAdapter {
    private List<View> views;
    private boolean isScorll;

    public LoopPagerAdapter(List<View> views) {
        this.views = views;
        if(views.size()>1) {
            isScorll = true;
        }else if(views.size()==1){
            isScorll = false;
        }
    }

    @Override
    public int getCount() {
        //Integer.MAX_VALUE = 2147483647
        if(isScorll){
            return Integer.MAX_VALUE;
        }else {
            return 1;
        }

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (views.size() > 0) {
            //position % view.size()是指虚拟的position会在[0，view.size()）之间循环
            View view = views.get(position % views.size());
            if (container.equals(view.getParent())) {
                container.removeView(view);
            }
            container.addView(view);
            return view;
        }
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }


}
