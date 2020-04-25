package com.transcendence.wan.module.mine.presenter;

import com.transcendence.blackhole.utils.GsonUtils;
import com.transcendence.blackhole.utils.L;
import com.transcendence.global.API;
import com.transcendence.network.jett.callback.IFailure;
import com.transcendence.network.jett.callback.ISuccess;
import com.transcendence.network.jett.retrofit.RetrofitClient;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.core.service.ParamMap;
import com.transcendence.wan.module.mine.model.RankListModel;
import com.transcendence.wan.module.mine.view.RankView;

import java.util.Map;

/**
 * @Author Joephone on 2020/4/24 22:17
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RankListPresenter extends WanBasePresenter<RankView>{

    public void rankList(int page){
        L.d("rankList");
        Map<String,Object> map = ParamMap.getInstance().page(page);

        RetrofitClient.create()
                .url(API.WAN.COIN_RANK_LIST(page))
                .params(map)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        L.d("rankList--"+response);
                        RankListModel rankListModel = GsonUtils.json2Cls(response,RankListModel.class);
                        if(isAttach()){
                            getWanBaseView().getRankListSuc(0,rankListModel.getData().getDatas());
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
