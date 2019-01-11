package com.wujie.minewanandroid.ui.activity;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wujie.minewanandroid.BaseActivity;
import com.wujie.minewanandroid.R;
import com.wujie.minewanandroid.presenter.BasePresenter;
import com.wujie.minewanandroid.util.ARouterUtils;

import butterknife.BindView;

/**
 * Time：2019/1/11 0011 上午 11:24
 * Author：WuChen
 * Description：
 **/
@Route(path = ARouterUtils.WebViewPath)
public class WebViewActivity extends BaseActivity {
    @BindView(R.id.web_view)
    WebView mWebView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {
        String webUrl = getIntent().getStringExtra("WebUrl");
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl(webUrl);
    }

}
