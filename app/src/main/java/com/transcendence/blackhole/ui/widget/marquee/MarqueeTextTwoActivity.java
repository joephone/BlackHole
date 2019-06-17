package com.transcendence.blackhole.ui.widget.marquee;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.widget.custom.autoscroll.MarqueTextViewTwo;

public class MarqueeTextTwoActivity extends TitleBarActivity {

	private MarqueTextViewTwo mMarque;

	@Override
	public int getLayoutId() {
		return R.layout.activity_widget_marquee_two;
	}

	@Override
	public void init() {
		setTitle("MarqueeTextTwo");
		mMarque = (MarqueTextViewTwo) findViewById(R.id.tv);
		mMarque.setText("                                                                                  "+"这个可以滚动的吧这个可以滚动的吧这个可以滚动的吧这个可以滚动的吧这个可以滚动的吧这个可以滚动的吧这个这个可以滚动的吧a");

	}


}
