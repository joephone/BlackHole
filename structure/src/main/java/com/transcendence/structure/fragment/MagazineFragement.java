package com.transcendence.structure.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.transcendence.blackhole.base.mvp.BaseFragment;
import com.transcendence.blackhole.utils.Logs;
import com.transcendence.structure.R;
import com.transcendence.structure.magazine.adapter.MagazineAdapter;
import com.transcendence.structure.magazine.bean.MagazineEntity;
import com.wirelesspienetwork.overview.views.Overview;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2019/5/7 13:49
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class MagazineFragement extends BaseFragment implements Overview.RecentsViewCallbacks{
    private Overview mStackOverView;
    private MagazineAdapter adapter;
    private int page = 0;
    private boolean isLoad = true;
    private boolean isLast = false;
    private List<MagazineEntity.DataBean.ArticlesBean> articles = new ArrayList<>();
    private List<Integer> listRes = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.zuimei_fragment_magazine;
    }

    @Override
    protected void initView(View view) {
        mStackOverView = view.findViewById(R.id.stackView);
    }

    @Override
    protected void init() {
        Logs.logI( "init: ________________!!!!!!!!!!!!!!!!!!!!");
        listRes.add(R.mipmap.beauty01);
        listRes.add(R.mipmap.beauty02);
        listRes.add(R.mipmap.beauty03);
        listRes.add(R.mipmap.beauty04);
        listRes.add(R.mipmap.beauty05);
        listRes.add(R.mipmap.beauty06);
        //设置overView
        mStackOverView.setCallbacks(this); //添加手势回调
        loadData();
    }


    public void loadData() {
        page+=6;
//        String url = String.format(Constants.MAGAZINE_URL, page);
//        Log.d("print", "loadData: " + page);
//        new RetrofitUtil(getContext()).setDownListener(this).downJson(url, 0x001);
        MagazineEntity.DataBean.ArticlesBean entity = new MagazineEntity.DataBean.ArticlesBean();

        for (int i = 0; i < page; i++) {
            entity = new MagazineEntity.DataBean.ArticlesBean();
            int index = i%6;
            entity.setResId(listRes.get(index));
            entity.setAuthorName("老张"+index+"号");
            articles.add(entity);
        }
        adapter = new MagazineAdapter(mActivity,articles);
        mStackOverView.setTaskStack(adapter);

        isLoad = false;

    }

    @Override
    public void onCardDismissed(int position) {
        Logs.logI("onCardDismissed: " + position);
    }

    //卡片移除完时记载下一页
    @Override
    public void onAllCardsDismissed() {
        loadData();
    }

    //添加一个借口回调方法,滑到顶部时加载下一页
    @Override
    public void onScrollChanged(float f) {

    }

    @Override
    public void onItemClick(View view, int position) {
//        Intent intent = new Intent(getActivity(), MagazineInfoActivity.class);
//        intent.putExtra("id",articles.get(position).getId());
//        startActivity(intent);
//        //设置切换动画，从右边进入，左边退出
//        getActivity().overridePendingTransition(R.anim.slde_in, R.anim.slde_out);
    }
}
