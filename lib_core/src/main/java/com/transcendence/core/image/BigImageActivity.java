//package com.transcendence.core.image;
//
//import android.net.Uri;
//import android.text.TextUtils;
//import android.widget.ImageView;
//
//import com.transcendence.core.R;
//import com.transcendence.core.base.activity.AppAc;
//import com.transcendence.core.global.Global;
//
///**
// * @author joephone
// * @date 2023/6/11
// * @desc
// */
//public class BigImageActivity extends AppAc {
//
//    ImageView imageView;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_bigimage;
//    }
//
//    @Override
//    protected void initView() {
//        imageView = findViewById(R.id.big_image);
//        String url = getIntent().getStringExtra(Global.INTENT_KEY.BIG_IMAGE);
//        if(TextUtils.isEmpty(url)){
//            return;
//        }
//        imageView.setOnClickListener(v-> {
//            BigImageActivity.this.finish();
//        });
//
//        Uri uri = Uri.parse(url);
//        imageView.setImageURI(uri);
////        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
////        linearParams.height= AppUtils.getScreenWidth(this);
////        imageView.setLayoutParams(linearParams);
////        GlideUtils.glideLoader(this, url+"?x-oss-process=image/resize,m_fixed,h_800,w_800", R.mipmap.icon_parient_question, R.mipmap.icon_parient_question, imageView, 1, 0);
//
//    }
//
//
//
//
//}
