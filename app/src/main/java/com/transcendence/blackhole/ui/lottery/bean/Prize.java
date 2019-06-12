package com.transcendence.blackhole.ui.lottery.bean;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * 奖品
 * @author harry
 *
 */
public class Prize {

	private int id;  //奖品id
	private String name;  //奖品名称
	private Bitmap icon;   //奖品
	private Rect rect;   //奖品摆放的位置
	private int bgColor;   //方块背景颜色
	
	
	public int getBgColor() {
		return bgColor;
	}

	public void setBgColor(int bgColor) {
		this.bgColor = bgColor;
	}
	private OnClickListener listener;
	public interface OnClickListener{
		void onClick();
	}
	
	public boolean isClick(Point point){
		//如果点击的位置是点击按钮覆盖的范围，则返回true，否则返回false
		return rect.contains(point.x,point.y);
	}
	
	public void setListener(OnClickListener listener) {
		this.listener = listener;
	}
	
	
	public void click(){
		if(listener!=null){
			listener.onClick();
		}
	}
	public Rect getRect() {
		return rect;
	}
	public void setRect(Rect rect) {
		this.rect = rect;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Bitmap getIcon() {
		return icon;
	}
	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}
	
	
}
