package com.transcendence.greenstar.demo.exportlog.logcat

import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.ToggleButton
import com.transcendence.core.base.activity.AppAc
import com.transcendence.greenstar.R
import com.transcendence.logcat.Logcat
import com.transcendence.logcat.SharedPrefHelper

/**
 * @author joephone
 * @date 2023/6/15
 * @desc 导出日志
 */
class LogcatKt : AppAc() {
    var mTvShowRunLog : TextView? =null
    var mSharedPrefHelper: SharedPrefHelper? = null
    var tBtnLogcatToggle: ToggleButton?=null
    var mTBtnAddLog: ToggleButton?=null
    /**
     * 获取布局 ID
     */
    override fun getLayoutId(): Int {
        return R.layout.activity_demo_exportlog_logcat
    }

    /**
     * 初始化数据
     */
    override fun initView() {
        mSharedPrefHelper = SharedPrefHelper.newInstance(this, Logcat.SP_LOGCAT_CONFIG)
        mTBtnAddLog = findViewById<View>(R.id.tbtn_log_toggle) as ToggleButton
        mTBtnAddLog!!.setOnCheckedChangeListener(mOnCheckedChangeListener)
        tBtnLogcatToggle = findViewById(R.id.tbtn_logcat_toggle)
        tBtnLogcatToggle?.setChecked(mSharedPrefHelper!!.getBoolean("logcat_enabled", false))
        tBtnLogcatToggle?.setOnCheckedChangeListener(mOnCheckedChangeListener)

//        LogManager.getInstance().startInit(mActivity)
    }

    private val mOnCheckedChangeListener =
        CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            when (buttonView.id) {
                R.id.tbtn_logcat_toggle -> {
                    if (isChecked) {
                        Logcat.enableLogcat(this)
                    } else {
                        Logcat.disableLogcat()
                    }
                    mSharedPrefHelper!!.saveBoolean("logcat_enabled", isChecked)
                }
                R.id.tbtn_log_toggle ->{
                    if(isChecked) {
                        mHandler.sendEmptyMessageDelayed(0, 1000);
                    } else {
                        mHandler.removeMessages(0);
                    }
                }

            }
        }

    override fun onStart() {
        super.onStart()
//        if (mSharedPrefHelper!!.getBoolean("logcat_enabled", false)) {
//            Logcat.enableLogcat(this)
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Logcat.disableLogcat()
    }

    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0 -> {
                    Log.d("AndroidLogcat", "This is test log : " + System.currentTimeMillis())
                    if (null != mTBtnAddLog && mTBtnAddLog!!.isChecked()) {
                        sendEmptyMessageDelayed(0, 1000)
                    }
                }
                else -> {
                }
            }
        }
    }
}