package my.app.clouddatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.jar.JarEntry;

public class ViewActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> name,mobile;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        lv = findViewById(R.id.list);
        viewMyData();
    }

    private void viewMyData() {
        String api = "https://fusile-rain.000webhostapp.com/get.php";
        RequestQueue requestQueue = Volley.newRequestQueue(ViewActivity.this);
        StringRequest sr = new StringRequest(Request.Method.GET, api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(ViewActivity.this,response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("mydata");
                    name = new ArrayList<String>();
                    mobile = new ArrayList<String>();
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        name.add(jsonObject1.getString("name"));
                        mobile.add(jsonObject1.getString("mob"));
                    }
                    adapter = new MyAdapter(ViewActivity.this,name,mobile);
                    lv.setAdapter(adapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            TextView t1 = view.findViewById(R.id.textView2);
                            String mob = t1.getText().toString();
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+mob));
                            startActivity(intent);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        sr.setShouldCache(false);
        sr.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(sr);
    }
}