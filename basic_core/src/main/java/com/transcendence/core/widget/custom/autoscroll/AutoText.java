package com.transcendence.core.widget.custom.autoscroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author Jerome
 */
public class AutoText extends TextView implements Runnable {
	private int currentScrollX;// ��ǰ������λ��
	private boolean isStop = false;
	private int textWidth;
	private boolean isMeasure = false;

	public AutoText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public AutoText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AutoText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if (!isMeasure) {// ���ֿ��ֻ���ȡһ�ξͿ�����
			getTextWidth();
			isMeasure = true;
		}
	}

	/**
	 * ��ȡ���ֿ��
	 */
	private void getTextWidth() {
		Paint paint = this.getPaint();
		String str = this.getText().toString();
		textWidth = (int) paint.measureText(str);
	}

	// ��дsetText ��setText��ʱ�����¼���text�Ŀ��
	@Override
	public void setText(CharSequence text, BufferType type) {
		// TODO Auto-generated method stub
		super.setText(text, type);
		this.isMeasure = false;
	}

	@Override
	public void run() {
		currentScrollX -= 2;// �����ٶ�
		scrollTo(currentScrollX, 0);
		if (isStop) {
			return;
		}
		if (getScrollX() <= -(this.getWidth())) {
			scrollTo(textWidth, 0);
			currentScrollX = textWidth;
			// return;
		}
		postDelayed(this, 5);
	}

	// ��ʼ����
	public void startScroll() {
		isStop = false;
		this.removeCallbacks(this);
		post(this);
	}

	// ֹͣ����
	public void stopScroll() {
		isStop = true;
		// textWidth=currentScrollX; //��ʱֹͣ
	}

	// ��ͷ��ʼ����
	public void startFor0() {
		currentScrollX = 0;
		startScroll();
	}
}
