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

public class FiveFragment extends WanBaseFragment<MinePresenter> implements View.OnClickListener,MineView {
    private static final String ARG_SHOW_TEXT = "text";


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_five;
    }

    @Override
    protected MinePresenter initPresenter() {
        return new MinePresenter();
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void loadData() {

    }


    private void init() {

    }


    public static FiveFragment newInstance(String title) {
        FiveFragment fragment = new FiveFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void getMyCoinSuc(int code, MyCoinBean bean) {

    }
}
