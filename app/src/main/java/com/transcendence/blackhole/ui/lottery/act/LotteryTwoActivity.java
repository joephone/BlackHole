package com.transcendence.blackhole.ui.lottery.act;


import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.ui.lottery.view.LotteryViewTwo;

/**
 * @author aquarius
 * @date 17-4-11
 */

public class LotteryTwoActivity extends TitleBarActivity {

    private LotteryViewTwo lotteryView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_lottery_two;
    }

    @Override
    public void init() {
        setTitle("九宫格2");
        lotteryView = (LotteryViewTwo) findViewById(R.id.lotteryview);
    }



    @Override
    protected void onResume() {
        super.onResume();
    }
}
