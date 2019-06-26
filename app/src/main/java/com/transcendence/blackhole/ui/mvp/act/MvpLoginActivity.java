package com.transcendence.blackhole.ui.mvp.act;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.ui.mvp.bean.User;
import com.transcendence.blackhole.ui.mvp.presenter.LoginPresenter;
import com.transcendence.blackhole.ui.mvp.presenter.LoginPresenterImpl;

/**
 * @author Joephone on 2019/6/25 15:42
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MvpLoginActivity extends TitleBarActivity implements View.OnClickListener,LoginPresenter.View{
    EditText etPhone;
    EditText etPwd;
    TextView tvLogin;

    private LoginPresenterImpl loginPresenter;//声明业务逻辑类

    @Override
    public int getLayoutId() {
        return R.layout.activity_mvp_test;
    }

    @Override
    public void init() {
        setTitle("登录");
        etPhone = findViewById(R.id.etPhone);
        etPwd = findViewById(R.id.etPwd);
        tvLogin = findViewById(R.id.tv);
        tvLogin.setOnClickListener(this);
        loginPresenter = new LoginPresenterImpl();//实例化业务逻辑类对象
        loginPresenter.attachView(this);//绑定view（把this付给业务逻辑类中的全局变量，
        //业务逻辑类中的逻辑方法会使用到这个全局变量（传进去的this），
        // 从而具体实现业务逻辑类中的业务逻辑）
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void loginSuccess(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void loginFailed(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv:
                //new的时候把东西get下来，赋给model Class ，完美！
                User user = new User(etPhone.getText().toString(),etPwd.getText().toString());
                loginPresenter.login(user);
                break;
        }
    }
}
