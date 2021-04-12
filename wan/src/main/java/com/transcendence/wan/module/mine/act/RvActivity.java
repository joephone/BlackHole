package com.transcendence.wan.module.mine.act;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.global.Global;
import com.transcendence.core.utils.L;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseActivity;
import com.transcendence.wan.module.beauty.model.BeautyBean;
import com.transcendence.wan.module.beauty.presenter.BeautyPresenter;
import com.transcendence.wan.module.beauty.view.BeautyView;
import com.transcendence.wan.module.mine.adapter.RvImageAdapter;
import com.transcendence.wan.module.mine.presenter.MyCoinPresenter;

import java.util.List;

/**
 * @Author Joephone on 2021/4/6 0006 下午 2:02
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class RvActivity extends WanBaseActivity<BeautyPresenter> implements BeautyView {
    private static int PAGE = 0;
    private RvImageAdapter adapter;
    private RecyclerView mRv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_rv;
    }

    @Nullable
    @Override
    protected BeautyPresenter initPresenter() {
        return new BeautyPresenter();
    }

    @Override
    protected void initView() {
        setTitle("福利社");
        mRv = findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRv.setLayoutManager(manager);
    }

    @Override
    protected void loadData() {
        presenter.getBeautyList(PAGE, Global.LIMIT);
    }


    @Override
    public void getBeautyListSuc(int code, List<BeautyBean> list) {
        L.d("getBeautyListSuc:" + code);
        adapter = new RvImageAdapter(list);
        mRv.setAdapter(adapter);
    }

    @Override
    public void getBeautyListFail(int code) {

    }
}
