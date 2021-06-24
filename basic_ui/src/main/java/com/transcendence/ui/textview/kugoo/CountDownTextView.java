package com.transcendence.ui.textview.kugoo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @Author Joephone on 2020/5/9 2:12
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class CountDownTextView extends AppCompatTextView {
    private Context mContext;
    // 倒计时动画时间
    private int duration = 5000;
    // 动画扫过的角度
    private int mSweepAngle = 360;
    // 属性动画
    private ValueAnimator animator;
    // 矩形用来保存位置大小信息
    private final RectF mRect = new RectF();
    // 圆弧的画笔
    private Paint mBackgroundPaint;

    public CountDownTextView(Context context) {
        this(context,null);
    }

    public CountDownTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CountDownTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    void init(){
        // 设置画笔平滑
        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 设置画笔颜色
        mBackgroundPaint.setColor(Color.WHITE);
        // 设置画笔边框宽度
        mBackgroundPaint.setStrokeWidth(dp2px(mContext,2f));
        // 设置画笔样式为边框类型
        mBackgroundPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int padding = dp2px(mContext,4f);
        mRect.top = padding;
        mRect.left = padding;
        mRect.right = getWidth() - padding;
        mRect.bottom = getHeight() - padding;

        // 画倒计时线内圆
        canvas.drawArc(mRect, //弧线所使用的矩形区域大小
                -90,  //开始角度
                mSweepAngle, //扫过的角度
                false, //是否使用中心
                mBackgroundPaint); // 设置画笔

        super.onDraw(canvas);
    }

    /**
     * 开始倒计时
     */
    public void start() {
        // 在动画中
        if (mSweepAngle != 360) return;
        //  初始化属性动画
        animator = ValueAnimator.ofInt(mSweepAngle).setDuration(duration);
        // 设置插值
        animator.setInterpolator(new LinearInterpolator());
        // 设置动画监听
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 获取属性动画返回的动画值
                mSweepAngle = (int) animation.getAnimatedValue();
                // 重绘自己
                invalidate();
            }
        });
        // 开始动画
        animator.start();
    }

    public void stop() {
        animator.cancel();
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    private int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * 设置持续时间
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
