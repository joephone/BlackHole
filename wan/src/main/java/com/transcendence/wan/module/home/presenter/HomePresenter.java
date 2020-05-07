package com.transcendence.wan.module.home.presenter;

import com.transcendence.blackhole.utils.GsonUtils;
import com.transcendence.global.API;
import com.transcendence.network.jett.callback.IFailure;
import com.transcendence.network.jett.callback.ISuccess;
import com.transcendence.network.jett.retrofit.RetrofitClient;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.module.home.model.BannerModel;
import com.transcendence.wan.module.home.view.HomeView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Joephone on 2020/5/6 15:46
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class HomePresenter extends WanBasePresenter<HomeView>{

    public void initBannerData() {
        Map<String, Object> map = new HashMap<>();  //ParamApi.getInstance().listPage(1, 10);
        RetrofitClient.create()
                .url(API.WAN.BANNER)
                .params(map)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                         L.d(response.toString());
                        BannerModel model = GsonUtils.json2Cls(response,BannerModel.class);
                        if(isAttach()){
                            getWanBaseView().getBannerSuccess(0,model.getData());
                        }

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .build()
                .post();

    }

}
