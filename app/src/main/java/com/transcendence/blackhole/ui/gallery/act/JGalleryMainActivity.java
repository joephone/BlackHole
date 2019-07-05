package com.transcendence.blackhole.ui.gallery.act;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.ui.gallery.adp.JbottomAdapter;
import com.transcendence.blackhole.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2019/7/3 15:44
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class JGalleryMainActivity extends TitleBarActivity implements ViewPager.OnPageChangeListener,JbottomAdapter.ZbottomEvent{

    private ViewPager mVpMain;
    private JbottomAdapter adapter;
    private RecyclerView mRv;
    private LinearLayoutManager mLinearLayoutManager;

    private List<View> mPageViews = new ArrayList<>();
    private List<View> mRvViews = new ArrayList<>();
    private int[] mImageIds = new int[]{R.mipmap.beauty01,
                                        R.mipmap.beauty02,
                                        R.mipmap.beauty03,
                                        R.mipmap.beauty04,
                                        R.mipmap.beauty05,
                                        R.mipmap.beauty06,
                                        R.mipmap.beauty07,
                                        R.mipmap.beauty08,
                                        R.mipmap.beauty09,};

    @Override
    public int getLayoutId() {
        return R.layout.activity_gallary_z_main;
    }

    @Override
    public void init() {
        setTitle("jGallery");
        mVpMain = findViewById(R.id.viewPager);
        mRv = findViewById(R.id.rv);
        adapter = new JbottomAdapter(mActivity,mImageIds);

        setRv();
        setVP();
    }



    private void setRv() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRv.setLayoutManager(mLinearLayoutManager);
        mRv.setAdapter(adapter);
        adapter.setListener(this);
    }

    private void setVP() {
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

            GlideUtils.getInstance().loadMipmap(mActivity,mImageIds[i],iv);

            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            mPageViews.add(iv);
        }
        mVpMain.setAdapter(pagerAdapter);
        mVpMain.addOnPageChangeListener(this);
    }


    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(mPageViews.get(position));

        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "title";
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mPageViews.get(position));
            return mPageViews.get(position);
        }
    };

    @Override
    public void onPageScrolled(int position, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        if(isVisible(position)){
            if(mRvViews!=null && mRvViews.size()>0 && mRvViews.get(position)!=null){
                scrollToMiddleW(mRvViews.get(position),position);
            }
        }
        rvScrollToMid(position);
    }

    @Override
    public void onPageScrollStateChanged(int position) {

    }

    @Override
    public void onRvImageClick(View v,int position) {
        mVpMain.setCurrentItem(position);

        if(isVisible(position)){
            scrollToMiddleW(v,position);
        }
        rvScrollToMid(position);
    }

    @Override
    public void addRvView(View v) {
        mRvViews.add(v);
    }




    /**
     *   //1.判断当前点击的 Item 是否可见  所点击的 Item是不是在屏幕位置中可见
     * @param position
     * @return
     */
    private boolean isVisible(int position) {
        //第一个可见的Item 位置值
        final int firstPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        //最后一个可见的Item 位置值
        final int lastPosition = mLinearLayoutManager.findLastVisibleItemPosition();
        return position <= lastPosition && position >= firstPosition;

    }




    /**
     * Item中的点击事件调用此方法则，不在中间位置的View自动滚动到屏幕中间的位置
     * @param view
     * @param position
     */
    private void scrollToMiddleW(View view,int position) {
        int vWidth = view.getWidth();
        Rect rect = new Rect();
        mRv.getGlobalVisibleRect(rect);
        //除掉点击View的宽度，剩下整个屏幕的宽度
        int reWidth = rect.right - rect.left - vWidth;
        final int firstPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        //从左边到点击的Item的位置距离
        int left = mRv.getChildAt(position - firstPosition).getLeft();
        //半个屏幕的宽度
        int half = reWidth / 2;
        //向中间移动的距离
        int moveDis = left - half;

        mRv.smoothScrollBy( moveDis,0);
    }


    /**
     * Item 竖直布局点击居中
     * @param view
     * @param position
     */
    private void scrollToMiddleH(View view,int position) {
        int vHeight = view.getHeight();
        Rect rect = new Rect();
        mRv.getGlobalVisibleRect(rect);
        int reHeight = rect.top- rect.bottom - vHeight;

        final int firstPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        int top = mRv.getChildAt(position - firstPosition).getTop();
        int half = reHeight / 2;
        mRv.smoothScrollBy( 0,top - half);
    }

    private void rvScrollToMid(int position){
        adapter.setSelectedItem(position);
        adapter.notifyDataSetChanged();
    }
}
