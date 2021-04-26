package com.transcendence.wan.album.act;

import android.widget.ImageView;

import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.utils.GlideUtils;
import com.transcendence.wan.R;

/**
 * @Author Joephone on 2021/4/1 0001 上午 9:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class ImageActivity extends TitleBarActivity {

    private ImageView iv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image;
    }

    @Override
    protected void init() {
        setTitle("大图");
        iv = findViewById(R.id.iv_img);
        GlideUtils.getInstance().loadImageFromUrl("https://ae01.alicdn.com/kf/U76a18e0d315e407a8daf3d91de033e31i.jpg",iv);
    }
}
