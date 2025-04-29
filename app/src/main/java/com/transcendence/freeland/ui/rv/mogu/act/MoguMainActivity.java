package com.transcendence.freeland.ui.rv.mogu.act;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.freeland.R;
import com.transcendence.freeland.ui.rv.mogu.bean.ItemVO;
import com.transcendence.freeland.ui.rv.mogu.fragment.ListFragment;
import com.transcendence.freeland.ui.rv.mogu.ui.AutoHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

public class MoguMainActivity extends AppAc { //AppCompatActivity
    private static final int NUM_FRAGMENT = 10;
    private ViewPager viewPager;
    private AutoHorizontalScrollView menu;
    private LinearLayout tabLayouts;
    private LinearLayout typeLayouts;

    private List<ListFragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<TextView> textViews = new ArrayList<>();
    private List<ItemVO> itemList = new ArrayList<>();

    private String[] typeTitles = {"李易峰专区","当剩女遇见桃花","春季遮肉必看",
            "甜心开胃菜","租男友","开学衣橱大改造","没有PS你可以吗","藏肉显瘦搭配"};
    private int[] typeImgs = {R.mipmap.activity_ui_rv_mogu_icon1,
            R.mipmap.activity_ui_rv_mogu_icon1,
            R.mipmap.activity_ui_rv_mogu_icon1,
            R.mipmap.activity_ui_rv_mogu_icon1,
            R.mipmap.activity_ui_rv_mogu_icon1,
            R.mipmap.activity_ui_rv_mogu_icon1,
            R.mipmap.activity_ui_rv_mogu_icon1,
            R.mipmap.activity_ui_rv_mogu_icon1};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ui_rv_mogu_main;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_ui_rv_mogu_main);
//
//
//
//    }



    private void initFragments() {
        for (int i = 0;i < NUM_FRAGMENT;i++){
            titles.add("title"+i);
            ListFragment fragment = new ListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("type",i);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);

            LinearLayout tabLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_ui_rv_mogu_tab_item,null);
            final TextView textView = (TextView)tabLayout.findViewById(R.id.tv_tab);
            textView.setText(titles.get(i));
            final int id = i;
            tabLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setSelector(id);
                }
            });
            tabLayouts.addView(tabLayout);
            textViews.add(textView);
        }
    }


    protected void initView() {
        setTitle("仿蘑菇街");
        viewPager = findViewById(R.id.id_viewpager);
        menu = findViewById(R.id.id_horizontalmenu);
        tabLayouts = findViewById(R.id.tab_layout);
        typeLayouts = findViewById(R.id.id_horizontalview_layout);
        initTypeLayout();
        initFragments();

        setSelector(0);
        viewPager.setCurrentItem(0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelector(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return NUM_FRAGMENT;
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }
        });


    }

    /**
     * 选中效果
     * @param position
     */
    private void setSelector(final int position) {
        for (int i = 0;i < NUM_FRAGMENT; i++){
            if (position == i){
                viewPager.setCurrentItem(position);
                menu.resetScrollWidth(position);
                textViews.get(i).setBackgroundResource(R.mipmap.activity_ui_rv_mogu_bg_nav_contacts);
            }else {
                textViews.get(i).setBackgroundResource(R.color.transparent);
            }
        }
    }

    /**
     * 初始化横向滑动的layouts
     */
    private void initTypeLayout() {
        initItemList();
        for (final ItemVO itemVO : itemList){
            FrameLayout tabLayout = (FrameLayout)LayoutInflater.from(this).inflate(R.layout.activity_ui_rv_mogu_horizontal_item,null);
            ImageView imageView = (ImageView)tabLayout.findViewById(R.id.id_horizontal_item_img);
            TextView textView = (TextView)tabLayout.findViewById(R.id.id_horizontal_item_desc);
            imageView.setImageResource(itemVO.getImage());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            textView.setText(itemVO.getDesc());

            tabLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //intent进入其他页面
                    //……
                    Toast.makeText(MoguMainActivity.this, "进入页面", Toast.LENGTH_SHORT).show();
                }
            });

            LinearLayout.LayoutParams vlp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            vlp.setMargins(calculateDpToPx(5),calculateDpToPx(5),calculateDpToPx(5),calculateDpToPx(5));
            typeLayouts.addView(tabLayout,vlp);
        }

    }

    private void initItemList() {
        itemList.clear();
        for (int i = 0;i < typeImgs.length;i ++){
            ItemVO itemVO = new ItemVO(typeTitles[i],typeImgs[i]);
            itemList.add(itemVO);
        }
    }

    private int calculateDpToPx(int padding_in_dp){
        final float scale = getResources().getDisplayMetrics().density;
        return  (int) (padding_in_dp * scale + 0.5f);
    }

}
