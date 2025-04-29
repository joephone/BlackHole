package com.transcendence.core.utils.glide.xinyu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by Joephone on 2018/8/17 16:49
 * E-Mail Address：joephonechen@gmail.com
 */

public class GlideRotateTransformation extends BitmapTransformation {

    private float rotateRotationAngle = 0f;

    public GlideRotateTransformation(Context context, float rotateRotationAngle) {
        super(context);

        this.rotateRotationAngle = rotateRotationAngle;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Matrix matrix = new Matrix();

        matrix.postRotate(rotateRotationAngle);

        return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
    }

    @Override
    public String getId() {
        return "rotate" + rotateRotationAngle;
    }


}
