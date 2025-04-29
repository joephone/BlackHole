package com.transcendence.greenstar.demo.jscalljava;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.core.app.ActivityCompat;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.greenstar.R;

/**
 * @author joephone
 * @date 2025/4/25 19:49
 * @description
 * @edition 1.0
 */
public class JsCallJavaCallPhoneActivity extends AppAc  {

    private WebView webView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_jscalljava_java_video;
    }

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        //设置支持javaScript脚步语言
        webSettings.setJavaScriptEnabled(true);

        //支持双击-前提是页面要支持才显示
//        webSettings.setUseWideViewPort(true);

        //支持缩放按钮-前提是页面要支持才显示
        webSettings.setBuiltInZoomControls(true);

        //设置客户端-不跳转到默认浏览器中
        webView.setWebViewClient(new WebViewClient());

        //设置支持js调用java
        webView.addJavascriptInterface(new AndroidAndJSInterface(), "Android");

        //加载本地资源
//        webView.loadUrl("http://atguigu.com/teacher.shtml");
        webView.loadUrl("file:///android_asset/JsCallJavaCallPhone.html");
//        webView.loadUrl("http://10.0.2.2:8080/assets/JsCallJavaCallPhone.html");
    }



    class AndroidAndJSInterface {
        /**
         * 该方法将被js调用,用于加载数据
         */
        @JavascriptInterface
        public void showcontacts() {
            // 下面的代码建议在子线程中调用
            String json = "[{\"name\":\"阿福\", \"phone\":\"18600012345\"}]";
            // 调用JS中的方法
            webView.loadUrl("javascript:show('" + json + "')");
        }

        /**
         * 拨打电话
         * @param phone
         */
        @JavascriptInterface
        public void call(String phone) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            if (ActivityCompat.checkSelfPermission(JsCallJavaCallPhoneActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(intent);

        }
    }
}