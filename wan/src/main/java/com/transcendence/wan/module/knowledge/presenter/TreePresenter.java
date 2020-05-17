package com.transcendence.wan.module.knowledge.presenter;

import com.transcendence.blackhole.utils.GsonUtils;
import com.transcendence.blackhole.utils.L;
import com.transcendence.global.API;
import com.transcendence.network.jett.callback.IFailure;
import com.transcendence.network.jett.callback.ISuccess;
import com.transcendence.network.jett.retrofit.RetrofitClient;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.core.service.ParamMap;
import com.transcendence.wan.module.knowledge.model.TreeModel;
import com.transcendence.wan.module.knowledge.view.TreeView;

import java.util.Map;

/**
 * @Author Joephone on 2020/5/9 4:36
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class TreePresenter extends WanBasePresenter<TreeView> {

    public void getTree() {
        Map<String,Object> map = ParamMap.getInstance().page(10);

        RetrofitClient.create()
                .params(map)
                .url(API.WAN.TREE)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        L.d("response-"+response);
                        TreeModel model = GsonUtils.json2Cls(response,TreeModel.class);
                        if(isAttach()){
                            getWanBaseView().getTreeSuc(0,model.getData());
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
