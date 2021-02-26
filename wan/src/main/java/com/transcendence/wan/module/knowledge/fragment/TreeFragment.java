package com.transcendence.wan.module.knowledge.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.transcendence.core.utils.L;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.listener.OnMyItemClickListener;
import com.transcendence.wan.module.knowledge.adapter.TreeLeftAdapter;
import com.transcendence.wan.module.knowledge.model.TreeBean;
import com.transcendence.wan.module.knowledge.presenter.TreePresenter;
import com.transcendence.wan.module.knowledge.view.TreeView;

import java.util.List;

/**
 * @author Joephone on 2019/12/17 11:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class TreeFragment extends WanBaseFragment<TreePresenter> implements TreeView {

    private TreeLeftAdapter mAdapter;
    private RecyclerView mRv;
    private TreeRightFragment mRightFragment;

    public static TreeFragment create(String title) {
        TreeFragment fragment = new TreeFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_tree;
    }

    @Override
    protected TreePresenter initPresenter() {
        return new TreePresenter();
    }

    @Override
    protected void initView() {
        mRv = findViewById(R.id.rv);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRv.setLayoutManager(manager);
    }

    @Override
    protected void loadData() {
        presenter.getTree();
    }


    @Override
    public void getTreeSuc(int code, List<TreeBean> data) {
        mAdapter = new TreeLeftAdapter(data,getActivity());
        mAdapter.setListener(new OnMyItemClickListener() {
            @Override
            public void onItemClick(int position) {
                L.d("position:"+position);
                if(position>=0){
                    createFragment(data.get(position));
                }
            }
        });
        mRv.setAdapter(mAdapter);
        createFragment(data.get(0));
    }

    @Override
    public void getTreeFail(int code) {

    }

    public void createFragment(TreeBean data) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        mRightFragment = new TreeRightFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", data);
        mRightFragment.setArguments(bundle);
//        mRightFragment.setListener(this);
        fragmentTransaction.add(R.id.fl_content, mRightFragment);
        fragmentTransaction.commit();
    }


}
