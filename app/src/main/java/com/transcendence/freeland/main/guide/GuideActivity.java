package com.transcendence.freeland.main.guide;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.freeland.R;
import com.transcendence.freeland.main.ArouterAc;

/**
 * @author joephone
 * @date 2023/5/30
 * @desc
 */
public class GuideActivity extends AppAc {

    private ViewPager vpGuide;
    private ImageView ivGuidePoint;
    private LinearLayout llContainer;
    private TextView tvStart;
    /**
     * 导点间距
     */
    private int mPaintDis;
    public static int[] mGuideIds = new int[]{
            R.drawable.beauty01,
            R.drawable.beauty02,
            R.drawable.beauty03,
            R.drawable.beauty04};


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_guide;
    }

    @Override
    protected void initView() {
        llContainer = findViewById(R.id.ll_container);
        ivGuidePoint = findViewById(R.id.iv_guide_point);
        tvStart = findViewById(R.id.tv_start);
        tvStart.setOnClickListener(v ->{
            startAc(ArouterAc.class);
            finish();
        });
        vpGuide = findViewById(R.id.vp_guide);
        vpGuide.setAdapter(new GuideAdapter());

        for (int i = 0; i < mGuideIds.length; i++) {
            //创建ImageView把mImgaeViewIds放进去
//            ImageView view = new ImageView(this);
//            view.setBackgroundResource(mImageIds[i]);
//            //添加到ImageView的集合中
//            mImageViewList.add(view);


            //小圆点    一个小灰点是一个ImageView
            ImageView pointView = new ImageView(this);
            pointView.setImageResource(R.drawable.guide_cycle_shape_unselected);
            //初始化布局参数，父控件是谁，就初始化谁的布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                //当添加的小圆点的个数超过一个的时候就设置当前小圆点的左边距为10dp;
                params.leftMargin = 20;
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
        vpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                if (position == mGuideIds.length - 1) {
                    tvStart.setVisibility(View.VISIBLE);
                } else {
                    tvStart.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("state:" + state);
            }
        });
    }


    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mGuideIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //初始化item布局
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = new ImageView(mActivity);
            view.setBackgroundResource(mGuideIds[position]);
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
