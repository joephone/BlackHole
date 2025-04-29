package com.transcendence.gallery.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.base.route.RoutePath;
import com.transcendence.gallery.R;

/**
 * 学习案例：
 * https://github.com/boycy815/PinchImageView
 * https://github.com/bm-x/PhotoView
 * https://github.com/ongakuer/PhotoDraweeView
 https://github.com/panpf/sketch
 */

@Route(path = RoutePath.Gallery.PAGER_MAIN)
public class GalleryMainActivity extends AppAc {

    private ImageView imageView;
    private TextView tv_5;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_gallery;
    }

    @Override
    protected void initView() {

        imageView = findViewById(R.id.tv_1);
        tv_5 = findViewById(R.id.tv_5);
        findViewById(R.id.tv_1).setOnClickListener(v->{
            startActivity(new Intent(this, FirstActivity.class));
        });
        findViewById(R.id.tv_2).setOnClickListener(v->{
            Intent intent2 = new Intent(this, SecondActivity.class);
            startActivity(intent2);
            overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        });
        findViewById(R.id.tv_3).setOnClickListener(v->{
            Intent intent3 = new Intent(this, ThirdActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(intent3, ActivityOptions.makeSceneTransitionAnimation(
                        GalleryMainActivity.this).toBundle());
            }else {
                startActivity(intent3);
            }
        });
        findViewById(R.id.tv_4).setOnClickListener(v->{
            startActivity(new Intent(this, FourActivity.class));
        });
        findViewById(R.id.tv_5).setOnClickListener(v->{
            Intent intent5 = new Intent(this, FiveActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(intent5, ActivityOptions.makeSceneTransitionAnimation(
                        GalleryMainActivity.this, tv_5, "YangChong").toBundle());
            }
        });
        findViewById(R.id.tv_6).setOnClickListener(v->{
            startActivity(new Intent(this, FourActivity.class));
        });
    }


}
