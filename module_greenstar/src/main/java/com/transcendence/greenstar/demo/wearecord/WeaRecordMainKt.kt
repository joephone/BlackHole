//package com.transcendence.greenstar.demo.wearecord
//
//import com.transcendence.core.base.common.activity.AppAc
//import com.transcendence.greenstar.R
//
///**
// * @author joephone
// * @date 2023/7/18
// * @desc
// */
//class WeaRecordMainKt : AppAc(){
//
//    var recordButton: RecordButton? = null
//
//    override fun getLayoutId(): Int {
//        return R.layout.activity_demo_wearecord_main;
//    }
//
//    override fun initView() {
//        recordButton = findViewById(R.id.btn_record)
//        recordButton?.setAudioRecord(AudioRecorder())
//    }
//}