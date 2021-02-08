package com.transcendence.guard.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.transcendence.guard.R;
import com.transcendence.guard.listener.XUtilCallBack;

/**
 * @Author Joephone on 2021/2/8 0008 下午 5:58
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class DialogSetUpPw extends Dialog implements View.OnClickListener{


    private TextView mTitle;
    private EditText mEtFirst;
    private EditText mEtSecond;

    private XUtilCallBack mCallBack;

    public DialogSetUpPw(@NonNull Context context) {
        super(context, R.style.dialog_custum);
    }

    public void setmCallBack(XUtilCallBack callBack) {
        this.mCallBack = callBack;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_ok:
//                mCallBack.ok();
                break;
            case R.id.bt_cancel:
//                mCallBack.cancel();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    private void init() {
        mTitle = findViewById(R.id.tv_title);
        mEtFirst = findViewById(R.id.et_first);
//        mEtSecond = findViewById(R.id.tv_title);
        findViewById(R.id.bt_ok).setOnClickListener(this);
        findViewById(R.id.bt_cancel).setOnClickListener(this);
    }


    public void setmTitle(String title) {
        if(!TextUtils.isEmpty(title)){
            this.mTitle.setText(title);
        }
    }
}
