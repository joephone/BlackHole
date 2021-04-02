package com.transcendence.music.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.print.PrintDocumentAdapter;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.utils.L;
import com.transcendence.core.utils.permission.PermissionPool;
import com.transcendence.music.R;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

/**
 * @Author Joephone on 2021/3/26 0026 下午 5:59
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class WebViewActivity extends AppCompatActivity {



    private WebSettings mWebSettings;

    private WebView mWebView;

    private long timeout = 20000;
    private Timer timer = null;

    private String mWebUrl = "";



//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_webview;
//    }

    

    @SuppressLint("JavascriptInterface")  //此注解为前端调用java 方法必备
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_webview);
        mWebView = findViewById(R.id.webView);
        //支持javascript
        mWebSettings = mWebView.getSettings();
//        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//把html中的内容放大webview等宽的一列中


        WebSettings settings = mWebView.getSettings();


        // 设置可以支持缩放
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 1、LayoutAlgorithm.NARROW_COLUMNS ： 适应内容大小
// 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setLoadWithOverviewMode(true);


        mWebView.canGoBack();
        mWebView.canGoForward();


        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mWebView.getSettings().setAppCachePath(WebViewActivity.this.getCacheDir().getAbsolutePath());
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
//        // 设置允许JS弹窗
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        mWebView.addJavascriptInterface(this, "AndroidFunction");


        //设置不用系统浏览器打开,直接显示在当前Webview
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);


        mWebView.loadUrl("https://monster-siren.hypergryph.com/m/music");  //加载页面

        mWebUrl = mWebView.getUrl();


        //针对Android3.0以上系统版本移除问题接口。
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            dealJavascriptLeak();
        }


        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }


            /**
             * API >= 21(Android 5.0.1)回调此方法
             */
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
                L.d( "运行方法 onShowFileChooser");
                // (1)该方法回调时说明版本API >= 21，此时将结果赋值给 mUploadCallbackAboveL，使之 != null
//                takePhoto();

                return true;
            }


            /**
             * 8(Android 2.2) <= API <= 10(Android 2.3)回调此方法
             */
            private void openFileChooser(ValueCallback<Uri> uploadMsg) {
                L.d( "运行方法 openFileChooser-1");
                // (2)该方法回调时说明版本API < 21，此时将结果赋值给 mUploadCallbackBelow，使之 != null
 
//                takePhoto();
            }

            /**
             * 11(Android 3.0) <= API <= 15(Android 4.0.3)回调此方法
             */
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                L.d( "运行方法 openFileChooser-2 (acceptType: " + acceptType + ")");
                // 这里我们就不区分input的参数了，直接用拍照
                openFileChooser(uploadMsg);
            }

            /**
             * 16(Android 4.1.2) <= API <= 20(Android 4.4W.2)回调此方法
             */
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                L.d( "运行方法 openFileChooser-3 (acceptType: " + acceptType + "; capture: " + capture + ")");
                // 这里我们就不区分input的参数了，直接用拍照
                openFileChooser(uploadMsg);
            }
        });


    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void dealJavascriptLeak() {
        mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
        mWebView.removeJavascriptInterface("accessibility");
        mWebView.removeJavascriptInterface("accessibilityTraversal");
    }




    // 监听
    private class MyWebViewClient extends WebViewClient {
//        Dialog progressDialog = ProgressDialog.show(mActivity, null, "加载中");

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {

            WebResourceResponse response = super.shouldInterceptRequest(view, url);
            return response;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            return super.shouldOverrideUrlLoading(view, url);
            L.d("url:"+mWebUrl);
            L.d("url:"+url);
            if (url.startsWith("http:") || url.startsWith("https:")) {
                return super.shouldOverrideUrlLoading(view, url);
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            timer = new Timer();
            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
//                    progressDialog.cancel();
                    timer.cancel();
                    timer.purge();

                }
            };
            timer.schedule(tt, timeout, 1);

        }


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
//            progressDialog.cancel();
            timer.cancel();
            timer.purge();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            System.out.println(error);
            //webView.loadUrl("file:///android_asset/error.html");
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }








//    @Override
//    public void onPermissionsGranted(int requestCode){
//        L.d("onPermissionsGranted:"+requestCode);
//        switch (requestCode){
//            case PermissionPool.WRITE_EXTERNAL_STORAGE:
//                downLoad();
//                break;
//        }
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode){
//        switch (requestCode){
//            case PermissionPool.WRITE_EXTERNAL_STORAGE:
////                DialogUtils.showPermissionWarning(mActivity,"请允许权限通过，否则下载功能无法正常使用");
//                break;
//
//        }
//    }

   


    //记录用户首次点击返回键的时间
    private long mExitTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();//返回上一页面
                return true;
            }else if (System.currentTimeMillis() - mExitTime > 2000) {
                Toast.makeText(WebViewActivity.this,"再按一次返回键退出程序",Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
