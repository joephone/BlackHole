package com.transcendence.heima.base;

import com.transcendence.heima.http.Http;
import com.transcendence.heima.http.HttpService;
import com.transcendence.heima.mvp.IModel;


/**
 * Model基类
 *
 * @author gc
 * @since 1.0
 */
public class BaseModel implements IModel {
    protected static HttpService httpService;
    // 初始化HttpService
    static {
        httpService = Http.getHttpService();
    }
}
