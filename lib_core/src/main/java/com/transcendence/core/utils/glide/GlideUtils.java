package com.transcendence.core.utils.glide;


import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
/**
 * FileName: GlideUtil
 * Created by zlx on 2020/9/18 16:02
 * Email: 1170762202@qq.com
 * Description:
 */
public class GlideUtils {

    private GlideUtils() {
    }

    private static class GlideUtilHolder {
        private static GlideUtils instance = new GlideUtils();
    }

    public static GlideUtils getInstance() {
        return GlideUtilHolder.instance;
    }

    /**
     * 加载普通图片
     */
//    public void loadImage(ImageView target, String url) {
//        Glide.with(target.getContext())
//                .load(url)
//                .transition(withCrossFade())
//                .into(target);
//
//    }


    public void loadScaleImage(ImageView target, String url) {
        Glide.with(target.getContext())
                .load(url)
                .thumbnail(0.1f)
                .into(target);
//        Glide.with(target.getContext()).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
////                int imageWidth = resource.getWidth();
////                int imageHeight = resource.getHeight();
////
////                int height = ScreenUtils.getScreenWidth() * imageHeight / imageWidth;
////                ViewGroup.LayoutParams para = target.getLayoutParams();
////                para.height = height;
////                para.width = ScreenUtils.getScreenWidth();//屏幕的宽度，没有工具类自己从网上搜
////                target.setImageBitmap(resource);
//
//                //获取原图的宽高
//                int imageWidth = resource.getWidth();
//                int imageHeight = resource.getHeight();
//
//                //获取imageView的宽
//                int imageViewWidth = target.getWidth();
//
//                //计算缩放比例
//                float sy = (float) (imageViewWidth * 0.1) / (float) (imageWidth * 0.1);
//
//                //计算图片等比例放大后的高
//                int imageViewHeight = (int) (imageHeight * sy);
//                ViewGroup.LayoutParams params = target.getLayoutParams();
//                params.height = imageViewHeight;
//                target.setLayoutParams(params);
//                target.setImageBitmap(resource);
//            }
//        });
    }




//    public void loadImage(ImageView iv, String url, Drawable drawable) {
//        Glide.with(iv.getContext())
//                .load(url)
//                .apply(RequestOptions.errorOf(drawable).placeholder(drawable))
//                .transition(withCrossFade())
//                .into(iv);
//    }
//
//    public void loadImage(ImageView iv, String url, int drawable) {
//        Glide.with(iv.getContext())
//                .load(url)
//                .apply(RequestOptions.errorOf(drawable).placeholder(drawable))
//                .transition(withCrossFade())
//                .into(iv);
//    }


    /**
     * 加载圆角图片
     */
//    public void loadRoundImage(ImageView iv, String url, int round) {
//        Glide.with(iv.getContext())
//                .load(url)
//                .transform(new CenterCrop(), new RoundedCorners(round))
//                .into(iv);
//    }
//
//
//    /**
//     * 加载圆形图片
//     */
//    public void loadCircleImage(ImageView iv, String url) {
//        Glide.with(iv.getContext())
//                .load(url)
//                .transition(new DrawableTransitionOptions().crossFade())
//                .transform(new CircleCrop()).into(iv);
//    }
}
