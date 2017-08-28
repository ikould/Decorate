package com.ikould.decorate;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ikould.frame.activity.BaseActivity;
import com.ikould.frame.util.NetWorkUtils;

public class MainActivity extends BaseActivity {

    private static final String LOAD_URL = "http://www.ikould.com/decorate/index.html";
    private WebView mWebView;
    private long    exitTime;

    @Override
    protected void onBaseCreate(Bundle savedInstanceState) {
        setBaseContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mWebView = (WebView) findViewById(R.id.mWebView);
        initWebView();
    }

    /**
     * 初始化WebView
     */
    protected void initWebView() {
        mWebView.getSettings().setJavaScriptEnabled(true);//支持javascript
        mWebView.requestFocus();//触摸焦点起作用mWb_sample_show.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);//取消滚动条
        if (NetWorkUtils.isNetworkAvailable(this)) {
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            Log.d("SampleShowActivity", "initWebView: 有网");
        } else {
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            Log.d("SampleShowActivity", "initWebView: 没网");
        }
        // 开启 DOM storage API 功能
        mWebView.getSettings().setDomStorageEnabled(true);
        //开启 database storage API 功能
        mWebView.getSettings().setDatabaseEnabled(true);
        //开启 Application Caches 功能
        mWebView.getSettings().setAppCacheEnabled(true);
        //load URL
        mWebView.loadUrl(LOAD_URL);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_LONG);
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.destroy();
    }
}
