package com.transcendence.guard.listener;


import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.io.File;

/**
 * @Author Joephone on 2021/2/8 下午 12:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 用于下载监听
 * @Edition 1.0
 * @EditionHistory
 */
public interface XUtilCallBack {

    void onSuccess(ResponseInfo<File> arg0);

    void onFailure(HttpException arg0, String arg1);

    void onLoading(long total, long current, boolean isUpLoading);
}
