package com.example.medicalshopmanagemt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WEbone extends AppCompatActivity {
private WebView webView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webone);
        webView2=findViewById(R.id.webview);
        webView2.setWebViewClient(new WebViewClient());
        webView2.loadUrl("http://managemedical.000webhostapp.com/");
        //  webView2.getSettings().setBuiltInZoomControls(true);
        webView2.getSettings().setSupportZoom(true);
        webView2.getSettings().setBuiltInZoomControls(true);
        webView2.getSettings().setDisplayZoomControls(false);
        webView2.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if(webView2.canGoBack())
            webView2.goBack();
        else
            super.onBackPressed();
    }
}
