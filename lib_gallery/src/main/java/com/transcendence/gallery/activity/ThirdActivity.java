package com.transcendence.gallery.activity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.transcendence.gallery.R;
import com.ycbjie.zoomimagelib.view.ZoomImageView;

public class ThirdActivity extends AppCompatActivity {

    private ZoomImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 设置contentFeature,可使用切换动画

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            }
            Transition explode = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setEnterTransition(explode);
            }
        }



        setContentView(R.layout.activity_third);

        imageView = findViewById(R.id.image);
        Uri parse = Uri.parse("file:///android_asset/yc.png");
        Glide.with(this)
                .load(R.drawable.image1)    //parse
                .into(imageView);
//        Picasso.with(this)
//                .load(parse)
//                .memoryPolicy(MemoryPolicy.NO_CACHE)
//                .into(imageView, new Callback() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//
//                    @Override
//                    public void onError() {
//
//                    }
//                });


    }
}
