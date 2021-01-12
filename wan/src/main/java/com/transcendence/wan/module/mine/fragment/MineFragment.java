package com.transcendence.wan.module.mine.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
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

public class MineFragment extends WanBaseFragment<MinePresenter> implements View.OnClickListener,MineView {
    private static final String ARG_SHOW_TEXT = "text";
    private ImageView ivRight;


//    private RelativeLayout rlUserInfo;
//    private LinearLayout llCoin,llPpen,ll_setting;
    private LinearLayout ll;
    private FrameLayout flRight;

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
//        llPpen = findViewById(R.id.ll_open);
//        llPpen.setOnClickListener(this);
//
//        ll_setting = findViewById(R.id.ll_setting);
//        ll_setting.setOnClickListener(this);

//        flRight = findViewById(R.id.fl_right);
//        flRight.setOnClickListener(this);
//        nsv = findViewById(R.id.nsv);
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
            case R.id.ll_open:
                AboutMeActivity.start(getContext());
                break;
//            case R.id.ll_about:
//                AboutMeActivity.start(getContext());
//                break;
            case R.id.ll_setting:
                SettingActivity.start(getContext());
                break;

        }
    }

    @Override
    public void getMyCoinSuc(int code, MyCoinBean bean) {

    }
}
