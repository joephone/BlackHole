package com.transcendence.blackhole.demo.other.act;

import android.view.View;
import android.widget.LinearLayout;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.blackhole.demo.other.view.LinearLayoutView;

/**
 * @author Joephone
 */
public class OverKeyBoardActivity extends TitleBarActivity implements LinearLayoutView.KeyBordStateListener {

	private LinearLayoutView resizeLayout;
	private LinearLayout logoLayout;


	@Override
	public int getLayoutId() {
		return R.layout.activity_other_overkeyboard;
	}

	@Override
	public void init() {
		resizeLayout = (LinearLayoutView) findViewById(R.id.login_root_layout);
		logoLayout = (LinearLayout) findViewById(R.id.login_layout_logo);
		resizeLayout.setKeyBordStateListener(this);
	}

	@Override
	public void stateChange(int state) {
		// TODO Auto-generated method stub
		switch (state) {
		case LinearLayoutView.KEYBORAD_HIDE:
			logoLayout.setVisibility(View.VISIBLE);
			break;
		case LinearLayoutView.KEYBORAD_SHOW:
			logoLayout.setVisibility(View.GONE);
			break;
		}
	}

}
