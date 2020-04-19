package com.transcendence.wan.module.mine.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.transcendence.blackhole.utils.L;
import com.transcendence.wan.R;
import com.transcendence.wan.module.mine.act.SettingActivity;

/**
 * @author Joephone on 2019/12/17 14:41
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MineFragment extends Fragment implements View.OnClickListener{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";
    private String mContentText;
    private ImageView ivLeft,ivRight;

    private NestedScrollView nsv;
    private RelativeLayout rlUserInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_navi_mine, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initView(View rootView) {
        ivRight = rootView.findViewById(R.id.ivRight);
        rlUserInfo = rootView.findViewById(R.id.rlUserInfo);
        rlUserInfo.setOnClickListener(this);
        nsv = rootView.findViewById(R.id.nsv);

        init();
    }

    private void init() {
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {

            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                L.d("scrollY-"+scrollY);
                L.d("oldScrollY-"+oldScrollY);
                setIvBlurHeight(oldScrollY);
            }
        });
    }

    private void setIvBlurHeight(int height) {
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment .
     */
    public static MineFragment newInstance(String title) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlUserInfo:
//                UserUtils.getInstance().doIfLogin(getContext());

                SettingActivity.start(getActivity());
                break;
        }
    }
}
