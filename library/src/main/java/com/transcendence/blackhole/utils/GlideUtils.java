package com.transcendence.blackhole.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.transcendence.blackhole.base.app.LibApplication;

/**
 * @author Joephone on 2019/5/7 17:09
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  使用简单
            可配置度高，自适应程度高
            支持常见图片格式 Jpg png gif webp
            支持多种数据源 网络、本地、资源、Assets 等
            高效缓存策略 支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
            生命周期集成 根据Activity/Fragment生命周期自动管理请求
            高效处理Bitmap 使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力
            这里默认支持Context，Glide支持Context,Activity,Fragment，FragmentActivity

 */


public class GlideUtils {
    private static GlideUtils instance;


    public static GlideUtils getInstance() {
        if (instance == null) {
            instance = new GlideUtils();
        }
        return instance;
    }

    /**
     *  1 默认加载
     */
    public void loadImageFromNew(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).into(mImageView);
    }

    /**
     *  2 加载本地
     */
    public void loadMipmap(Context mContext, int resourceId, ImageView mImageView) {
        Glide.with(mContext).load(resourceId).into(mImageView);
    }

}
