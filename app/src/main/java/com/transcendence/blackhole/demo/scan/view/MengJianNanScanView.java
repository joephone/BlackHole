package com.transcendence.blackhole.demo.scan.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by mengjingnan on 2017/10/26.
 * 雷达扫描效果。
 */
public class MengJianNanScanView extends View {
    private static final int DEFAULT_WIDTH = 2000;
    private static final int DEFAULT_HEIGHT = 2000;
    private static final int DEFAULT_CIRCLE_RADIUS = 28;//雷达中间圆圈的半径大小
    private int radarRadius = 1000;//雷达扇面的半径大小
    private int defaultWidth;
    private int defaultHeight;
    private int start;
    private int centerX;
    private int centerY;
    private int circleColor = Color.parseColor("#008000");
    private int radarColor = Color.parseColor("#32CD32");
    private int tailColor = Color.parseColor("#00ffffff");
    private int centerColor = Color.parseColor("#ffffff");
    //彩虹，太丑了！
//    private int color1 = Color.parseColor("#FF0000");
//    private int color2 = Color.parseColor("#FF7F00");
//    private int color3 = Color.parseColor("#FF7F00");
//    private int color4 = Color.parseColor("#00FF00");
//    private int color5 = Color.parseColor("#00FF00");
//    private int color6 = Color.parseColor("#0000FF");
//    private int color7 = Color.parseColor("#8B00FF");
//    private float[] positions = {0, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f};
//    int[] colors = {tailColor, color7, color6, color5, color4, color3, color2, color1, tailColor };
    private float[] positions = {0, 0.3f, 1.0f};
    int[] colors = {tailColor, tailColor, radarColor};
    private Paint mPaintCircle;
    private Paint mPaintRadar;
    private Paint mPaintCenter;
    private Matrix matrix;

    private Handler handler = new Handler();
    private Runnable run = new Runnable() {
        @Override
        public void run() {
            start += 4.1;//一次旋转的度数,结合每次delay的时间，可以决定旋转的速度
            matrix = new Matrix();
            matrix.postRotate(start, centerX, centerY);
            postInvalidate();
            handler.postDelayed(run, 20);
        }
    };

    public MengJianNanScanView(Context context) {
        super(context);
        init(null, context);
    }

    public MengJianNanScanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, context);
    }

    public MengJianNanScanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        Shader shader = new SweepGradient(centerX, centerY, colors, positions);//设置渐变效果
        mPaintRadar.setShader(shader);
        //计算雷达圆环的半径，越小越好，因为在一些机型例如meizuMX5上会有问题
        radarRadius = (int) Math.sqrt(centerX * centerX + centerY * centerY);//勾股定理
        //由于半径是到画笔宽度的中点位置，所以半径和宽度要这么算，而且半径不能太小，不然没法画这么大
        radarRadius = (radarRadius + DEFAULT_CIRCLE_RADIUS) / 2;
        mPaintRadar.setStrokeWidth((radarRadius - DEFAULT_CIRCLE_RADIUS) * 2 + 1);
    }

    private void init(AttributeSet attrs, Context context) {
        initPaint();
        //得到当前屏幕的像素宽高

        defaultWidth = dip2px(context, DEFAULT_WIDTH);
        defaultHeight = dip2px(context, DEFAULT_HEIGHT);

        matrix = new Matrix();
    }

    private void initPaint() {
        mPaintCenter = new Paint();
        mPaintCenter.setColor(centerColor);
        mPaintCenter.setStyle(Paint.Style.FILL);
        mPaintCenter.setStrokeWidth(2);
        mPaintCenter.setAntiAlias(true);

        mPaintCircle = new Paint();
        mPaintCircle.setColor(circleColor);
        mPaintCircle.setAntiAlias(true);//抗锯齿
        mPaintCircle.setStyle(Paint.Style.STROKE);//设置实心
        mPaintCircle.setStrokeWidth(12);//画笔宽度

        mPaintRadar = new Paint();
        mPaintRadar.setColor(radarColor);

        mPaintRadar.setStyle(Paint.Style.STROKE);
        mPaintRadar.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int resultWidth;
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);

        if (modeWidth == MeasureSpec.EXACTLY) {
            resultWidth = sizeWidth;
        } else {
            resultWidth = defaultWidth;
            if (modeWidth == MeasureSpec.AT_MOST) {
                resultWidth = Math.min(resultWidth, sizeWidth);
            }
        }

        int resultHeight;
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (modeHeight == MeasureSpec.EXACTLY) {
            resultHeight = sizeHeight;
        } else {
            resultHeight = defaultHeight;
            if (modeHeight == MeasureSpec.AT_MOST) {
                resultHeight = Math.min(resultHeight, sizeHeight);
            }
        }
        setMeasuredDimension(resultWidth, resultHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(centerX, centerY, 17, mPaintCenter);//绘制中间的实心白色圆
        canvas.drawCircle(centerX, centerY, 22, mPaintCircle);//绘制红色小圆圈
        canvas.concat(matrix);//设置matrix
        canvas.drawCircle(centerX, centerY, radarRadius, mPaintRadar);//绘制扫描大圆环
    }

    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public void start() {
        if (handler != null)
            handler.post(run);
    }

    public void close() {
        handler.removeCallbacks(run);
    }

}
