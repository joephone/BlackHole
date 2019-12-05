package com.transcendence.wan.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hjq.image.ImageLoader;
import com.transcendence.blackhole.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.blackhole.fragment.BlankFragment;
import com.transcendence.wan.R;
import com.transcendence.wan.main.WanMainActivity;

import javax.xml.transform.Transformer;

/**
 * @author Joephone on 2019/11/27 11:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class HomeFragment extends WanBaseFragment {

    private static final String ARG_SHOW_TEXT = "text";

    private ImageView ivLeft,ivRight;
//    private Banner
    private RecyclerView mRv;
    private GoweiiFragmentPagerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);
        initView(rootView);
        createHeaderBanner();
        return rootView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View rootView) {
        mRv = rootView.findViewById(R.id.rv);
        ivLeft = rootView.findViewById(R.id.ivLeft);
        ivRight = rootView.findViewById(R.id.ivRight);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                if(activity instanceof WanMainActivity){
                    WanMainActivity mainActivity = (WanMainActivity) activity;
                    mainActivity.slideToDama();
                }
            }
        });
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void createHeaderBanner() {
//        if (mBanner == null) {
//            mBanner = new Banner(getContext());
//            int height = (int) (DisplayInfoUtils.getInstance().getWidthPixels() * (9F / 16F));
//            mBanner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
//            mBanner.setImageLoader(new com.youth.banner.loader.ImageLoader() {
//                @Override
//                public void displayImage(Context context, Object url, ImageView imageView) {
//                    ImageLoader.banner(imageView, (String) url);
//                }
//            });
//            mBanner.setIndicatorGravity(BannerConfig.CENTER);
//            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
//            mBanner.setBannerAnimation(Transformer.Default);
//            mBanner.startAutoPlay();
//            mBanner.setDelayTime(5000);
//            mBanner.setOnBannerListener(new OnBannerListener() {
//                @Override
//                public void OnBannerClick(int position) {
//                    BannerBean bean = mBannerBeans.get(position);
//                    WebActivity.start(getContext(), bean.getTitle(), bean.getUrl());
//                }
//            });
//            mAdapter.addHeaderView(mBanner, 0);
//        }
//        if (SettingUtils.getInstance().isShowBanner()) {
//            mBanner.setVisibility(View.VISIBLE);
//        } else {
//            mBanner.setVisibility(View.GONE);
//        }
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment .
     */
    public static HomeFragment newInstance(String title) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }

}
