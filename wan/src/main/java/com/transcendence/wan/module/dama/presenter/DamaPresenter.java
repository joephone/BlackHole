package com.transcendence.wan.module.dama.presenter;

import com.transcendence.core.utils.GsonUtils;
import com.transcendence.core.utils.L;
import com.transcendence.global.API;
import com.transcendence.network.jett.callback.IError;
import com.transcendence.network.jett.callback.IFailure;
import com.transcendence.network.jett.callback.ISuccess;
import com.transcendence.network.jett.retrofit.RetrofitClient;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.network.service.ParamMap;
import com.transcendence.wan.module.main.bean.ArticleListBean;
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
                .url(API.WAN.ARTICLE_LIST_DAMA(page))
                .params(map)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        L.d(" Dama onResponse"+response);
                        ArticleListBean articleListBean = GsonUtils.getInstance().json2Cls(response, ArticleListBean.class);
                        if(isAttach()){
                            getWanBaseView().getUserArticleListSuccess(200, articleListBean.getData().getDatas());
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        L.d(" Dama onFailure");
//                        if(isAttach()){
//                            getWanBaseView().getUserArticleListFailed(200,"请求失败");
//                        }
                        if(isAttach()){
                            getWanBaseView().getUserArticleListFailed(200,"请求失败");
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        L.d(" Dama IError");
                        if(isAttach()){
                            getWanBaseView().getUserArticleListFailed(200,msg);
                        }
                    }
                })
                .build()
                .get();

    }


}
