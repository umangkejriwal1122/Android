package net.apptronix.volleytutorial;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2;
    String user,pass;
    String InsertUrl="http://www.apptronix.net/volley/insert.php";
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        pd=new ProgressDialog(MainActivity.this);

    }

    public void save(View v)
    {
        user=e1.getText().toString();
        pass=e2.getText().toString();
        signup(user,pass);
        pd.setMessage("Please Wait");
        pd.show();
    }

    public void signup(final String user, final String pass) {

        RequestQueue rq= Volley.newRequestQueue(MainActivity.this);

        StringRequest sr=new StringRequest(Request.Method.POST, InsertUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> stringMap = new HashMap<String, String>();
                stringMap.put("user",user);
                stringMap.put("pass",pass);
                return stringMap;
            }
        };
            sr.setShouldCache(false);
        sr.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            rq.add(sr);
    }


    public void login(View v)
    {
        user=e1.getText().toString();
        pass=e2.getText().toString();
        login(user,pass);
        pd.setMessage("Please Wait");
        pd.show();
    }

    public void login(final String user, final String pass) {

        String LoginUrl="http://www.apptronix.net/volley/login.php?user="+user+"&pass="+pass+"";

        RequestQueue rq= Volley.newRequestQueue(MainActivity.this);

        StringRequest sr=new StringRequest(Request.Method.GET, LoginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

          //      Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    String id = jsonObject1.getString("id");
                    String passRecvd = jsonObject1.getString("pass");
                    String userRecvd = jsonObject1.getString("user");
                    Toast.makeText(MainActivity.this, id+userRecvd+passRecvd, Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){

        };
        sr.setShouldCache(false);
        sr.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.add(sr);
    }


}
