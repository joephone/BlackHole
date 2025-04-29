package com.transcendence.core.ui.webview;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.transcendence.core.R;
import com.transcendence.core.base.activity.AppAc;

import java.util.List;


public class MLWebviewActivity extends AppAc {

    private String nTitle = "";

    /**
     * 是否加载本地html
     */
    private boolean bLoadLocal = false;

    private String mLocalPath = "";

    private MLWebView mWebView;

    /**
     * 获取布局 ID
     */
    @Override
    protected int getLayoutId() {
        return 0;
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initView() {

    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        initBundle();

        View layout = inflater.inflate(R.layout.common_title_webview, null);
//        View titleView = layout.findViewById(R.id.title_layout);
//        ((TextView) titleView.findViewById(R.id.common_titleview_text)).setText(nTitle);
//        titleView.findViewById(R.id.common_titleview_btn_close).setVisibility(View.GONE);
//        titleView.findViewById(R.id.common_titleview_btn_left).setOnClickListener(this);
//        View view = titleView.findViewById(R.id.view_margin);
//        setMarginTop(view);
        mWebView = (MLWebView) layout.findViewById(R.id.commwebview);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        mWebView.setWebViewClient(new MyWebViewClient());
        if (bLoadLocal && !TextUtils.isEmpty(mLocalPath)) {
            mWebView.loadUrl(mLocalPath);
        }
        setContentView(layout);

    }




    /**
     * bundle中的key值：conetnt,title,className。都是必填。 flag为选填，默认true(
     * 是否显示title栏的电话，首页按钮)
     */
    private void initBundle() {
        Intent argumentIntent = getIntent();
        Bundle bundeargu = argumentIntent.getBundleExtra("localArgu");
        if (bundeargu != null) {
            this.nTitle = bundeargu.getString("title");
            this.bLoadLocal = bundeargu.getBoolean("local");
            this.mLocalPath = bundeargu.getString("localPath");
        }
    }



    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("tel:")) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
//                if (DeviceUtil.isIntentAvailable(MLWebviewActivity.this, intent)) {
                    MLWebviewActivity.this.startActivity(intent);
//                }
            } else if (url.endsWith(".apk")) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return false;
            } else if (url.startsWith("tmast://appdetails")) {
                if (!checkAppInstalled(MLWebviewActivity.this, "com.tencent.android.qqdownloader")) {
                    super.shouldOverrideUrlLoading(view, url);
//                    DialogUtils.showToast("请先安装应用宝!");
                    showMsg("请先安装应用宝");
                } else {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    return false;
                }
            } else if (url.contains(".apk?")) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return false;
            } else {
                mWebView.loadUrl(url);
            }
            return true;
        }
    }

    private boolean checkAppInstalled(Context context, String pkgName) {
        if (pkgName == null || pkgName.isEmpty()) {
            return false;
        }
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> info = packageManager.getInstalledPackages(0);
        if (info == null || info.isEmpty())
            return false;
        for (int i = 0; i < info.size(); i++) {
            if (pkgName.equals(info.get(i).packageName)) {
                return true;
            }
        }
        return false;
    }
}


