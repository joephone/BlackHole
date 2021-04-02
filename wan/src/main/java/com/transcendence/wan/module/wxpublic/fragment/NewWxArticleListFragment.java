package com.transcendence.wan.module.wxpublic.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.transcendence.core.utils.L;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.listener.OnMyItemClickListener;
import com.transcendence.wan.module.knowledge.fragment.TreeRightFragment;
import com.transcendence.wan.module.wxpublic.adapter.NewWxLeftAdapter;
import com.transcendence.wan.module.wxpublic.model.WxChapterBean;
import com.transcendence.wan.module.wxpublic.presenter.NaviWxPublicPresenter;
import com.transcendence.wan.module.wxpublic.view.NaviWxPublicView;

import java.util.List;

/**
 * @Author Joephone on 2021/3/30 0030 下午 3:20
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class NewWxArticleListFragment extends WanBaseFragment<NaviWxPublicPresenter> implements NaviWxPublicView {

    private NewWxLeftAdapter mAdapter;
    private RecyclerView mRv;

    private WxArticleListFragment mRightFragment;

    public static NewWxArticleListFragment newInstance(String title) {
        NewWxArticleListFragment fragment = new NewWxArticleListFragment();
        Bundle args = new Bundle();
        args.putString("text", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_new_wx_main;
    }

    @Override
    protected NaviWxPublicPresenter initPresenter() {
        return new NaviWxPublicPresenter();
    }

    @Override
    protected void initView() {
        mRv = findViewById(R.id.rv);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRv.setLayoutManager(manager);

    }

    @Override
    protected void loadData() {
        presenter.getWxPublicList();
    }

    @Override
    public void getWxChapterSuc(int code, List<WxChapterBean> list) {
        mAdapter = new NewWxLeftAdapter(list,getActivity());
        mAdapter.setListener(new OnMyItemClickListener() {
            @Override
            public void onItemClick(int position) {
                L.d("position:"+position);
                if(position>=0){
                    createFragment(list.get(position));
                }
            }
        });
        mRv.setAdapter(mAdapter);
        createFragment(list.get(0));
    }

    @Override
    public void getWxChapterFail(int code) {

    }


    public void createFragment(WxChapterBean data) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        mRightFragment = new WxArticleListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", data);
        mRightFragment.setArguments(bundle);
//        mRightFragment.setListener(this);
        fragmentTransaction.add(R.id.fl_content, mRightFragment);
        fragmentTransaction.commit();
    }
}
