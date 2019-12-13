package com.transcendence.blackhole.demo.mvp.presenter;

import com.transcendence.blackhole.base.mvp.LibBaseView;
import com.transcendence.blackhole.demo.mvp.bean.User;

/**
 * @author Joephone on 2019/6/25 15:19
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface LoginPresenter {

    interface View extends LibBaseView {
        void showToast(String msg) ;
        void loginSuccess(String msg) ;
        void loginFailed(String msg) ;
    }

    interface Presenter {
        //绑定view | 由原理图可知，presenter需要绑定View才行
        void attachView(LibBaseView v);

        void detachView();

        void login(User user);
    }
}
