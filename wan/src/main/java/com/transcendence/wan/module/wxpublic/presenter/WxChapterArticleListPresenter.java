package com.transcendence.wan.module.wxpublic.presenter;

import com.transcendence.core.utils.GsonUtils;
import com.transcendence.global.API;
import com.transcendence.network.jett.callback.IFailure;
import com.transcendence.network.jett.callback.ISuccess;
import com.transcendence.network.jett.retrofit.RetrofitClient;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.network.service.ParamWx;
import com.transcendence.wan.module.wxpublic.model.WxChapterArticleModel;
import com.transcendence.wan.module.wxpublic.view.WxChapterArticleListView;

import java.util.Map;

/**
 * @Author Joephone on 2020/4/27 1:41
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WxChapterArticleListPresenter extends WanBasePresenter<WxChapterArticleListView> {

    public void getWxArticleChapterList(int id,int page) {
//        L.d("getWxArticleChapterList");
        Map<String,Object> map = ParamWx.getInstance().wxChapterArticleList(id,page);

        RetrofitClient.create()
                .url(API.WAN.WX_CHAPTER_ARTICLE_LIST(id,page))
                .params(map)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        L.d("response--"+response);
                        WxChapterArticleModel model = GsonUtils.json2Cls(response,WxChapterArticleModel.class);
                        if(isAttach()) {
                            getWanBaseView().getWxChapterArticleListSuc(0,model.getData().getDatas());
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
