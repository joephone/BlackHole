package com.mzelzoghbi.zgallery.activities;

import android.support.v7.widget.RecyclerView;

import com.mzelzoghbi.zgallery.CustomViewPager;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.library.R;

/**
 * @author Joephone on 2019/8/23 17:27
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class ZGalleryActivity extends TitleBarActivity {

    private CustomViewPager mViewPager;
    private RecyclerView mRv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_z_gallery_main_vp;
    }

    @Override
    public void init() {
        mViewPager = findViewById(R.id.vp);
        mRv = findViewById(R.id.rv);
    }
}
