package com.transcendence.wan.module.home.presenter;

import com.transcendence.core.utils.GsonUtils;
import com.transcendence.core.utils.L;
import com.transcendence.global.API;
import com.transcendence.network.jett.callback.IError;
import com.transcendence.network.jett.callback.IFailure;
import com.transcendence.network.jett.callback.ISuccess;
import com.transcendence.network.jett.retrofit.RetrofitClient;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.presenter.WanTitlebarPresenter;
import com.transcendence.network.service.ParamMap;
import com.transcendence.wan.module.home.model.BannerModel;
import com.transcendence.wan.module.home.view.HomeView;
import com.transcendence.wan.module.main.bean.ArticleListBean;
import com.transcendence.wan.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Joephone on 2020/5/6 15:46
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class HomePresenter extends WanTitlebarPresenter<HomeView> {
    /**
     *   fetch banner data
     */
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

    /**
     * fetch article data
     * @param page
     */
    public void getArticleList(int page){
        Map<String,Object> map = ParamMap.getInstance().page(page);

        RetrofitClient.create()
                .url(API.WAN.ARTICLE_LIST_HOME(page))
                .params(map)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        L.d("home onResponse"+response);
                        ArticleListBean bean = GsonUtils.getInstance().json2Cls(response, ArticleListBean.class);
                        if(isAttach()){
                            getWanBaseView().getArticleListHomeSuccess(200,bean.getData().getDatas());
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        if(isAttach()){
                            getWanBaseView().getArticleListHomeFailed(200, StringUtils.getString(R.string.request_fail));
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        if(isAttach()){
                            getWanBaseView().getArticleListHomeFailed(200,msg);
                        }
                    }
                })
                .build()
                .get();

    }
}
