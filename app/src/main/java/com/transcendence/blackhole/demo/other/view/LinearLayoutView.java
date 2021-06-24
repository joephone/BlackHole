package com.transcendence.blackhole.demo.other.view;


import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.transcendence.core.utils.L;

public class LinearLayoutView extends LinearLayout{

	public static final int KEYBORAD_HIDE = 0;
	public static final int KEYBORAD_SHOW = 1;
	private static final int SOFTKEYPAD_MIN_HEIGHT = 50;
	
	private Handler uiHandler = new Handler();
	
	public LinearLayoutView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public LinearLayoutView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onSizeChanged(int w,final int h, int oldw,final int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		uiHandler.post(new Runnable() {
			@Override
			public void run() {
				if (oldh - h > SOFTKEYPAD_MIN_HEIGHT){		
					L.d("up弹起");
					keyBordStateListener.stateChange(KEYBORAD_SHOW);
				}
				else {
					L.d( "down隐藏");
					if(keyBordStateListener != null){
						keyBordStateListener.stateChange(KEYBORAD_HIDE);
					}
				}
			}
		});
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	private KeyBordStateListener keyBordStateListener;
	
	public void setKeyBordStateListener(KeyBordStateListener keyBordStateListener) {
		this.keyBordStateListener = keyBordStateListener;
	}

	public interface KeyBordStateListener{		
		void stateChange(int state);
	}
	
}
