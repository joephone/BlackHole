package com.transcendence.heima.http;

import com.transcendence.heima.base.BaseHttpResult;
import com.transcendence.heima.bean.LoginBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 网络请求的接口都在这里
 *
 * @author gc
 * @since 1.0
 */

public interface HttpService {
    /** 登录接口 */
    @FormUrlEncoded
    @POST("device/location/add")
    Observable<BaseHttpResult<LoginBean>> login(@Field("username") String username,
                                                @Field("password") String pwd);


    /** 登录接口 */
    @FormUrlEncoded
    @POST("device/location/add")
    Observable<BaseHttpResult<ResponseBody>> aisFetch(@Field("contentList") String username);
}
