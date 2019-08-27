package com.transcendence.blackhole.demo.lottery.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.transcendence.blackhole.demo.lottery.bean.Prize;
import com.transcendence.blackhole.utils.L;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 奖品1
 * @author harry
 */

public class LotteryViewOne extends SurfaceView implements Callback {

	/**
	 * holder
	 */
	private SurfaceHolder mHolder;


	private List<Prize> prizes;
	//抽奖开关
	private boolean flags;

	private int lottery=6;   //设置中奖号码

	private int current=2;   //抽奖开始的位置

	private int count=0;   //旋转次数累计

	private int countDown;    //倒计次数，快速旋转完成后，需要倒计多少次循环才停止

	//旋转方块的颜色
	private int transfer= 0x70ffff00;

	private int MAX=50;   //最大旋转次数

	private OnTransferWinningListener listener;

	ExecutorService service;

	public LotteryViewOne(Context context, AttributeSet attrs) {
		super(context, attrs);
		//SurfaceView的SurfaceHolder是已经实现好的实例
		mHolder = this.getHolder();
		mHolder.addCallback(this);
	}

	public LotteryViewOne(Context context) {
		this(context,null);
	}
	
	public void setOnTransferWinningListener(OnTransferWinningListener listener){
		this.listener=listener;
	}
	
	public interface OnTransferWinningListener{
		/**
		 * 中奖回调
		 * @param position
		 */
		void onWinning(int position);
	}
	
	
	/**
	 * 设置中奖号码
	 * @param lottery
     */
	public void setLottery(int lottery) {
		if(prizes!=null&& Math.round(prizes.size()/2)==0){
			throw new RuntimeException("开始抽奖按钮不能设置为中奖位置！");
		}
		this.lottery = lottery;
	}

	/**
	 * 设置转盘颜色
	 * @param transfer
     */
	public void setTransfer(int transfer) {
		this.transfer = transfer;
	}

	/**
	 * 设置奖品集合
	 * @param prizes
     */
	public void setPrizes(List<Prize> prizes){
		this.prizes=prizes;
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		handleTouch(event);
		return super.onTouchEvent(event);
	}

