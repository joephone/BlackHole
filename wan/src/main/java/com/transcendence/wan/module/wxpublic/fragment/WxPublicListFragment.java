package com.transcendence.wan.module.wxpublic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.transcendence.blackhole.utils.L;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.module.wxpublic.model.WxChapterBean;
import com.transcendence.wan.module.wxpublic.presenter.WxPublicListPresenter;
import com.transcendence.wan.module.wxpublic.view.WxPublicListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2020/4/18 15:47
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WxPublicListFragment extends WanBaseFragment<WxPublicListPresenter> implements WxPublicListView {


   private List<WxChapterBean> mList;

    public static WxPublicListFragment newInstance(ArrayList<WxChapterBean> list) {
        WxPublicListFragment fragment = new WxPublicListFragment();
        Bundle args = new Bundle();
        args.putSerializable("list", list);
        fragment.setArguments(args);
        return fragment;
    }


    public void setList(List<WxChapterBean> list) {
        this.mList = list;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_wx_public_account_list;
    }

    @Nullable
    @Override
    protected WxPublicListPresenter initPresenter() {
        return new WxPublicListPresenter();
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            mList = (List<WxChapterBean>) getArguments().getSerializable("list");
            L.d("WxPublicListFragment onCreateView"+mList.size());
        }
    }

    @Override
    protected void loadData() {

    }



}
