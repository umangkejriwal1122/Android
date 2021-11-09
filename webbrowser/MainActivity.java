package umang.kejriwal.webbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                web.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        web.loadUrl("http://www.google.com");
    }

    @Override
    public void onBackPressed() {
        if(web.canGoBack()){
            web.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}