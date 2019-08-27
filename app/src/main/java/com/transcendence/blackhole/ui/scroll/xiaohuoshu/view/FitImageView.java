package com.transcendence.blackhole.ui.scroll.xiaohuoshu.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 图片宽度充满屏幕、高度按原始比例自适应
 * @author forvv231
 */


@SuppressLint("AppCompatCustomView")
public class FitImageView extends ImageView {

    public FitImageView(Context context) {
        super(context);
    }

    public FitImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 父视图问子视图你想要多大空间
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        Drawable drawable = getDrawable();
        if(drawable!=null){
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) Math.ceil((float) width * (float) drawable.getIntrinsicHeight() / (float) drawable.getIntrinsicWidth());
            //子视图告诉父视图具体大小
            setMeasuredDimension(width, height);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * onMeasure 作用   测量; 度量;
     （1）一般情况重写onMeasure()方法作用是为了自定义View尺寸的规则，如果你的自定义View的尺寸是根据父控件行为一致，就不需要重写onMeasure()方法
     （2）如果不重写onMeasure方法，那么自定义view的尺寸默认就和父控件一样大小，当然也可以在布局文件里面写死宽高，而重写该方法可以根据自己的需求设置自定义view大小
     */

    /**
     * 认识 onMeasure
     （0）onMeasure (int widthMeasureSpec, int heightMeasureSpec）是view自己的方法
     （1）onMeasure 方法简单的理解就是是用于测量视图的大小，主要是用来测量自己和内容的来确定宽度和高度
     （2）onMeasure有两个参数 （ int widthMeasureSpec, int heightMeasureSpec），该参数表示控件可获得的空间以及关于这个空间描述的元数据.
     （3）widthMeasureSpec和heightMeasureSpec这两个值通常情况下都是由父视图经过计算后传递给子视图的，说明父视图会在一定程度上决定子视图的大小。
     */


    /**
     * 认识 MeasureSpec
     * 在测量自定义view的大小之前，我们需要认识一个类MeasureSpec，它封装了父布局传递给子布局的布局要求，每个MeasureSpec代表了一组宽度和高度的要求  MeasureSpec由size和mode组成。
     * specMode一共有三种类型，如下所示：
     * 1. EXACTLY
     * 表示父视图希望子视图的大小应该是由specSize的值来决定的，系统默认会按照这个规则来设置子视图的大小，简单的说（当设置width或height为match_parent时，模式为EXACTLY，因为子view会占据剩余容器的空间，所以它大小是确定的）
     * 2. AT_MOST
     * 表示子视图最多只能是specSize中指定的大小。（当设置为wrap_content时，模式为AT_MOST, 表示子view的大小最多是多少，这样子view会根据这个上限来设置自己的尺寸）
     * 3. UNSPECIFIED
     * 表示开发人员可以将视图按照自己的意愿设置成任意的大小，没有任何限制。这种情况比较少见，不太会用到。
     */

}
