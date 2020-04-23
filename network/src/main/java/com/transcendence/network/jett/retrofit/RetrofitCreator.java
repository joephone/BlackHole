package com.transcendence.network.jett.retrofit;

import com.transcendence.global.API;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author Joephone on 2019/10/31 11:07
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RetrofitCreator {
    /**
     * 产生一个全局的retrofit客户端
     */

    /**
     * 可以单独设置的okhttp
     */
    private static final class OkHttpHolder{
        private static final int TIME_OUT=60;
        private static final OkHttpClient OK_HTTP_CLIENT=new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }


    /**
     * 获取对象
     */
    public static RetrofitAPI getRetrofitAPI(){
        Retrofit retrofit = getRetrofit();
        return retrofit.create(RetrofitAPI.class);
    }


    private static Retrofit getRetrofit() {
        final String BASE_URL= API.API_WAN_ANDROID; //ProjectInit.getConfiguratorByKey(ConfigKeys.API_HOST);
        Retrofit client =new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .build();
        return client;
    }
}
