package umang.kejriwal.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    EditText e1;
    WebView w1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        e1 = findViewById(R.id.editText);
        w1 = findViewById(R.id.web);
        w1.getSettings().setJavaScriptEnabled(true);
        w1.loadUrl("http://www.google.com");
        w1.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                w1.loadUrl(""+request.getUrl());
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }

    public void action(View v){
        String url =  e1.getText().toString();
        w1.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if(w1.canGoBack()){
            w1.goBack();
        }
        else{
            super.onBackPressed();
        }

    }
}
