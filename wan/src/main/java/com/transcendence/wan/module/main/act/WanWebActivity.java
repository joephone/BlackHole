package com.transcendence.wan.module.main.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.widget.ImageView;

import com.transcendence.wan.R;
import com.transcendence.wan.base.act.WanBaseActivity;
import com.transcendence.wan.module.home.model.BannerBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Joephone on 2019/12/17 18:24
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WanWebActivity extends WanBaseActivity {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.iv_forward)
    ImageView ivForward;


    private int mArticleId = -1;
    private String mTitle = "";
    private String mAuthor = "";
    private String mUrl = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);


        mArticleId = getIntent().getIntExtra("articleId", -1);
        mTitle = getIntent().getStringExtra("title");
        mTitle = mTitle == null ? "" : mTitle;
        mAuthor = getIntent().getStringExtra("author");
        mAuthor = mAuthor == null ? "" : mAuthor;
        mUrl = getIntent().getStringExtra("url");
        mUrl = mUrl == null ? "" : mUrl;
        boolean collected = getIntent().getBooleanExtra("collected", false);
        webView.loadUrl(mUrl);
    }

    public static void start(Context context, BannerBean.DataBean item) {
        int articleId = item.getOriginId() != 0 ? item.getOriginId() : item.getId();
        start(context, articleId, item.getTitle(), item.getLink(), item.isCollect());
    }

    public static void start(Context context, int articleId, String title, String url, boolean collected) {
        Intent intent = new Intent(context, WanWebActivity.class);
        intent.putExtra("articleId", articleId);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("collected", collected);
        context.startActivity(intent);

    }
}
