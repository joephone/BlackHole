package com.transcendence.blackhole.demo.mvp.act;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.toast.ToastUtils;
import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.BaseMvpActivity;
import com.transcendence.blackhole.demo.mvp.adp.MvpBeautyListAdapter;
import com.transcendence.blackhole.demo.mvp.bean.Beauty;
import com.transcendence.blackhole.demo.mvp.presenter.BeautyListPresenter;
import com.transcendence.blackhole.demo.mvp.view.IBeautyListView;

import java.util.List;

/**
 * @author Joephone on 2019/7/9 16:09
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 *
 * @Edition 1.0
 * @EditionHistory
 */

public class MvpBeautyListActivity extends BaseMvpActivity<IBeautyListView,BeautyListPresenter<IBeautyListView>> implements IBeautyListView {

    RecyclerView mRv;
    TextView mTv;
    String test;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mvp_beauty_list;

    }

    @Override
    public void init() {
        setTitle("老张的期待");
        mRv = findViewById(R.id.rv);
        mTv = findViewById(R.id.tv);
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basePresenter.presenter();
            }
        });

    }


    @Override
    protected BeautyListPresenter<IBeautyListView> createPresenter() {
        return new BeautyListPresenter<>();
    }

    @Override
    public void onShowLoading() {
        ToastUtils.show("loading");
    }

    @Override
    public void onDismissLoading() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    ToastUtils.show("加载成功");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onShowBeautyList(List<Beauty> beautyList) {
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRv.setLayoutManager(mLinearLayoutManager);

        MvpBeautyListAdapter adapter = new MvpBeautyListAdapter(mActivity,beautyList);
        mRv.setAdapter(adapter);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
