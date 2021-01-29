package com.transcendence.wan.module.main.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.core.utils.StatusBarUtils;
import com.transcendence.wan.R;
import com.transcendence.wan.module.home.model.BannerBean;

/**
 * @author Joephone on 2019/12/17 18:24
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WanWebActivity extends AppCompatActivity implements View.OnClickListener{


    private WebView mWebView;
    private ImageView ivBack;
    private ImageView ivMenu;
    private ImageView ivForward;
    private TextView tvTitle;
    private FrameLayout flBack;

    private int mArticleId = -1;
    private String mTitle = "";
    private String mAuthor = "";
    private String mUrl = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        StatusBarUtils.with(WanWebActivity.this).init();

        mWebView = findViewById(R.id.webView);
        flBack = findViewById(R.id.flBack);
        flBack.setOnClickListener(this);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        ivMenu = findViewById(R.id.iv_menu);
        ivMenu.setOnClickListener(this);
        ivForward = findViewById(R.id.iv_forward);
        ivForward.setOnClickListener(this);
        tvTitle = findViewById(R.id.tvTitle);


        mArticleId = getIntent().getIntExtra("articleId", -1);
        mTitle = getIntent().getStringExtra("title");
        mTitle = mTitle == null ? "" : mTitle;
        mAuthor = getIntent().getStringExtra("author");
        tvTitle.setText(mTitle = mTitle == null ? "" : mTitle);
        mUrl = getIntent().getStringExtra("url");
        mUrl = mUrl == null ? "" : mUrl;
//        boolean collected = getIntent().getBooleanExtra("collected", false);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.loadUrl(mUrl);
    }
//
//    public static void start(Context context, BannerBean.DataBean item) {
//        int articleId = item.getOriginId() != 0 ? item.getOriginId() : item.getId();
//        start(context, articleId, item.getTitle(), item.getLink(), item.isCollect());
//    }

    public static void start(Context context, int articleId, String title, String url,String author, boolean collected) {
        Intent intent = new Intent(context, WanWebActivity.class);
        intent.putExtra("articleId", articleId);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("author", author);
        intent.putExtra("collected", collected);
        context.startActivity(intent);
    }


    public static void start(Context context, String url) {
        start(context,url,"");
    }

    public static void start(Context context, String url,String title) {
        Intent intent = new Intent(context, WanWebActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    public static void start(Context context, BannerBean item) {
        Intent intent = new Intent(context, WanWebActivity.class);
        intent.putExtra("title", item.getDesc());
        intent.putExtra("url", item.getUrl());
        context.startActivity(intent);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.flBack:
            case R.id.iv_back:
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.iv_menu:

                break;
            case R.id.iv_forward:
                if (mWebView.canGoForward()) {
                    mWebView.goForward();
                }
                break;
        }
    }


}
