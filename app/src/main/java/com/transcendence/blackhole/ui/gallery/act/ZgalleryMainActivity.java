package com.transcendence.blackhole.ui.gallery.act;

import android.view.View;
import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;

/**
 * @author Joephone on 2019/8/23 16:21
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class ZgalleryMainActivity extends TitleBarActivity implements View.OnClickListener {

    private TextView tvGallery;
    private TextView tvGv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gallary_z_main;
    }

    @Override
    public void init() {
        setTitle("zGallery");
        tvGallery = findViewById(R.id.tvGallery);
        tvGv = findViewById(R.id.tvGv);
        tvGallery.setOnClickListener(this);
        tvGv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvGallery:
                break;
            case R.id.tvGv:
                break;
        }
    }
}
