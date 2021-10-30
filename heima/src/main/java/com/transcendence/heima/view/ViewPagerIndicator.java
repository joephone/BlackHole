package com.transcendence.heima.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.transcendence.heima.R;

/**
 * @Author Joephone on 2021/9/16 0016 下午 5:37
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class ViewPagerIndicator extends ViewPager {

    private int mCount; //小圆点的个数
    private int mIndex; //当前小圆点的位置
    private Context context;
    public ViewPagerIndicator(Context context) {
        this(context, null);
    }
    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
    /**
     * 设置滑动到当前小圆点时其他圆点的位置
     */
    public void setCurrentPosition(int currentIndex) {
        mIndex = currentIndex; //当前小圆点
        this.removeAllViews(); //移除界面上存在的view
        int pex = context.getResources().getDimensionPixelSize(
                R.dimen.view_indicator_padding);
        for (int i = 0; i < this.mCount; i++) {
            //创建一个ImageView控件来放置小圆点
            ImageView imageView = new ImageView(context);
            if (mIndex == i) { //滑动到的当前界面
                //设置小圆点的图片为白色图片
                imageView.setImageResource(R.drawable.indicator_on);
            }else {
                //设置小圆点的图片为灰色图片
                imageView.setImageResource(R.drawable.indicator_off);
            }
            imageView.setPadding(pex, 0, pex, 0); //设置小圆点图片的上下左右的padding
            this.addView(imageView); //把此小圆点添加到自定义的ViewPagerIndicator控件上
        }
    }
    /**
     * 设置小圆点的数目
     */
    public void setCount(int count) {
        this.mCount = count;
    }
}
