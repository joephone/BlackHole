package com.transcendence.blackhole.ui.image.act;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.hjq.image.ImageLoader;
import com.lzy.imagepicker.bean.ImageItem;
import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;

/**
 * @author Joephone on 2019/6/19 14:18
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class PictruePreviewActivity extends TitleBarActivity {

    ImageView mIvImg;
    ImageItem imageItem = new ImageItem();

    @Override
    public int getLayoutId() {
        return R.layout.activity_image_preview;
    }

    @Override
    public void init() {
        setTitle("单图预览");
        mIvImg = findViewById(R.id.ivImg);
        Bundle bundle = getIntent().getExtras();
        imageItem = (ImageItem) bundle.getSerializable("ImageItem");
        if(!TextUtils.isEmpty(imageItem.path)){
            ImageLoader.loadImage(mIvImg,imageItem.path);
        }else if(imageItem.resourceId > 0){
            ImageLoader.loadImage(mIvImg,imageItem.resourceId);
        }
    }
}
