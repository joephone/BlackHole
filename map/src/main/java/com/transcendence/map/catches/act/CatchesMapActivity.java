package com.transcendence.map.catches.act;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.transcendence.core.utils.L;
import com.transcendence.map.R;

/**
 * @author Joephone on 2019/10/15 16:17
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class CatchesMapActivity extends ScollLayoutActivity {

    private ImageView mIvMyLoc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catches);

        mScrollLayout = findViewById(R.id.scrollLayout);
        mRlRoot = findViewById(R.id.rlRoot);

        initScrollLayoutView();
        initMapViewFragment();
        mIvMyLoc = findViewById(R.id.ivMyLoc);
        mIvMyLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapUtil!=null){
                    mapUtil.onMyLoc();
                }else {
                    L.d("mapUtil == null");
                }
            }
        });

    }




}
