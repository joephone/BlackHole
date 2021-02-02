package com.transcendence.blackhole.demo.allapp;

import android.support.v4.app.Fragment;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.demo.allapp.fragment.AllAppFragment;

/**
 * @author Joephone on 2019/8/27 17:13
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class AllAppActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return AllAppFragment.newInstance();
    }

    @Override
    public int getLayoutId() {
        return  R.layout.activity_fragment;
    }

    @Override
    public void init() {
        setTitle("全APP一览");
    }
}
