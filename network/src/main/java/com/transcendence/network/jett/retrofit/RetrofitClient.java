package com.transcendence.network.jett.retrofit;


import com.transcendence.network.jett.HttpMethod;
import com.transcendence.network.jett.callback.IError;
import com.transcendence.network.jett.callback.IFailure;
import com.transcendence.network.jett.callback.IRequest;
import com.transcendence.network.jett.callback.ISuccess;
import com.transcendence.network.jett.callback.RequestCallbacks;
import com.transcendence.network.jett.download.DownloadHandler;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author Joephone on 2019/12/3 16:19
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RetrofitClient {
    private final Map<String, Object> PARAMS;
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    //上传下载
    private final File FILE;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String FILENAME;

    public RetrofitClient(Map<String, Object> params,
                          String url,
                          IRequest request,
                          ISuccess success,
                          IFailure failure,
                          IError error,
                          RequestBody body,
                          File file, String downloadDir, String extension, String filename) {
        this.PARAMS = params;
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;  //    /sdcard/XXXX.ext
        this.EXTENSION = extension;
        this.FILENAME = filename;
    }

    public static RestClientBuilder create() {
        return new RestClientBuilder();
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR);
    }

    /**
     * 开始真实的网络操作   参数HTTP_METHOD.GET  HTTP_METHOD.POST......
     *
     * @param method
     */
    private void request(HttpMethod method) {
        final RetrofitAPI service = RetrofitCreator.getRetrofitAPI();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        //开始进行网络访问
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MultipartBody.FORM, FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData(
                        "file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;

        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }

        //结束网络访问
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

    }

    //各种请求(给用户使用的)
    //各种请求
    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
        new DownloadHandler(PARAMS, URL, REQUEST,
                SUCCESS, FAILURE, ERROR, DOWNLOAD_DIR,
                EXTENSION, FILENAME)
                .handleDownload();
    }

}
