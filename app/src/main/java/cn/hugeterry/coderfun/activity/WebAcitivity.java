package cn.hugeterry.coderfun.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.concurrent.TimeUnit;

import cn.hugeterry.coderfun.CoderfunCache;
import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.utils.BrowserUtils;
import cn.hugeterry.coderfun.utils.ShareUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/22 20:01
 */
public class WebAcitivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SwipyRefreshLayout swipyRefreshLayout;
    private WebView webView;
    private FrameLayout loadingView;
    private String url;
    private String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        CoderfunCache.isBackFromWebOrImage = true;
        url = getIntent().getStringExtra("url");
        desc = getIntent().getStringExtra("desc");

        loadingView = (FrameLayout) findViewById(R.id.loadingview);
        initToolbar();
        initWebView();
        initSwipyRefreshLayout();
    }

    private void initSwipyRefreshLayout() {
        swipyRefreshLayout = (SwipyRefreshLayout) findViewById(R.id.swipyrefreshlayout);
        swipyRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                Observable.timer(2, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                swipyRefreshLayout.setRefreshing(false);
                            }
                        });
                webView.loadUrl(url);
                loadingView.setVisibility(View.GONE);
            }
        });

    }

    private void initWebView() {
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    loadingView.setVisibility(View.GONE);
                    url = webView.getUrl();
                } else {
                    loadingView.setVisibility(View.VISIBLE);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAppCacheEnabled(true);

        webView.loadUrl(url);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setTitle(desc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_share:
                ShareUtils.shareText(this, webView.getTitle() + " " + webView.getUrl() + " 来自「趣刻」APP");
                break;
            case R.id.action_refresh:
                webView.reload();
                break;
            case R.id.action_copy:
                BrowserUtils.copyToClipBoard(this, webView.getUrl());
                break;
            case R.id.action_open_in_browser:
                BrowserUtils.openInBrowser(this, webView.getUrl());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (webView != null) webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (webView != null) webView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) webView.destroy();
    }

}
