package com.transcendence.network.retrofit;

import com.transcendence.global.API;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @Author Joephone on 2020/4/20 0:29
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RetrofitFactory {

    private static class OkHttpClientHolder{
        private static final int TIME_OUT=60;
        public static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }


    public static Retrofit factory(){
        final String BASE_URL = API.API_WAN_ANDROID;
        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpClientHolder.OK_HTTP_CLIENT)
                .build();
        return client;
    }

}
