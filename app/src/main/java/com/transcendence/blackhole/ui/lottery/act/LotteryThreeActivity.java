package com.transcendence.blackhole.ui.lottery.act;

import com.hjq.toast.ToastUtils;
import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.ui.lottery.view.luckyboard.LuckyAward;
import com.transcendence.blackhole.ui.lottery.view.luckyboard.LuckyBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cdc4512
 * @date 2018/3/30
 */

public class LotteryThreeActivity extends TitleBarActivity {

    LuckyBoard luckyBoard;

    @Override
    public int getLayoutId() {
        return R.layout.activity_lottery_three;
    }

    @Override
    public void init() {
        setTitle("九宫格3");
        luckyBoard = (LuckyBoard) findViewById(R.id.luckyboard);

        List<LuckyAward> awards = new ArrayList<>();
        awards.add(new LuckyAward("阿珂", "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=249354644,175269563&fm=27&gp=0.jpg", 0.1f));
        awards.add(new LuckyAward("百里守约", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2259891641,813905710&fm=27&gp=0.jpg", 0.1f));
        awards.add(new LuckyAward("曹操", "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2639560139,842609066&fm=27&gp=0.jpg", 0.1f));
        awards.add(new LuckyAward("黄忠", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=579192554,2336386543&fm=27&gp=0.jpg", 0.1f));
        awards.add(new LuckyAward("刘备", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1620262605,627001861&fm=27&gp=0.jpg", 0.1f));
        awards.add(new LuckyAward("夏侯惇", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2623316471,2443593485&fm=27&gp=0.jpg", 0.1f));
        awards.add(new LuckyAward("张飞", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2445078472,4134287578&fm=27&gp=0.jpg", 0.1f));
        awards.add(new LuckyAward("赵云", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=16058097,1380439742&fm=27&gp=0.jpg", 0.1f));
        luckyBoard.setAvAward(new LuckyAward("谢谢惠顾", "http://img3.imgtn.bdimg.com/it/u=2627630245,3498178596&fm=27&gp=0.jpg", 0f));
        luckyBoard.setAwards(awards);
        luckyBoard.setResultCallback(new LuckyBoard.ResultCallback() {
            @Override
            public void result(LuckyAward award) {
                ToastUtils.show(award.getName()+"freeline");
            }
        });
    }




}
