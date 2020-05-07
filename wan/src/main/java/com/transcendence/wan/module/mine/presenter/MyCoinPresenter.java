package com.transcendence.wan.module.mine.presenter;

import com.transcendence.blackhole.utils.GsonUtils;
import com.transcendence.blackhole.utils.L;
import com.transcendence.global.API;
import com.transcendence.network.jett.callback.IFailure;
import com.transcendence.network.jett.callback.ISuccess;
import com.transcendence.network.jett.retrofit.RetrofitClient;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.core.service.ParamMap;
import com.transcendence.wan.core.service.ParamUser;
import com.transcendence.wan.module.mine.model.MyCoinListModel;
import com.transcendence.wan.module.mine.model.MyCoinModel;
import com.transcendence.wan.module.mine.view.MyCoinView;

import java.util.Map;

/**
 * @Author Joephone on 2020/4/25 14:42
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MyCoinPresenter extends WanBasePresenter<MyCoinView> {


    public void getCoin() {
        L.d("getCoin");
        Map<String,Object> map = ParamUser.getInstance().logout();

        RetrofitClient.create()
                .url(API.WAN.MY_COIN)
                .params(map)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        L.d("response--"+response);
                        MyCoinModel model = GsonUtils.json2Cls(response,MyCoinModel.class);
                        if(isAttach()){
                            getWanBaseView().getMyCoinSuc(0,model.getData());
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .build().get();
    }



    public void getMyCoinList(int page) {
        L.d("getMyCoinList");
        Map<String,Object> map = ParamMap.getInstance().page(page);

        RetrofitClient.create()
                .url(API.WAN.MY_COIN_LIST(page))
                .params(map)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        L.d("response--"+response);
                        MyCoinListModel model = GsonUtils.json2Cls(response, MyCoinListModel.class);
                        if(isAttach()){
                            getWanBaseView().getMyCoinListSuc(0,model.getData().getDatas());
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .build().get();
    }

}