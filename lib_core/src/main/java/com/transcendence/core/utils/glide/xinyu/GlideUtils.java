package com.transcendence.core.utils.glide.xinyu;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.transcendence.core.R;


/**
 * Created by Administrator on 2016/12/28.
 */

public class GlideUtils {
    public static void glideLoader(Context context, String url, int erroImg, int emptyImg, ImageView iv) {
        //原生 API
        Glide.with(context).load(url).placeholder(emptyImg).error(erroImg).into(iv);
    }

    /**
     *
     * @param context 上下文
     * @param url 图片地址
     * @param erroImg 加载错误图显示片
     * @param emptyImg 加载中显示图片
     * @param iv 控件
     * @param tag 0代表圆形 1代表圆角
     * @param r   圆角角度
     */
    public static void glideLoader(Context context, Object url, int erroImg, int emptyImg, ImageView iv, int tag, int r) {
        if (0 == tag) {
            Glide.with(context).load(url).placeholder(emptyImg).error(erroImg).transform(new GlideCircleTransform(context)).into(iv);
        } else if (1 == tag) {
            Glide.with(context).load(url).placeholder(emptyImg).error(erroImg).transform(new GlideRoundTransform(context, r)).into(iv);
        }  else if (2 == tag) {
            Glide.with(context).load(url).placeholder(emptyImg).error(erroImg).transform(new GlideRotateTransformation(context, r)).into(iv);
        }
    }


    public static void fitCenter(Context context, Object url, ImageView iv ){
        Glide.with(context).load(url).fitCenter().into(iv);
    }

    //R.drawable."图片的名称"
    public static void gif(Context context, Object url, ImageView iv ){
        Glide.with(context).load(url).asGif().into(iv);
    }

    public static void gifCount(Context context, int localUrl, ImageView iv,int count){
        Glide.with(context).load(localUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<Integer, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                // 获取gif动画时长
                GifDrawable drawable = (GifDrawable) resource;
                GifDecoder decoder = drawable.getDecoder();
                long duration = 0;
                for (int i = 0; i < drawable.getFrameCount(); i++) {
                    duration += decoder.getDelay(i);
                }
//                Log.e("peter", "动画时长" + duration);
                //后面这段代码根据你的具体业务需求，添加相应的代码块
//                handler.sendEmptyMessageDelayed(MESSAGE_SUCCESS,
//                        duration + 700);
                return false;
            }
        }).into(new GlideDrawableImageViewTarget(iv, count));//1代表播放一次
    }



    /**
     *
     * @param context 上下文
     * @param url 图片地址
     * @param erroImg 加载错误图显示片
     * @param emptyImg 加载中显示图片
     * @param iv 控件
     * @param tag 0代表圆形 1代表圆角
     * @param radio   圆角角度
     */
    public static void glideLoader(Context context, String url, int erroImg, int emptyImg, ImageView iv, int tag, int radio,int L) {
        url = url+"?x-oss-process=image/resize,l_"+L;
        if (0 == tag) {
            Glide.with(context).load(url).placeholder(emptyImg).error(erroImg).transform(new GlideCircleTransform(context)).into(iv);
        } else if (1 == tag) {
            Glide.with(context).load(url).placeholder(emptyImg).error(erroImg).transform(new GlideRoundTransform(context, radio)).into(iv);
        }  else if (2 == tag) {
            Glide.with(context).load(url).placeholder(emptyImg).error(erroImg).transform(new GlideRotateTransformation(context, radio)).into(iv);
        }
    }

    public static void fitCenter(Context context, String url, ImageView iv,int h,int w){
        url = url+"?x-oss-process=image/resize,m_fixed,h_"+h+",w_"+w;
//        Logs.logE("url---"+url);
        Glide.with(context).load(url).fitCenter().into(iv);
    }

    public static void fitCenter(Context context, String url, ImageView iv,int L){
        url = url+"?x-oss-process=image/resize,l_"+L;
//        Logs.logE("url---"+url);
        Glide.with(context).load(url).fitCenter().into(iv);
    }

    public static void avatar(Context mContext, String url, ImageView ivAvatar,int L){
        url = url+"?x-oss-process=image/resize,l_"+L;
        GlideUtils.glideLoader(mContext,url, R.drawable.ic_default,R.drawable.ic_default,
                ivAvatar ,1,5);
    }

}
