package com.transcendence.network.jett.download;

import android.os.AsyncTask;


import com.transcendence.network.jett.ApiSource;
import com.transcendence.network.jett.callback.IError;
import com.transcendence.network.jett.callback.IFailure;
import com.transcendence.network.jett.callback.IRequest;
import com.transcendence.network.jett.callback.ISuccess;
import com.transcendence.network.jett.retrofit.RetrofitCreator;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadHandler {
    private final Map<String, Object> PARAMS;
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String FILENAME;
    private final ApiSource SOURCE;

    public DownloadHandler(Map<String, Object> params, String url,
                           IRequest request, ISuccess success,
                           IFailure failure, IError error,
                           String downloadDir, String extension, String filename,
                           ApiSource source) {
        this.PARAMS = params;
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.FILENAME = filename;
        this.SOURCE = source;
    }

    /**
     * 下载文件
     */
    public final void handleDownload(){
        RetrofitCreator.getRetrofitAPI(SOURCE).download(URL,PARAMS)
        .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    //开始把这次下载的结果保存到文件中,使用线程
                    SaveFileTask task=new SaveFileTask(REQUEST,SUCCESS);
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                            DOWNLOAD_DIR,EXTENSION,response.body(),FILENAME);
                    //如果下载成功了，就告诉用户
                    if(task.isCancelled()){
                        if(REQUEST!=null){
                            REQUEST.onRequestEnd();
                        }
                    }
                }else{
                    if(ERROR!=null){
                        ERROR.onError(response.code(),response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                FAILURE.onFailure();
            }
        });
    }

}










