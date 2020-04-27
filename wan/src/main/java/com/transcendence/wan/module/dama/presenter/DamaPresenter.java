package com.transcendence.wan.module.dama.presenter;

import com.transcendence.blackhole.utils.GsonUtils;
import com.transcendence.blackhole.utils.L;
import com.transcendence.global.API;
import com.transcendence.network.jett.callback.IFailure;
import com.transcendence.network.jett.callback.ISuccess;
import com.transcendence.network.jett.retrofit.RetrofitClient;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.core.service.ParamMap;
import com.transcendence.wan.module.dama.model.DamaBean;
import com.transcendence.wan.module.dama.view.DamaView;

import java.util.Map;

/**
 * @author Joephone on 2019/12/11 16:13
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class DamaPresenter extends WanBasePresenter<DamaView> {

    public void getArticleList(int page){
        Map<String,Object> map = ParamMap.getInstance().page(page);

        RetrofitClient.create()
                .url(API.WAN.DAMA_ARTICLE_LIST(page))
                .params(map)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        L.d(" Dama onResponse"+response);
                        DamaBean damaBean = GsonUtils.getInstance().json2Cls(response, DamaBean.class);
                        if(isAttach()){
                            getWanBaseView().getUserArticleListSuccess(200,damaBean.getData().getDatas());
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        L.d(" Dama onFailure");
                        if(isAttach()){
                            getWanBaseView().getUserArticleListFailed(200,"请求失败");
                        }
                    }
                })
                .build()
                .get();

    }


}
