package com.transcendence.freeland.basefun.bugly

import android.widget.TextView
import com.transcendence.core.base.activity.AppAc
import com.transcendence.freeland.R

/**
 * @author joephone
 * @date 2023/6/5
 * @desc
 */
class BuglyKt : AppAc(){
    lateinit var mTvBugly :TextView

    override fun getLayoutId(): Int {
        return R.layout.activity_basefun_bugly
    }

    override fun initView() {
        mTvBugly = findViewById(R.id.tv)
        mTvBugly.setOnClickListener { v ->
            1/0
        }
    }
}