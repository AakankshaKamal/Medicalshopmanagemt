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
    }
}
