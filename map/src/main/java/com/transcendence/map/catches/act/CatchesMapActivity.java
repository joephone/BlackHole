package com.transcendence.map.catches.act;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.transcendence.map.R;
import com.transcendence.map.catches.fragment.AmapFragmentActivity;
import com.transcendence.map.catches.view.ScrollLayoutLiuF;

/**
 * @author Joephone on 2019/10/15 16:17
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class CatchesMapActivity extends AmapFragmentActivity {

    private ScrollLayoutLiuF mScrollLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScrollLayout = findViewById(R.id.scrollLayout);
    }


}
