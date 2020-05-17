package com.transcendence.wan.module.knowledge.presenter;

import com.transcendence.blackhole.utils.GsonUtils;
import com.transcendence.global.API;
import com.transcendence.network.jett.callback.IFailure;
import com.transcendence.network.jett.callback.ISuccess;
import com.transcendence.network.jett.retrofit.RetrofitClient;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.core.service.ParamMap;
import com.transcendence.wan.module.knowledge.model.NaviModel;
import com.transcendence.wan.module.knowledge.view.KnowledgeTwoView;

import java.util.Map;

/**
 * @Author Joephone on 2020/5/9 4:36
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class KnowledgeTwoPresenter extends WanBasePresenter<KnowledgeTwoView> {

    public void getNavi() {
        Map<String,Object> map = ParamMap.getInstance().page(10);

        RetrofitClient.create()
                .params(map)
                .url(API.WAN.NAVI)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        L.d("response-"+response);
                        NaviModel model = GsonUtils.json2Cls(response,NaviModel.class);
                        if(isAttach()){
                            getWanBaseView().getNaviSuc(0,model.getData());
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .build().post();

    }
}
