package com.transcendence.network.cangahi;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @Author Joephone on 2021/9/23 0023 下午 5:05
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public interface ApiService {

    /**
     * post请求方式
     */
    @FormUrlEncoded         //post请求必需要申明该注解
    @POST("locationInfo/shipInfo")
    Observable<BaseResponse<RequestBody>> getInfo(@Field("mmsi") String mmsi);//请求参数

    /**
     * post请求方式
     */
    @FormUrlEncoded         //post请求必需要申明该注解
    @POST("locationInfo/shipInfo")
    Observable<BaseResponse<RequestBody>> getInfoMap(@FieldMap Map<String, Object> map);//请求参数


    @POST("logon/getCaptcha")
    Observable<BaseResponse<RequestBody>> getCaptcha();//请求参数

    @FormUrlEncoded         //post请求必需要申明该注解
    @POST("device/location/add")
    Observable<BaseResponse<RequestBody>> aisFetch(@FieldMap Map<String, Object> contentList);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @FormUrlEncoded         //post请求必需要申明该注解
    @POST("device/location/add")
    Observable<BaseResponse<RequestBody>> aisFetchJson(@Field("contentList") String contentList);
}
