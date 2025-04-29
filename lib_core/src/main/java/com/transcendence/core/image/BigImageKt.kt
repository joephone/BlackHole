package com.transcendence.core.image

import android.net.Uri
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import com.transcendence.core.R
import com.transcendence.core.base.activity.AppAc
import com.transcendence.core.global.Global

/**
 * @author joephone
 * @date 2023/7/3
 * @desc
 */
class BigImageKt : AppAc() {

    var imageView: ImageView? = null

    /**
     * 获取布局 ID
     */
    override fun getLayoutId(): Int {
        return R.layout.activity_bigimage
    }

    /**
     * 初始化数据
     */
    override fun initView() {
        imageView = findViewById(R.id.big_image)
        val url = intent.getStringExtra(Global.PUBLIC_INTENT_KEY.BIG_IMAGE)
        if (TextUtils.isEmpty(url)) {
            return
        }
        imageView?.setOnClickListener(View.OnClickListener { v: View? -> this@BigImageKt.finish() })

        val uri = Uri.parse(url)
        imageView?.setImageURI(uri)
//        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
//        linearParams.height= AppUtils.getScreenWidth(this);
//        imageView.setLayoutParams(linearParams);
//        GlideUtils.glideLoader(this, url+"?x-oss-process=image/resize,m_fixed,h_800,w_800", R.mipmap.icon_parient_question, R.mipmap.icon_parient_question, imageView, 1, 0);

    }
}