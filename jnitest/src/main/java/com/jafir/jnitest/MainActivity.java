package com.jafir.jnitest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.jafir.jnilibrary.Hello;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.text)).setText(new Hello().sayHello() + "\n" + Hello.staticSayHello() + "\n" + new Hello().getPerson());
        new Hello().callArray();

//        initWebView();


    }

    private void initWebView() {
        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("https://unstable-notification.store.xflag.com/broadcast_notifications/1");
        webView.loadUrl("https://baidu.com");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String command = "javascript:(function(){document.body.style.backgroundColor=\"#ff00FF\"})()";
                view.loadUrl(command);
            }
        });
    }
}
