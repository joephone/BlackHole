package com.transcendence.wan.module.dama.presenter;

import com.transcendence.blackhole.global.API;
import com.transcendence.blackhole.utils.GsonUtils;
import com.transcendence.blackhole.utils.L;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.module.dama.model.DamaBean;
import com.transcendence.wan.module.dama.view.DamaView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Joephone on 2019/12/11 16:13
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class DamaPresenter extends WanBasePresenter<DamaView> {

    public void getArticleList(int page){
        OkHttpClient client = new OkHttpClient.Builder().build();


        Request request = new Request.Builder().url(API.WAN.damaArticleList(page)).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.d("onFailure");
//                Message message = Message.obtain();
//                message.what =1;
//                mHandler.sendMessageDelayed(message,3000);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                L.d(" Dama onResponse"+json);
                DamaBean damaBean = GsonUtils.getInstance().json2Cls(json, DamaBean.class);
                if(isAttach()){
                    getWanBaseView().getUserArticleListSuccess(200,damaBean.getData().getDatas());
                }

            }
        });
    }


}
