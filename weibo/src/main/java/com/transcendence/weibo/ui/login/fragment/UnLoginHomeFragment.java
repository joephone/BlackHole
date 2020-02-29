package com.transcendence.weibo.ui.login.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.transcendence.weibo.R;
import com.transcendence.weibo.base.fragment.BaseFragment;

/**
 * Created by Administrator on 2020/2/29.
 */

public class UnLoginHomeFragment extends BaseFragment {
    private View mView;
    private ImageView mCircleView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.unlogin_mainfragment_layout, container, false);
        mCircleView = (ImageView) mView.findViewById(R.id.circleView);
        Animation rotateAnim = AnimationUtils.loadAnimation(getContext(), R.anim.endlessrotate);
        LinearInterpolator lin = new LinearInterpolator();
        rotateAnim.setInterpolator(lin);
        mCircleView.setAnimation(rotateAnim);
        return mView;
    }

}
