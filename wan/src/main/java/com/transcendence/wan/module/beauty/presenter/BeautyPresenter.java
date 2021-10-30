package com.transcendence.wan.module.beauty.presenter;

import com.transcendence.core.utils.GsonUtils;
import com.transcendence.global.API;
import com.transcendence.network.jett.ApiSource;
import com.transcendence.network.jett.callback.IFailure;
import com.transcendence.network.jett.callback.ISuccess;
import com.transcendence.network.jett.retrofit.RetrofitClient;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.network.service.ParamMap;
import com.transcendence.wan.module.beauty.model.BeautyModel;
import com.transcendence.wan.module.beauty.view.BeautyView;

import java.util.Map;

/**
 * @Author Joephone on 2021/3/5 0005 下午 2:28
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class BeautyPresenter extends WanBasePresenter<BeautyView> {

    public void getBeautyList(int page,int count){
        Map<String,Object> map = ParamMap.getInstance().page(page,count);

        RetrofitClient.create()
                .source(ApiSource.GANK)
                .url(API.GANK_IO.GIRLS(page,count))
                .params(map)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        BeautyModel model = GsonUtils.getInstance().json2Cls(response, BeautyModel.class);
                        if(isAttach()){
                            getWanBaseView().getBeautyListSuc(200, model.getData());
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        if(isAttach()){
                            getWanBaseView().getBeautyListFail(200);
                        }
                    }
                })
                .build()
                .get();
    }

}
