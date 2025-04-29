package com.transcendence.freeland.basefun.countdownrestart

import android.app.Activity
import android.content.Intent
import android.widget.TextView
import com.transcendence.core.BuildConfig
import com.transcendence.core.base.activity.AppAc
import com.transcendence.freeland.R
import com.transcendence.freeland.main.ArouterAc
import com.transcendence.freeland.main.guide.LauncherActivity
import java.util.*

/**
 * @author joephone
 * @date 2023/6/5
 * @desc
 */
class CountDownRestartKt : AppAc() {
    var currentSecond = 3;
    var mTv : TextView? =null
    var mTimer : Timer? =null
    val task = object : TimerTask() {
        override fun run() {
            mTv?.setText("")
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_basefun_bugly
    }

    override fun initView() {
        mTv = findViewById(R.id.tv)
        mTv?.setOnClickListener {
            mTimer?.cancel()
            reStart()
        }
        countDown()
    }

    /**
     * 然后每隔1秒执行一次task 总共2两秒
     */
    private fun countDown(){
        mTimer = Timer()
//        mTimer!!.schedule(
//            task,0,2000
//        )
        mTimer?.schedule(object : TimerTask() {
            override fun run() {
                //需要执行的任务
                currentSecond--
                //在 Kotlin 中，字符串模板使用符号 $ 来标识需要被替换的表达式。在表达式中，您可以使用任何变量或常量，包括数字和字符串。
                mTv?.text = "$currentSecond 秒后重新启动，不等了点击立即重启"
                if(currentSecond == 0){
                    reStart()
                }
            }
        }, 0,2000)
    }

    private fun reStart() {
        val intent: Intent
        intent = if (BuildConfig.DEBUG) {
            // 如果是未登录的情况下跳转到闪屏页
            Intent(mActivity, LauncherActivity::class.java) //SplashActivity SplashActivity
        } else {
            // 如果是已登录的情况下跳转到首页
            Intent(mActivity, ArouterAc::class.java)
        }

        if (mActivity !is Activity) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        mActivity.startActivity(intent)
        finish()
    }
}



