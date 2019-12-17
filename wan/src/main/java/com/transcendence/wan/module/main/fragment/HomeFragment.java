package com.transcendence.wan.module.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.transcendence.blackhole.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.blackhole.network.callback.IFailure;
import com.transcendence.blackhole.network.callback.ISuccess;
import com.transcendence.blackhole.network.retrofit.RetrofitClient;
import com.transcendence.blackhole.utils.L;
import com.transcendence.blackhole.utils.ScreenUtils;
import com.transcendence.blackhole.widget.custom.banner.BannerLayout;
import com.transcendence.wan.R;
import com.transcendence.wan.module.home.model.BannerBean;
import com.transcendence.wan.module.main.act.WanMainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Joephone on 2019/11/27 11:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class HomeFragment extends Fragment implements BannerLayout.OnBannerItemClickListener{

    private static final String ARG_SHOW_TEXT = "text";

    private ImageView ivLeft,ivRight;

    private BannerLayout mBanner;
    private RecyclerView mRv;
    private GoweiiFragmentPagerAdapter adapter;

    private List<BannerBean.DataBean> mBannerBeans;
    private List<String> mBannerUrls;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);
        initView(rootView);
        initBannerData();
//        createHeaderBanner();
        return rootView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View rootView) {
        mBanner = rootView.findViewById(R.id.banner);
        mBanner.setOnBannerItemClickListener(this);
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
        if (mBanner == null) {
            mBanner = new BannerLayout(getContext());
            int height = (int) (ScreenUtils.getScreenWidth(getContext()) * (9F / 16F));
            mBanner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));

            mBanner.setOnBannerItemClickListener(this);
//            mBanner.setViewUrls();


//            mBanner.setIndicatorGravity(BannerConfig.CENTER);
//            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
//            mBanner.setBannerAnimation(Transformer.Default);
            mBanner.startAutoPlay();

        }
//        if (SettingUtils.getInstance().isShowBanner()) {
//            mBanner.setVisibility(View.VISIBLE);
//        } else {
//            mBanner.setVisibility(View.GONE);
//        }
    }


    private void initBannerData() {
        Map<String, Object> map = new HashMap<>();  //ParamApi.getInstance().listPage(1, 10);


        RetrofitClient.create()
                .url("banner/json")
                .params(map)
                .success(new ISuccess() {
                     @Override
                     public void onSuccess(String response) {
                         L.d(response.toString());
                         Gson gson = new Gson();
                         BannerBean model = gson.fromJson(response,BannerBean.class);
                         mBannerBeans = model.getData();
                         mBannerUrls = new ArrayList<>(model.getData().size());
                         List<String> titles = new ArrayList<>(model.getData().size());
                         for (BannerBean.DataBean bean : model.getData()) {
                             mBannerUrls.add(bean.getImagePath());
                             titles.add(bean.getTitle());
                         }
                         mBanner.setViewUrls(mBannerUrls);
                     }
                 })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .build()
                .post();

    }


    public static HomeFragment newInstance(String title) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onScroll(int position) {

    }

    @Override
    public void onMove(int position) {

    }


    @Override
    public void onStart() {
        super.onStart();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }
}
