package com.example.apps;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class WebViewActivity extends AppCompatActivity {
    private static final String TAG = "WebViewActivity";
    String name;
    String url;

    @SuppressLint("JavascriptInterface")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        url = extras.getString("url");
        initToolbar();
        WebView myWebView = findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebClient());
        WebSettings settings = myWebView.getSettings();
        myWebView.addJavascriptInterface(this, "Android");
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
//        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        if (Build.VERSION.SDK_INT >= 19) {
            // chromium, enable hardware acceleration
            myWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            myWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        settings.setSupportZoom(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
//        settings.setBuiltInZoomControls(true);

        settings.setAppCacheEnabled(true);
//        settings.setAppCachePath(getCacheDir().getAbsolutePath());
        settings.setDatabaseEnabled(true);
        settings.setSupportMultipleWindows(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        settings.setSaveFormData(true);

        if (new CheckNetwork(WebViewActivity.this).isNetworkAvailable()) {
            myWebView.loadUrl(url);
        } else {
            Toast.makeText(WebViewActivity.this, "No internet connection!", Toast.LENGTH_SHORT).show();
        }

    }

    private class WebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            Log.d(TAG, "onPageStarted url = " + url);
            ShowProgressDialog.Show(WebViewActivity.this);
        }

        @SuppressLint("NewApi")
        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d(TAG, "onPageFinished url = " + url);
            String javascript = "javascript: $('#trmerchantterm').prevAll().hide();";
            view.loadUrl(javascript);
            ShowProgressDialog.Dismiss();
            super.onPageFinished(view, url);
        }

    }

    private void initToolbar() {
        Toolbar toolbar_ep = findViewById(R.id.toolbar);
        toolbar_ep.setTitle(name);
        toolbar_ep.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar_ep);
        toolbar_ep.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar_ep.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }
}
