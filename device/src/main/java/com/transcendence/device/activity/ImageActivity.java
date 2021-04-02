package com.transcendence.device.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.device.R;
import com.transcendence.device.utils.ImageUtils;
import com.transcendence.device.utils.SizeUtils;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/9/26
 *     desc  : 图片工具类测试
 * </pre>
 */
public class ImageActivity extends TitleBarActivity {

    private ImageView ivSrc;
    private ImageView ivView2Bitmap;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_image;
    }

    @Override
    protected void init() {
        setTitle("图片工具类测试");
        ivSrc = (ImageView) findViewById(R.id.iv_src);
        ivView2Bitmap = (ImageView) findViewById(R.id.iv_view2Bitmap);
        ImageView ivRound = (ImageView) findViewById(R.id.iv_round);
        ImageView ivRoundCorner = (ImageView) findViewById(R.id.iv_round_corner);
        ImageView ivFastBlur = (ImageView) findViewById(R.id.iv_fast_blur);
        ImageView ivRenderScriptBlur = (ImageView) findViewById(R.id.iv_render_script_blur);
        ImageView ivStackBlur = (ImageView) findViewById(R.id.iv_stack_blur);
        ImageView ivAddFrame = (ImageView) findViewById(R.id.iv_add_frame);
        ImageView ivAddReflection = (ImageView) findViewById(R.id.iv_add_reflection);
        ImageView ivAddTextWatermark = (ImageView) findViewById(R.id.iv_add_text_watermark);
        ImageView ivAddImageWatermark = (ImageView) findViewById(R.id.iv_add_image_watermark);
        ImageView ivGray = (ImageView) findViewById(R.id.iv_gray);

        Bitmap src = ImageUtils.getBitmap(getResources(), R.mipmap.beauty06);
        Bitmap watermark = ImageUtils.getBitmap(getResources(), R.mipmap.ic_launcher);

        SizeUtils.forceGetViewSize(ivSrc, new SizeUtils.onGetSizeListener() {
            @Override
            public void onGetSize(View view) {
                ivView2Bitmap.setImageBitmap(ImageUtils.view2Bitmap(ivSrc));
            }
        });
        ivRound.setImageBitmap(ImageUtils.toRound(src));
        ivRoundCorner.setImageBitmap(ImageUtils.toRoundCorner(src, 60));
        ivFastBlur.setImageBitmap(ImageUtils.fastBlur(this, src, 0.1f, 5));
        ivRenderScriptBlur.setImageBitmap(ImageUtils.renderScriptBlur(this, src, 10));
        src = ImageUtils.getBitmap(getResources(), R.mipmap.beauty06);
        ivStackBlur.setImageBitmap(ImageUtils.stackBlur(src, 10, false));
        ivAddFrame.setImageBitmap(ImageUtils.addFrame(src, 16, Color.GREEN));
        ivAddReflection.setImageBitmap(ImageUtils.addReflection(src, 80));
        ivAddTextWatermark.setImageBitmap(ImageUtils.addTextWatermark(src, "blankj", 40, 0x8800ff00, 0, 0));
        ivAddImageWatermark.setImageBitmap(ImageUtils.addImageWatermark(src, watermark, 0, 0, 0x88));
        ivGray.setImageBitmap(ImageUtils.toGray(src));
    }
}