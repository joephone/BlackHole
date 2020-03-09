package com.transcendence.wan.module.login.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.transcendence.blackhole.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.blackhole.widget.custom.TabView;
import com.transcendence.wan.R;
import com.transcendence.wan.event.LoginEvent;
import com.transcendence.wan.module.login.act.LoginActivity;
import com.transcendence.wan.module.login.model.LoginBean;
import com.transcendence.wan.module.login.presenter.LoginPresenter;
import com.transcendence.wan.module.login.view.LoginView;
import com.transcendence.wan.module.main.fragment.WanBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2020/3/2 20:39
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class LoginFragment extends WanBaseFragment<LoginPresenter> implements View.OnClickListener,LoginView {


    private LinearLayout mLlGoRegister;

    private LoginActivity mActivity;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment .
     */
    public static LoginFragment newInstance(String title) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }


    private List<TabView> mTabs = new ArrayList<>();
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    private ViewPager mVpMain;
    private GoweiiFragmentPagerAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (LoginActivity) context;
    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Nullable
    @Override
    protected LoginPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_login, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    private void initView(View rootView) {
        mLlGoRegister = rootView.findViewById(R.id.llGoRegister);
        mLlGoRegister.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llGoRegister:
                mActivity.changeVp(1);
                break;
        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void showLoadingBar() {

    }

    @Override
    public void dismissLoadingBar() {

    }

    @Override
    public void clearLoading() {

    }

    @Override
    public void loginSuccess(int code, LoginBean data) {
        new LoginEvent(true).post();
    }

    @Override
    public void loginFailed(int code, String msg) {

    }
}
