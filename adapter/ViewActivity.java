package umang.kejriwal.clouddatabase;

import androidx.appcompat.app.AppCompatActivity;

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

public class ViewActivity extends AppCompatActivity {

    ListView lv;
    MyAdapter adapter;
    ArrayList<String> name,mobile;
    String url = "https://soured-fences.000webhostapp.com/get.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        lv = findViewById(R.id.list);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("mydata");
                    name = new ArrayList<String>();
                    mobile = new ArrayList<String>();
                    for(int i = 0; i<jsonArray.length(); i++){
                        JSONObject jo = jsonArray.getJSONObject(i);
                        name.add(jo.getString("name"));
                        mobile.add(jo.getString("mob"));
                    }
                    adapter = new MyAdapter(ViewActivity.this,name,mobile);
                    lv.setAdapter(adapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            TextView t1 = view.findViewById(R.id.textView3);
                            String mob = t1.getText().toString();
                            Toast.makeText(ViewActivity.this, mob, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }
}