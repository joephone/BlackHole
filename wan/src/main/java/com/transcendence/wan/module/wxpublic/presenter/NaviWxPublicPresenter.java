package com.transcendence.wan.module.wxpublic.presenter;

import com.transcendence.blackhole.utils.GsonUtils;
import com.transcendence.blackhole.utils.L;
import com.transcendence.global.API;
import com.transcendence.network.jett.callback.IFailure;
import com.transcendence.network.jett.callback.ISuccess;
import com.transcendence.network.jett.retrofit.RetrofitClient;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.core.service.ParamMap;
import com.transcendence.wan.module.wxpublic.model.WxChapterBean;
import com.transcendence.wan.module.wxpublic.model.WxChapterModel;
import com.transcendence.wan.module.wxpublic.view.NaviWxPublicView;

import java.util.Map;

/**
 * @Author Joephone on 2020/4/26 14:53
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class NaviWxPublicPresenter extends WanBasePresenter<NaviWxPublicView> {


//    L.d("WxPublicListFragment loadData");
//    OkHttpClient client = new OkHttpClient.Builder().build();
//    Request request = new Request.Builder().url(API.WAN.WX_ARTICLE_CHAPTER).build();
//        client.newCall(request).enqueue(new Callback() {
//        @Override
//        public void onFailure(Call call, IOException e) {
//            L.d("onFailure");
//        }
//
//        @Override
//        public void onResponse(Call call, Response response) throws IOException {
////                L.d(" WxPublicListFragment onResponse"+response.body().string());
//            WxChapterModel chapter =  GsonUtils.getInstance().json2Cls(response.body().string(), WxChapterModel.class);
//        }
//    });


    public void getWxPublicList(){
        L.d("getWxPublicList");
        Map<String,Object>  map = ParamMap.getInstance().page(0);

        RetrofitClient.create()
                .url(API.WAN.WX_ARTICLE_CHAPTER)
                .params(map)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        L.d("response--"+response);
                        WxChapterModel model = GsonUtils.json2Cls(response,WxChapterModel.class);
                        if(isAttach()){
                            getWanBaseView().getWxChapterSuc(0,model.getData());
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
