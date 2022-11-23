package apptronix.net.webbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDomStorageEnabled(true);
        web.loadUrl("http://www.google.com");
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                web.loadUrl(""+request.getUrl());
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(web.canGoBack()){
            web.goBack();
        }
        else{
            super.onBackPressed();
        }

    }
}