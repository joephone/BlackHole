package com.transcendence.wan.module.mine.presenter;

import com.transcendence.blackhole.utils.L;
import com.transcendence.global.API;
import com.transcendence.network.jett.callback.IFailure;
import com.transcendence.network.jett.callback.ISuccess;
import com.transcendence.network.jett.retrofit.RetrofitClient;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.core.service.ParamUser;
import com.transcendence.wan.module.mine.view.MineView;

import java.util.Map;

/**
 * @Author Joephone on 2020/4/24 17:16
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MinePresenter extends WanBasePresenter<MineView> {


    public void logout(){
        Map<String,Object> map = ParamUser.getInstance().logout();

        RetrofitClient.create()
                .url(API.WAN.LOG_OUT)
                .params(map)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        L.d(""+response);
                        if (isAttach()) {
                            getWanBaseView().logoutSuccess(0, null);
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        L.d("onFailure");
                        if (isAttach()) {
                            getWanBaseView().logoutFailed(-1, "");
                        }
                    }
                })
                .build()
                .get();
    }
}
