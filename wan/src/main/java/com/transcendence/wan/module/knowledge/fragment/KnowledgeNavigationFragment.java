package com.transcendence.wan.module.knowledge.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transcendence.blackhole.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.wan.R;

/**
 * @author Joephone on 2019/11/27 11:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class KnowledgeNavigationFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    private ViewPager mVp;
    private GoweiiFragmentPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_knowledge, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVP();
    }

    private void initView(View rootView) {
        mVp = rootView.findViewById(R.id.vp);

    }


    private void initVP() {
        //预加载
        mVp.setOffscreenPageLimit(1);
        adapter = new GoweiiFragmentPagerAdapter(getChildFragmentManager());
        adapter.setFragments(KnowledgeNavigationOneFragment.newInstance("知识一"), KnowledgeNavigationTwoFragment.newInstance("知识二"));
        mVp.setAdapter(adapter);
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment .
     */
    public static KnowledgeNavigationFragment newInstance(String title) {
        KnowledgeNavigationFragment fragment = new KnowledgeNavigationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }

}
