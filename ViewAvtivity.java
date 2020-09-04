package umang.kejriwal.clouddatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewAvtivity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> name,age;
    String url = "https://umangkejriwal.000webhostapp.com/get.php";
    MyAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_avtivity);
        lv = findViewById(R.id.list);
        RequestQueue rq = Volley.newRequestQueue(ViewAvtivity.this);
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ViewAvtivity.this, response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jo = new JSONObject(response);
                    JSONArray ja = jo.getJSONArray("data");
                    name = new ArrayList<String>();
                    age = new ArrayList<String>();
                    for(int i=0;i<ja.length();i++){
                        JSONObject jsonObject = ja.getJSONObject(i);
                        String n = jsonObject.getString("name");
                        String a = jsonObject.getString("age");
                        name.add(n);
                        age.add(a);
                    }
                    ad = new MyAdapter(ViewAvtivity.this,name,age);
                    lv.setAdapter(ad);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewAvtivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        sr.setShouldCache(false);
        sr.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.add(sr);
    }
}
