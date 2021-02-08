package com.transcendence.guard.utils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.transcendence.guard.listener.XUtilCallBack;

import java.io.File;

/**
 * @Author Joephone on 2021/2/8 0008 下午 1:43
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class DownLoadUtils {

    public void downApk(String url, String targetFile, final XUtilCallBack callBack){
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.download(url, targetFile, new RequestCallBack<File>() {
            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                callBack.onSuccess(responseInfo);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                callBack.onFailure(e,s);
            }
        });
    }
}
