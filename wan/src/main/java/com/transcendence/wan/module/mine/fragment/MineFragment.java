package com.transcendence.wan.module.mine.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.transcendence.blackhole.arouter.ARouterController;
import com.transcendence.blackhole.arouter.ARouterUtils;
import com.transcendence.blackhole.global.Global;
import com.transcendence.blackhole.utils.L;
import com.transcendence.ui.scroll.HeaderZoomLayout;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.module.main.act.WanWebActivity;
import com.transcendence.wan.module.mine.act.AboutMeActivity;
import com.transcendence.wan.module.mine.act.MyCoinActivity;
import com.transcendence.wan.module.mine.act.RankActivity;
import com.transcendence.wan.module.mine.model.MyCoinBean;
import com.transcendence.wan.module.mine.presenter.MinePresenter;
import com.transcendence.wan.module.mine.view.MineView;
import com.transcendence.wan.module.setting.act.SettingActivity;
import com.transcendence.wan.utils.UserUtils;

/**
 * @author Joephone on 2019/12/17 14:41
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MineFragment extends WanBaseFragment<MinePresenter> implements View.OnClickListener,MineView, HeaderZoomLayout.OnScrollListener {
    private static final String ARG_SHOW_TEXT = "text";
    private ImageView ivRight;


//    private RelativeLayout rlUserInfo;
    private LinearLayout llCoin,llPpen,ll_setting,llAboutAuthor,llReadLater;
    private LinearLayout ll;
    private FrameLayout flRight;
    private HeaderZoomLayout mScroll;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_mine;
    }

    @Override
    protected MinePresenter initPresenter() {
        return new MinePresenter();
    }

    @Override
    protected void initView() {
//        ll.findViewById(R.id.ll_about_author);
//        ll.setOnClickListener(this);

//        ivRight = findViewById(R.id.ivRight);
//        rlUserInfo = findViewById(R.id.rlUserInfo);
//        rlUserInfo.setOnClickListener(this);
//        llCoin = findViewById(R.id.ll_coin);
//        llCoin.setOnClickListener(this);
//
        llPpen = findViewById(R.id.ll_open_project);
        llPpen.setOnClickListener(this);
        llReadLater = findViewById(R.id.ll_read_later);
        llReadLater.setOnClickListener(this);

//
//        ll_setting = findViewById(R.id.ll_setting);
//        ll_setting.setOnClickListener(this);

//        flRight = findViewById(R.id.fl_right);
//        flRight.setOnClickListener(this);
//        nsv = findViewById(R.id.nsv);
        llAboutAuthor = findViewById(R.id.ll_about_author);
        llAboutAuthor.setOnClickListener(this);
        mScroll = findViewById(R.id.scrollView);
        mScroll.setOnScrollListener(new HeaderZoomLayout.OnScrollListener() {
            @Override
            public void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                L.d("onScroll");
            }
        });
        init();
    }

    @Override
    protected void loadData() {

    }

    private void init() {
//        ivRight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RankActivity.start(getContext());
//            }
//        });
//        nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//
//            @Override
//            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                setIvBlurHeight(oldScrollY);
//            }
//        });
    }

    private void setIvBlurHeight(int height) {

    }


    public static MineFragment newInstance(String title) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fl_right:
                RankActivity.start(getContext());
                break;
            case R.id.rlUserInfo:
                if(UserUtils.getInstance().toDoIfLogin(getContext())){
//                SettingActivity.start(getActivity());
                }
                break;
            case R.id.ll_coin:
                if(UserUtils.getInstance().toDoIfLogin(getContext())){
                    MyCoinActivity.start(getContext());
                }
                break;
            case R.id.ll_read_later:
                ARouterUtils.navigation(ARouterController.APP_MAIN);
                break;
            case R.id.ll_open_project:
                WanWebActivity.start(getContext(), Global.GITHUB_AUTHOR_MAIN_PROJECT);
                break;
            case R.id.ll_about_author:
                AboutMeActivity.start(getContext());  // ARouterUtils.navigation(AppConstantValue.mainIndex[2]);
                break;
            case R.id.ll_setting:
                SettingActivity.start(getContext());
                break;

        }
    }

    @Override
    public void getMyCoinSuc(int code, MyCoinBean bean) {

    }

    @Override
    public void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        L.d("onScroll");
        if(Math.abs(scrollY - oldScrollY)>50){
            L.d("此处调用刷新");
        }
    }
}
