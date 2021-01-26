package hello.webbrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText e1;
WebView  w1;
String  url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.editText);
        w1 = (WebView) findViewById(R.id.webView);
        w1.getSettings().setJavaScriptEnabled(true);
        w1.getSettings().setDomStorageEnabled(true);
        w1.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                w1.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     if(item.getItemId()==R.id.back){
     if(w1.canGoBack()){
         w1.goBack();
     }
     else {
         finish();
     }
     }

        return super.onOptionsItemSelected(item);
    }

    public void action(View v){
url="http://"+ e1.getText().toString();
 w1.loadUrl(url);

    }
}
