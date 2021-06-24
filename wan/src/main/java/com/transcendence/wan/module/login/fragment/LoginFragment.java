package com.transcendence.wan.module.login.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.transcendence.core.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.core.utils.L;
import com.transcendence.core.widget.custom.TabView;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.event.LoginEvent;
import com.transcendence.wan.module.login.act.LoginActivity;
import com.transcendence.wan.module.login.model.LoginBean;
import com.transcendence.wan.module.login.presenter.LoginPresenter;
import com.transcendence.wan.module.login.view.LoginView;
import com.transcendence.wan.widget.AccountInputView;
import com.transcendence.wan.widget.PasswordInputView;
import com.transcendence.wan.widget.SubmitView;

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

    private AccountInputView tvAccount;
    private PasswordInputView tvPassWord;
    private SubmitView svLogin;
    private LinearLayout llGoRegister;

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
        return R.layout.fragment_login;
    }

    @Nullable
    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {
        tvAccount = findViewById(R.id.tvAccount);
        tvPassWord = findViewById(R.id.tvPassWord);
        llGoRegister = findViewById(R.id.llGoRegister);
        svLogin = findViewById(R.id.sv_login);
        llGoRegister.setOnClickListener(this);
        svLogin.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llGoRegister:
                mActivity.changeVp(1);
            case R.id.sv_login:
                String username = "15171484007";//tvAccount.getText().trim();
                String password = "123456";//tvPassWord.getText().trim();
                presenter.login(username,password);
                break;
        }
    }

    @Override
    public void loginSuccess(int code, LoginBean data) {
        L.d("loginSuccess");
        new LoginEvent(true).post();
        finish();
    }

    @Override
    public void loginFailed(int code, String msg) {
        L.d("loginFailed");
    }
}