	/**
	 * 触摸
	 * @param event
	 */
	public void handleTouch(MotionEvent event) {
		Point touchPoint=new Point((int)event.getX()-getLeft(),(int)event.getY());
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			Prize prize = prizes.get(Math.round(prizes.size())/2);
			if(prize.isClick(touchPoint)){
				if(!flags){
					setStartFlags(true);
					prize.click();
				}
			}
			break ;
		default:
			break ;
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
	}
	//surfaceView的生命周期
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Canvas canvas=null;
		//新建缓存线程池来执行绘制任务
		service = Executors.newCachedThreadPool();
		try {
			canvas = mHolder.lockCanvas();
			drawBg(canvas);
			drawPrize(canvas);

			Prize prize = prizes.get(Math.round(prizes.size()/2));
			//中间按钮点击
			prize.setListener(new Prize.OnClickListener() {
				@Override
				public void onClick() {
					start();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(canvas!=null) {
				mHolder.unlockCanvasAndPost(canvas);}
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		setStartFlags(false);
	}
	public void start() {
		//这一步从网上获取
		setLottery(getRandom());
		//获取线程池，并提交任务（newCachedThreadPool（）有复用线程功能），当有新任务时，如果有空闲线程在，则让空闲线程执行任务。当超过60s没任务时，则该线程终止，
		//因此线程池处于空闲状态，将不消耗资源
		service.execute(new SurfaceRunnable());
	}

	private class SurfaceRunnable implements Runnable {
		@Override
		public void run() {
			//当flags为false时，该任务执行完毕
			while(flags){
				Canvas canvas = null;
				try {
					canvas = mHolder.lockCanvas();

					drawBg(canvas);

					drawTransfer(canvas);

					drawPrize(canvas);
					
					controllerTransfer();

				} catch (Exception e) {
					e.printStackTrace();
				}finally{

					if(canvas!=null) {
						mHolder.unlockCanvasAndPost(canvas);    //保证每次都将绘图的内容提交
					}
				}
			}
			L.d("ThreadName="+ Thread.currentThread().getId());
		}
	}

	//绘制背景
	private void drawBg(Canvas canvas) {
		canvas.drawColor(Color.WHITE, Mode.CLEAR);
		int width = getMeasuredWidth()/3;
		int x1=0;
		int y1=0;
		
		int x2=0;
		int y2=0;
		
		int len = (int) Math.sqrt(prizes.size());
		
		for(int x=0;x<len*len;x++){
			
			Prize prize = prizes.get(x);
			
			int index=x;
			x1=getPaddingLeft()+width*(Math.abs(index)%len);
			y1=getPaddingTop()+width*(index/len);

			x2=x1+width-getPaddingLeft();
			y2=y1+width-getPaddingTop();
			Rect rect=new Rect(x1,y1,x2,y2);
			
			Paint paint=new Paint();
			paint.setColor(prize.getBgColor());
			canvas.drawRect(rect, paint);
		}
	}
	//绘制奖品
	private void drawPrize(Canvas canvas) {
		int width = getMeasuredWidth()/3;
		int x1=0;
		int y1=0;

		int x2=0;
		int y2=0;

		int len = (int) Math.sqrt(prizes.size());

		for(int x=0;x<len*len;x++){

			Prize prize = prizes.get(x);

			int index=x;
			x1=getPaddingLeft()+width*(Math.abs(index)%len);
			y1=getPaddingTop()+width*(index/len);

			x2=x1+width;
			y2=y1+width;
			Rect rect=new Rect(x1+width/6,y1+width/6,x2-width/6,y2-width/6);
			prize.setRect(rect);
			Paint paint = new Paint();
//			paint.setColor(0xaac0c0c0);
			canvas.drawBitmap(prize.getIcon(), null, rect, paint);

		}
	}


	//绘制旋转的方块
	private void drawTransfer(Canvas canvas) {
		int width = getMeasuredWidth()/3;
		int x1;
		int y1;
		
		int x2;
		int y2;
		int len = (int) Math.sqrt(prizes.size());
		current=next(current, len);
		x1=getPaddingLeft()+width*(Math.abs(current)%len);
		y1=getPaddingTop()+width*((current)/len);
		
		x2=x1+width-getPaddingLeft();
		y2=y1+width-getPaddingTop();

		Rect rect=new Rect(x1,y1,x2,y2);
		Paint paint=new Paint();
		paint.setColor(transfer);
		canvas.drawRect(rect, paint);
	}

	//控制旋转
	private void controllerTransfer() {
		//旋转次数已达标，可以停止旋转了
		if(count>MAX){
			countDown++;
			SystemClock.sleep(count*5);
		}else{
			//因为count不断加大，所以旋转越来越慢
			SystemClock.sleep(count*2);
		}
		
		count++;
		//可以通过控制countDown来停止surfaceView的绘制
		if(countDown>2){
			//current的位置为抽中的lottery时，让子线程停止绘制
			if(lottery==current){
				countDown=0;
				count=0;
				//控制绘制开关
				setStartFlags(false);
				
				if(listener!=null){
					//切换到主线程中运行
					post(new Runnable() {
						
						@Override
						public void run() {
							listener.onWinning(current);
						}
					});
					
				}
			}
		}
	}
	
	public void setStartFlags(boolean flags){
		this.flags=flags;
	}
	
	//获取随机中奖数，实际开发中一般中奖号码是服务器告诉我们的
	private int getRandom(){
		Random r=new Random();
		int nextInt =r.nextInt(prizes.size());
		if(nextInt%(Math.round(prizes.size()/2))==0){
			//随机号码等于中间开始位置，需要继续摇随机号
			return getRandom();
		}
		return nextInt;
	}

	//旋转下一个的位置
	public int next(int position,int len){
		int current=position;
		if(current+1<len){
			return ++current;
		}
		
		if((current+1)%len==0&&current<len*len-1){
			return current+=len;
		}
		
		if(current%len==0){
			return current-=len;
		}
		
		if(current<len*len){
			return --current;
		}
		
		return current;
	}
	
	



	/** 
     * 重新测量
     */  
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)  
    {  
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);  
        int width = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(width, width);  
    }
}
