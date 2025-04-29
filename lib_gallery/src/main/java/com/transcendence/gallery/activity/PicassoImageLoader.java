package com.transcendence.gallery.activity;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.transcendence.gallery.R;
import com.transcendence.gallery.viewpager.inter.IMediaLoader;


public class PicassoImageLoader implements IMediaLoader {

    private String url;
    private Integer thumbnailWidth;
    private Integer thumbnailHeight;
    private int image;
    private int type;
    public static final int LOCAL_IMAGE = 1;
    public static final int NETWORK_IMAGE = 2;


    public PicassoImageLoader(String url , int type) {
        this.url = url;
        this.type = type;
    }

    public PicassoImageLoader(int image ,int type) {
        this.image = image;
        this.type = type;
    }

    public PicassoImageLoader(String url, Integer thumbnailWidth, Integer thumbnailHeight) {
        this.url = url;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
    }

    public PicassoImageLoader(int image, Integer thumbnailWidth, Integer thumbnailHeight) {
        this.image = image;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
    }


    @Override
    public boolean isImage() {
        return true;
    }

    @Override
    public void loadMedia(Context context, final ImageView imageView, final IMediaLoader.SuccessCallback callback) {
        if (type==LOCAL_IMAGE){
            Glide.with(context)
                    .load(image)
                    .placeholder(R.drawable.placeholder_image)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.placeholder_image)
                    .into(imageView);
        }

    }

    @Override
    public void loadThumbnail(Context context, final ImageView thumbnailView, final IMediaLoader.SuccessCallback callback) {
//        if (type==LOCAL_IMAGE){
//            Glide.with(context)
//                    .load(image)
//                    .placeholder(R.drawable.placeholder_image)
//                    .centerInside()
//                    .into(thumbnailView);
//        } else {
//            Glide.with(context)
//                    .load(url)
//                    .placeholder(R.drawable.placeholder_image)
//                    .centerInside()
//                    .into(thumbnailView);
//        }
    }

//    private static class ImageCallback implements Callback {
//        private final IMediaLoader.SuccessCallback callback;
//
//        public ImageCallback(SuccessCallback callback) {
//            this.callback = callback;
//        }
//
//        @Override public void onSuccess() {
//            callback.onSuccess();
//        }
//
//        @Override
//        public void onError() {
//
//        }
//    }
}
