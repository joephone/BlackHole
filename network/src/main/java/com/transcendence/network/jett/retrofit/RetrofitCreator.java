package com.transcendence.network.jett.retrofit;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.transcendence.global.API;
import com.transcendence.network.jett.ApiSource;

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

    private static PersistentCookieJar mCookieJar = null;

    public static void setCookieJar(PersistentCookieJar cookieJar) {
        RetrofitCreator.mCookieJar = cookieJar;
    }

    /**
     * 产生一个全局的retrofit客户端
     */

    /**
     * 可以单独设置的okhttp
     */
    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .cookieJar(mCookieJar)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 获取对象
     *
     * @param source
     */
    public static RetrofitAPI getRetrofitAPI(ApiSource source) {
        Retrofit retrofit = getRetrofit(source);
        return retrofit.create(RetrofitAPI.class);
    }


    private static Retrofit getRetrofit(ApiSource source) {
        String BASE_URL = API.API_WAN_ANDROID; //ProjectInit.getConfiguratorByKey(ConfigKeys.API_HOST);
        if (source != null) {
            switch (source) {
                case WAN:
                    BASE_URL = API.API_WAN_ANDROID;
                    break;
                case GANK:
                    BASE_URL = API.API_GANK_IO;
                    break;
                case AIS:
                    BASE_URL = API.API_AIS;
                    break;
            }
        }
        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .build();
        return client;
    }
}
