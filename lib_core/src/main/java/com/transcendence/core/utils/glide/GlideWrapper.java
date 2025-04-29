//package com.transcendence.core.utils.glide;
//
//import android.graphics.drawable.Drawable;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.view.animation.LinearInterpolator;
//import android.widget.ImageView;
//
//import androidx.annotation.Nullable;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.DataSource;
//import com.bumptech.glide.load.engine.GlideException;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.RequestOptions;
//import com.bumptech.glide.request.target.Target;
//import com.transcendence.core.R;
//import com.transcendence.core.base.app.CoreApp;
//import com.transcendence.core.base.app.MainApplication;
//
///**
// * @Author Joephone on 2022/9/5 0005 上午 10:50
// * @E-Mail Address：joephonechen@gmail.com
// * @Desc
// * @Edition 1.1
// * @EditionHistory  1.1 改名为wrapper 添加 gpt 提供的options
// */
//public class GlideWrapper {
//
//    private static GlideWrapper instance;
//
//    private GlideWrapper(){}
//
//    public static GlideWrapper getInstance() {
//        if (instance == null) {
//            instance = new GlideWrapper();
//        }
//        return instance;
//    }
//
//    public void loadImageFromUrl(String imagePath, ImageView imageView){
//        Glide.with(CoreApp.getInstance())
//                .load(imagePath).listener(new RequestListener<Drawable>() {
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                showAnimation(imageView);
//                return false;
//            }
//        })
//                .apply(options)
//                .into(imageView);
//    }
//
//    public void loadImageFromDra(Drawable imagePath, ImageView imageView){
//        Glide.with(CoreApp.getInstance())
//                .load(imagePath)
//                .apply(options)
//                .into(imageView);
//    }
//
//    public void loadImageFromUrl(int imagePath, ImageView imageView){
//        Glide.with(CoreApp.getInstance())
//                .load(imagePath)
//                .apply(options)
//                .into(imageView);
//    }
//
//    private static RequestOptions options = new RequestOptions()
//            .placeholder(R.drawable.ic_placeholder)
//            .error(R.drawable.ic_default_img);
//
//    private void showAnimation(View view) {
//        Animation animation = AnimationUtils.loadAnimation(CoreApp.getInstance(), R.anim.fade_in);
//        LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
//        animation.setInterpolator(lin);
//        view.startAnimation(animation);
//    }
//
//}
