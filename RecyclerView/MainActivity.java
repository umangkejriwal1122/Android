package net.apptronix.umang.first.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Utils> GetVideoAdapter;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    CustomRecyclerAdapter recyclerViewadapter;

    ProgressBar progressBar;

    String GET_JSON_DATA_HTTP_URL;
    String JSON_NAME = "username";
    String JSON_LINK = "password";
    JsonArrayRequest jsonArrayRequest ;
    RequestQueue requestQueue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewContainer);
        GET_JSON_DATA_HTTP_URL = "https://scurrile-taxes.000webhostapp.com/get.php";
        GetVideoAdapter = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        Load_Data();
    }

    public void Load_Data(){

        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Data_to_Recycler(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "No Videos Available Now", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);
    }

    public void Data_to_Recycler(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            Utils GetVideoAdapter1 = new Utils();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                GetVideoAdapter1.setName(json.getString(JSON_NAME));
                GetVideoAdapter1.setLink(json.getString(JSON_LINK));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetVideoAdapter.add(GetVideoAdapter1);
        }

        recyclerViewadapter = new CustomRecyclerAdapter(GetVideoAdapter, this);

        recyclerView.setAdapter(recyclerViewadapter);
        recyclerViewadapter.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView link = view.findViewById(R.id.username);
                Toast.makeText(MainActivity.this, link.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}