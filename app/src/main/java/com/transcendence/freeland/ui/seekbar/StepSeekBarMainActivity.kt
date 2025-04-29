package com.transcendence.freeland.ui.seekbar

import com.transcendence.core.base.activity.AppAc
import com.transcendence.freeland.R

/**
 * @Author Joephone C
 * @CreateTime 2024年07月23日 11:52:03
 * @Desc Seek Bar
 */
class StepSeekBarMainActivity : AppAc(){
    override fun getLayoutId(): Int {
        return R.layout.activity_ui_seekbar_step
    }

    override fun initView() {
        setTitle("Seek Bar")

    }
}