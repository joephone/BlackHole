package com.transcendence.blackhole.ui.rv.freshloadmore.act;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.global.API;
import com.transcendence.blackhole.ui.rv.freshloadmore.adapter.RvFreshAndLoadMoreAdapter;
import com.transcendence.blackhole.ui.rv.freshloadmore.bean.RvFreshLoadMoreBean;
import com.transcendence.blackhole.utils.GsonUtils;
import com.transcendence.blackhole.utils.L;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author Joephone on 2020/4/16 23:01
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RvFreshAndLoadMoreActivity extends TitleBarActivity {

    private RecyclerView mRv;
    private RvFreshAndLoadMoreAdapter adapter;
    private ProgressDialog loadingDialog;


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            //更新UI
            switch (msg.what) {
                case 1:
                    updateUI((RvFreshLoadMoreBean)msg.obj);
                    break;
            }
        };
    };



    @Override
    protected int getLayoutId() {
        return R.layout.activity_ui_rv_fresh_loadmore;
    }

    @Override
    protected void init() {

        mRv = findViewById(R.id.rv);
        loadingDialog = new ProgressDialog(mActivity);

        GridLayoutManager manager = new GridLayoutManager(mActivity,2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(manager);


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    private void loadData() {
        L.d("loadData");
        loadingDialog.show();
        OkHttpClient okhttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(API.GANK_IO.GIRLS).build();
        okhttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.d("onFailure");
                loadingDialog.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                L.d("onResponse");
                String json = response.body().string();
                RvFreshLoadMoreBean bean = GsonUtils.getInstance().json2Cls(json, RvFreshLoadMoreBean.class);
                loadingDialog.dismiss();
                Message message = Message.obtain();
                message.what = 1;
                message.obj = bean;
                mHandler.sendMessage(message);

            }
        });


    }


    private void updateUI(RvFreshLoadMoreBean bean) {
        if(adapter == null){
            adapter = new RvFreshAndLoadMoreAdapter(mActivity,bean.getData());
            mRv.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }


}
