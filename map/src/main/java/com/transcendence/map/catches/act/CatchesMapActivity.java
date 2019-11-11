package com.transcendence.map.catches.act;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.transcendence.map.R;
import com.transcendence.map.catches.view.ScrollLayoutLiuF;
import com.transcendence.map.mobike.main.act.MobikeFatherActivity;

/**
 * @author Joephone on 2019/10/15 16:17
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class CatchesMapActivity extends MobikeFatherActivity {

    private ScrollLayoutLiuF mScrollLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScrollLayout = findViewById(R.id.scrollLayout);
    }


}
