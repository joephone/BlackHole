package com.transcendence.blackhole.ui.lottery.act;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;

import com.hjq.toast.ToastUtils;
import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.ui.lottery.bean.Prize;
import com.transcendence.blackhole.ui.lottery.view.LotteryViewOne;

import java.util.ArrayList;
import java.util.List;

public class LotteryOneActivity extends TitleBarActivity {
	
	LotteryViewOne nl;


	@Override
	public int getLayoutId() {
		return R.layout.activity_lottery_one_home;
	}

	@Override
	public void init() {
		nl=(LotteryViewOne) findViewById(R.id.nl);
		// 这句不能少
		nl.setZOrderOnTop(true);
		nl.getHolder().setFormat(PixelFormat.TRANSPARENT);

		int[]prizesIcon={
				R.mipmap.lottery_one_danfan,
				R.mipmap.lottery_one_meizi,
				R.mipmap.lottery_one_iphone,
				R.mipmap.lottery_one_f015,
				R.mipmap.lottery_one_arrow,
				R.mipmap.lottery_one_f040,
				R.mipmap.lottery_one_ipad,
				R.mipmap.lottery_one_spree_icon,
				R.mipmap.lottery_one_spree_success_icon};
		final List<Prize> prizes=new ArrayList<>();
		for(int x=0;x<9;x++){
			Prize lottery=new Prize();
			lottery.setId(x+1);
			lottery.setName("Lottery"+(x+1));
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), prizesIcon[x]);
			lottery.setIcon(bitmap);
			if((x+1)%2==0){
				lottery.setBgColor(0xffffcccc);
			}else if(x==4){
				lottery.setBgColor(0xffffffff);
			}else{
				lottery.setBgColor(0xffffcccc);
			}

			prizes.add(lottery);
		}
		nl.setPrizes(prizes);
		nl.setOnTransferWinningListener(new LotteryViewOne.OnTransferWinningListener() {

			@Override
			public void onWinning(int position) {
				ToastUtils.show("抽中了第"+prizes.get(position).getId()+"个奖品");
			}
		});
	}
}
