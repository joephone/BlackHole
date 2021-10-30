package com.transcendence.heima.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;

import com.transcendence.heima.R;
import com.transcendence.heima.base.BaseActivity;
import com.transcendence.heima.bean.LoginBean;
import com.transcendence.heima.contract.LoginContract;
import com.transcendence.heima.presenter.LoginPresenter;


/**
 * 登录View
 *
 * @author gc
 * @since 1.0
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView {

    private EditText inputEmail; // 账号
    private EditText inputPassword; // 密码
    private AppCompatButton btnLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter loadPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {
        inputEmail = (EditText)findViewById(R.id.input_email); // 账号
        inputPassword = (EditText)findViewById(R.id.input_password); // 密码
        btnLogin = (AppCompatButton)findViewById(R.id.btn_login);
    }

    @Override
    protected void initListener() {
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void otherViewClick(View view) {
//        mPresenter.login(getUserName(), getPwd());
        mPresenter.aisFetch("121112312312312313131");
    }

    @Override
    public String getUserName() {
        return inputEmail.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return inputPassword.getText().toString().trim();
    }

    @Override
    public void loginSuccess(LoginBean loginBean) {
        toast(loginBean.toString());
    }

    @Override
    public void loginFail(String failMsg) {
        toast(failMsg);
    }

    public boolean checkNull() {
        boolean isNull = false;
        if (TextUtils.isEmpty(getUserName())) {
            inputEmail.setError("账号不能为空");
            isNull = true;
        } else if (TextUtils.isEmpty(getPwd())) {
            inputPassword.setError("密码不能为空");
            isNull = true;
        }
        return isNull;
    }
}
