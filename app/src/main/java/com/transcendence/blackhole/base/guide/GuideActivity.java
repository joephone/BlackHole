package com.transcendence.blackhole.base.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.transcendence.blackhole.R;

/**
 * @author Joephone on 2019/5/10 18:08
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class GuideActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mViewPager;
    private int[] mImageIds = new int[]{R.mipmap.beauty06,R.mipmap.beauty04,R.mipmap.beauty01};
    private LinearLayout llContainer;
    private ImageView ivGuidePoint;
    private int mPaintDis;
    private Button btnStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);


        init();
    }

    private void init() {
        mViewPager = findViewById(R.id.vpGuide);
        btnStart = findViewById(R.id.btnStart);
        ivGuidePoint = findViewById(R.id.ivGuidePoint);
        llContainer = findViewById(R.id.llContainer);
        btnStart.setOnClickListener(this);
        mViewPager.setAdapter(new GuideAdapter());

        for (int i=0; i<mImageIds.length; i++){
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
            if (i>0){
                //当添加的小圆点的个数超过一个的时候就设置当前小圆点的左边距为10dp;
                params.leftMargin=10;
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
                mPaintDis =   llContainer.getChildAt(1).getLeft()-llContainer.getChildAt(0).getLeft();
                System.out.println("距离："+mPaintDis);
            }
        });


        //ViewPager滑动Pager监听  setOnPageChangeListener
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动过程中的回调
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //当滑到第二个Pager的时候，positionOffset百分比会变成0，position会变成1，所以后面要加上position*mPaintDis
                int letfMargin = (int)(mPaintDis*positionOffset)+position*mPaintDis;
                //在父布局控件中设置他的leftMargin边距
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)ivGuidePoint.getLayoutParams();
                params.leftMargin = letfMargin;
                ivGuidePoint.setLayoutParams(params);
            }


            @Override
            public void onPageSelected(int position) {
                System.out.println("position:"+position);
                if (position==mImageIds.length-1){
                    btnStart.setVisibility(View.VISIBLE);
                }else{
                    btnStart.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("state:"+state);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStart:
                gotoLoginActivity();
                break;
        }
    }

    private void gotoLoginActivity() {

    }


    class GuideAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //初始化item布局
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = new ImageView(GuideActivity.this);
            view.setBackgroundResource(mImageIds[position]);
            container.addView(view);
            return view;
        }

        //销毁item
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

}
