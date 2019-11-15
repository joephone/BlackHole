package com.transcendence.map.mobike.main.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.transcendence.map.R;

/**
 * @author Joephone on 2019/11/15 16:52
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class SlidingUpPanelTestActivity extends AppCompatActivity {

    private SlidingUpPanelLayout mSlidingUpPanelLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidinguppanel_test);
        initView();
    }
    //自己实现哦
    private void initView()
    {
        mSlidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mSlidingUpPanelLayout.setPanelSlideListener(new SlidingUpPanelLayout.SimplePanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

//                AnimationUtil.setAlphaVisibility(mManagePanelMask, slideOffset);
            }

            @Override
            public void onPanelExpanded(View panel) {
//                Fragment f = getSupportFragmentManager().findFragmentById(R.id.layout_manage_container);
//
//
//                if (!(f instanceof SlidingUpPanelLayout.PanelSlideListener))
//                    return;
//
//                ((SlidingUpPanelLayout.PanelSlideListener) f).onPanelExpanded(panel);
            }

            @Override
            public void onPanelCollapsed(View panel) {
//                Fragment f = getSupportFragmentManager().findFragmentById(R.id.layout_manage_container);
//                if (!(f instanceof SlidingUpPanelLayout.PanelSlideListener))
//                    return;
//
//                ((SlidingUpPanelLayout.PanelSlideListener) f).onPanelCollapsed(panel);
            }
        });
        //在程序中加入Fragment
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        final Fragment fragment = MyMemeFragment.getInstance();
//        fragmentTransaction.add(R.id.layout_manage_container, fragment);
//        fragmentTransaction.commit();
    }

}
