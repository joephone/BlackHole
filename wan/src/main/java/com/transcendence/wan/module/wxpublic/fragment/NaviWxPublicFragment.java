package com.transcendence.wan.module.wxpublic.fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.transcendence.core.utils.L;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.module.wxpublic.adapter.MultiFragmentPageAdapter;
import com.transcendence.wan.module.wxpublic.model.WxChapterBean;
import com.transcendence.wan.module.wxpublic.presenter.NaviWxPublicPresenter;
import com.transcendence.wan.module.wxpublic.view.NaviWxPublicView;
import com.transcendence.wan.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2020/4/18 15:47
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class NaviWxPublicFragment extends WanBaseFragment<NaviWxPublicPresenter> implements NaviWxPublicView {

    private ViewPager mVp;
    private TabLayout mTabLayout;
    private MultiFragmentPageAdapter mAdapter;
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();


    public static NaviWxPublicFragment create() {
        return new NaviWxPublicFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_wx_public_account_head;
    }

    @Override
    protected NaviWxPublicPresenter initPresenter() {
        return new NaviWxPublicPresenter();
    }


    @Override
    protected void initView() {
//        L.d("NaviWxPublicFragment onCreateView");
        mVp = findViewById(R.id.vp);
        mTabLayout = findViewById(R.id.tab_layout);
        mAdapter = new MultiFragmentPageAdapter(getChildFragmentManager());
    }

    @Override
    protected void loadData() {
        presenter.getWxPublicList();
    }


    @Override
    public void getWxChapterSuc(int code, List<WxChapterBean> list) {
        L.d("getWxChapterSuc");
        int length = list.size() >= 16 ? 16 : list.size();
        for (int i = 0; i < length; i++) {
            mTitleList.add(list.get(i).getName());
            mFragmentList.add(WxArticleListFragment.newInstance(list.get(i)));
        }

        mTabLayout.setupWithViewPager(mVp);
//        mTabLayout.setTabsFromPagerAdapter(mAdapter);   calling setupWithViewPager is enough.
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                AppUtils.setIndicator(mTabLayout, 20, 20);
            }
        });
        mAdapter.setTitles(mTitleList);
        mAdapter.setFragments(mFragmentList);
        mVp.setAdapter(mAdapter);

    }

    @Override
    public void getWxChapterFail(int code) {

    }


}
