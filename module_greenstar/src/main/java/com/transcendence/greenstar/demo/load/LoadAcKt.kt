package com.transcendence.greenstar.demo.load

import com.transcendence.core.base.activity.AppAc
import com.transcendence.core.widget.loading.LoadingView
import com.transcendence.greenstar.R

/**
 * @author joephone
 * @date 2023/3/23
 * @desc
 */
class LoadAcKt: AppAc(){

    private lateinit var mLoadingView: LoadingView

    override fun getLayoutId(): Int {
        return R.layout.activity_demo_loading
    }

    override fun initView() {
        mLoadingView = findViewById(R.id.loading_view)
        mLoadingView.showLoading()
    }
}