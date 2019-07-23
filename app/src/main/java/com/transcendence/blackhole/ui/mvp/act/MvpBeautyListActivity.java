package com.transcendence.blackhole.ui.mvp.act;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hjq.toast.ToastUtils;
import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.BaseMvpActivity;
import com.transcendence.blackhole.ui.mvp.adp.MvpBeautyListAdapter;
import com.transcendence.blackhole.ui.mvp.bean.Beauty;
import com.transcendence.blackhole.ui.mvp.presenter.BeautyListPresenter;
import com.transcendence.blackhole.ui.mvp.view.IBeautyListView;

import java.util.List;

/**
 * @author Joephone on 2019/7/9 16:09
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MvpBeautyListActivity extends BaseMvpActivity<IBeautyListView,BeautyListPresenter<IBeautyListView>> implements IBeautyListView {

    RecyclerView mRv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mvp_beauty_list;

    }

    @Override
    public void init() {
        setTitle("老张的期待");
        mRv = findViewById(R.id.rv);
        basePresenter.presenter();
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
