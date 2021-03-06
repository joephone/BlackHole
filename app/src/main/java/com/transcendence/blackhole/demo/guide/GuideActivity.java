package com.transcendence.blackhole.demo.guide;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.global.Global;
import com.transcendence.blackhole.index.IndexActivity;

/**
 * @author Joephone on 2019/5/10 18:08
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class GuideActivity extends TitleBarActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private LinearLayout llContainer;
    private ImageView ivGuidePoint;
    private int mPaintDis;
    private TextView mTvStart;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    public void init() {
        setTitle(false);
        mViewPager = findViewById(R.id.vpGuide);
        mTvStart = findViewById(R.id.tvStart);
        ivGuidePoint = findViewById(R.id.ivGuidePoint);
        llContainer = findViewById(R.id.llContainer);
        mTvStart.setOnClickListener(this);
        mViewPager.setAdapter(new GuideAdapter());

        for (int i = 0; i < Global.mBeautyIds.length; i++) {
            //创建ImageView把mImgaeViewIds放进去
//            ImageView view = new ImageView(this);
//            view.setBackgroundResource(mImageIds[i]);
//            //添加到ImageView的集合中
//            mImageViewList.add(view);


            //小圆点    一个小灰点是一个ImageView
            ImageView pointView = new ImageView(this);
            pointView.setImageResource(R.drawable.guide_gray_cycle_shape);
            //初始化布局参数，父控件是谁，就初始化谁的布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                //当添加的小圆点的个数超过一个的时候就设置当前小圆点的左边距为10dp;
                params.leftMargin = 18;
            }
            //设置小灰点的宽高包裹内容
            pointView.setLayoutParams(params);
            //将小灰点添加到LinearLayout中
            llContainer.addView(pointView);
        }


        //监听布局是否已经完成  布局的位置是否已经确定
        ivGuidePoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //避免重复回调        出于兼容性考虑，使用了过时的方法    removeGlobalOnLayoutListener   新方法是 removeOnGlobalLayoutListener
                ivGuidePoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //布局完成了就获取第一个小灰点和第二个之间left的距离
                mPaintDis = llContainer.getChildAt(1).getLeft() - llContainer.getChildAt(0).getLeft();
                System.out.println("距离：" + mPaintDis);
            }
        });


        //ViewPager滑动Pager监听  setOnPageChangeListener
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动过程中的回调
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //当滑到第二个Pager的时候，positionOffset百分比会变成0，position会变成1，所以后面要加上position*mPaintDis
                int letfMargin = (int) (mPaintDis * positionOffset) + position * mPaintDis;
                //在父布局控件中设置他的leftMargin边距
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivGuidePoint.getLayoutParams();
                params.leftMargin = letfMargin;
                ivGuidePoint.setLayoutParams(params);
            }


            @Override
            public void onPageSelected(int position) {
                System.out.println("position:" + position);
                if (position == Global.mBeautyIds.length - 1) {
                    mTvStart.setVisibility(View.VISIBLE);
                } else {
                    mTvStart.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("state:" + state);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvStart:
                gotoLoginActivity();
                break;
        }
    }

    private void gotoLoginActivity() {
        Intent intent = new Intent(GuideActivity.this, IndexActivity.class);
        startActivity(intent);
        finish();
    }


    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Global.mBeautyIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //初始化item布局
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = new ImageView(GuideActivity.this);
            view.setBackgroundResource(Global.mBeautyIds[position]);
            container.addView(view);
            return view;
        }

        //销毁item
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
