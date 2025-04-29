package com.transcendence.core.ui.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;



@SuppressLint("SetJavaScriptEnabled")
public class MLWebView extends WebView {

	public MLWebView(Context context) {
		super(context);
		initWebView();
	}

	public MLWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initWebView();
	}

	/**
	 * 功能描述:基本属性设置
	 *
	 */
	@TargetApi(Build.VERSION_CODES.FROYO)
	private void initWebView() {
		setHorizontalScrollBarEnabled(false);
		// setVerticalScrollBarEnabled(false);
		WebSettings webSettings = getSettings();
		getSettings().setJavaScriptEnabled(true);

		if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.FROYO) {
			// Enable plugins
			webSettings.setPluginState(WebSettings.PluginState.ON);
		}

		// Increase the priority of the rendering thread to high
		webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);

		// Enable application caching
		webSettings.setAppCacheEnabled(true);

		// Enable HTML5 local storage and make it persistent
		// webSettings.setDomStorageEnabled(true);
		// webSettings.setDatabaseEnabled(true);
		// webSettings.setDatabasePath("/data/data/" +
		// WebViewActivity.this.getPackageName() + "/databases/");

		// Clear spurious cache data
		clearHistory();
		clearFormData();
		// clearCache(true);
		// webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

		// Set viewing area
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setSavePassword(false);
		webSettings.setSaveFormData(false);
		webSettings.setBuiltInZoomControls(false);
		// Accept cookies
		// CookieSyncManager.createInstance(WebViewActivity.this);
		// CookieManager cookieManager = CookieManager.getInstance();
		// cookieManager.setAcceptCookie(true);

		// Make sure that the webview does not allocate blank space on the side
		// for the scrollbars
		setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
	}
}

