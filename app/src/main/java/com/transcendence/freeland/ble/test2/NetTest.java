package com.transcendence.freeland.ble.test2;

import android.widget.Button;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.freeland.R;

/**
 * @author joephone
 * @date 2023/6/4
 * @desc
 */
public class NetTest extends AppAc {
    private CheckNetWork mCheckNetWork =new CheckNetWork();
    Button btn_check;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_ble_nettest;
    }

    @Override
    protected void initView() {
        btn_check = (Button) findViewById(R.id.btn_check);
        btn_check.setOnClickListener(v->{
            checkNet();
        });
    }

    private void checkNet() {
        if(!mCheckNetWork.isNetworkAvailable(getApplication())){
            mCheckNetWork.showNetDialog(NetTest.this);
        }else {
            showMsg("有网络，哈哈");
        }
    }
}
