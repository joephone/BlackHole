package com.transcendence.wan.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.transcendence.blackhole.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.blackhole.fragment.BlankFragment;
import com.transcendence.wan.R;
import com.transcendence.wan.main.WanMainActivity;

/**
 * @author Joephone on 2019/11/27 11:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class HomeFragment extends WanBaseFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    private ImageView ivLeft,ivRight;
    private ViewPager mVp;
    private GoweiiFragmentPagerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View rootView) {
        mVp = rootView.findViewById(R.id.vp);
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


    private void initVP() {
        //预加载
        mVp.setOffscreenPageLimit(1);
        adapter = new GoweiiFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.setTitles("广场","主页面");
        adapter.setFragments(DamaFragment.newInstance("广场"), BlankFragment.newInstance("主页面"));
        mVp.setAdapter(adapter);
        mVp.setCurrentItem(1);
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
