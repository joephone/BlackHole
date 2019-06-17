package com.transcendence.blackhole.widget.custom.autoscroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @author Jerome
 */
public class MarqueeText extends AppCompatTextView implements Runnable {
	private int currentScrollX;// 当前滚动的位置
	private boolean isStop = false;
	private int textWidth;
	private boolean isMeasure = false;

	public MarqueeText(Context context) {
		super(context);
	}

	public MarqueeText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MarqueeText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (!isMeasure) {
			getTextWidth();
			isMeasure = true;
		}
	}

	private void getTextWidth() {
		Paint paint = this.getPaint();
		String str = this.getText().toString();
		textWidth = (int) paint.measureText(str);
	}

	@Override
	public void setText(CharSequence text, BufferType type) {
		super.setText(text, type);
		this.isMeasure = false;
	}

	@Override
	public void run() {
		currentScrollX += 1;// 滚动速度
		scrollTo(currentScrollX, 0);
		if (isStop) {
			return;
		}
		if (getScrollX() >= textWidth) {
			scrollTo(-this.getWidth(), 0);
			currentScrollX = -this.getWidth();
		}
		postDelayed(this, 10);
	}

	public void startScroll() {
		isStop = false;
		this.removeCallbacks(this);
		post(this);
	}

	public void stopScroll() {
		currentScrollX = 0;
		isStop = true;
	}

	public void startFor0() {
		currentScrollX = 0;
		startScroll();
	}
}
