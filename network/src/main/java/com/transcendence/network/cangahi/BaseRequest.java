package com.transcendence.network.cangahi;

import com.transcendence.global.API;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * @Author Joephone on 2021/9/23 0023 下午 5:07
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class BaseRequest {

    //初始化Okhttp,绑定拦截器事件
    OkHttpClient client=new OkHttpClient.Builder().
            connectTimeout(20, TimeUnit.SECONDS).                   //设置请求超时时间
            readTimeout(20, TimeUnit.SECONDS).                       //设置读取数据超时时间
            writeTimeout(20,TimeUnit.SECONDS).                      //设置写入数据超时时间
            addInterceptor(InterceptorUtil.LogInterceptor()).                //绑定日志拦截器
            addNetworkInterceptor(InterceptorUtil.HeaderInterceptor())       //绑定header拦截器
            .build();

    Retrofit retrofit=new Retrofit.Builder().
            addConverterFactory(GsonConverterFactory.create()).             //设置gson转换器,将返回的json数据转为实体
            addCallAdapterFactory(RxJavaCallAdapterFactory.create()).       //设置CallAdapter
            baseUrl(API.API_AIS)
            .client(client)                                                  //设置客户端okhttp相关参数
            .build();


    public static BaseRequest instance;
    public ApiService apiService =retrofit.create(ApiService.class);         //经过retrofit的实例,获取ApiServise接口的实例

    private BaseRequest(){

    }
    public static BaseRequest getInstance(){
        if(instance==null){
            synchronized (BaseRequest.class){
                if(instance==null){
                    instance=new BaseRequest();
                }
            }
        }
        return instance;
    }

    public ApiService getApiService(){
        return apiService;
    }

}
