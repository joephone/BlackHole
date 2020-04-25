package com.transcendence.wan.module.mine.act;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.transcendence.wan.R;
import com.transcendence.wan.base.act.WanBaseActivity;
import com.transcendence.wan.module.mine.presenter.MyCoinPresenter;
import com.transcendence.wan.module.mine.view.MyCoinView;
import com.transcendence.wan.utils.AnimatorUtils;

/**
 * @Author Joephone on 2020/4/25 14:41
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MyCoinActivity extends WanBaseActivity<MyCoinPresenter> implements MyCoinView {

    private static int PAGE = 1;
    private TextView tvCoin;

    public static void start(Context context) {
        Intent intent = new Intent(context, MyCoinActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_my_rank;
    }

    @Nullable
    @Override
    protected MyCoinPresenter initPresenter() {
        return new MyCoinPresenter();
    }

    @Override
    protected void initView() {
        tvCoin = findViewById(R.id.tv_coin);
        AnimatorUtils.doIntAnim(tvCoin, 1000, 1000);

    }

    @Override
    protected void loadData() {
        presenter.getCoin();
        presenter.getMyCoinList(PAGE);
    }
}
