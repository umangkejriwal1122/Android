package net.apptronix.www.facebook_login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Main2Activity extends AppCompatActivity implements ImageLoad.Listener {

    TextView tv;
    SharedPreferences settings;
    String name,email,dob,gender,userid;
    Button b1,b2;
    ImageView iv;
    Bitmap bit;
    String url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main2);
        tv=(TextView)findViewById(R.id.textView2);
        b1=(Button)findViewById(R.id.button2);
        b2=(Button)findViewById(R.id.button3);
        iv=(ImageView)findViewById(R.id.imageView);
        settings = getSharedPreferences("user", Context.MODE_PRIVATE);
        name = settings.getString("name", "");
        gender = settings.getString("gender", "");
        dob = settings.getString("birthday", "");
        email = settings.getString("email", "");
        userid = settings.getString("userid", "");
        url="https://graph.facebook.com/" + userid+ "/picture?type=large";
        new ImageLoad(this).execute(url);
        if ((name.equals(""))) {

            b1.setVisibility(View.VISIBLE);

        } else {

            tv.setText("Name:" + name + "\nEmail:" + email + "\nD.O.B : " + dob
                    + "\nGender:" + gender+"\nUser Id:"+userid);
            b2.setVisibility(View.VISIBLE);

        }



    }


    public void a(View v)
    {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
    public void b(View v)
    {
        disconnectFromFacebook();
        SharedPreferences sp=getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed=sp.edit();
        ed.clear();
        ed.commit();
        b2.setVisibility(View.GONE);
        b1.setVisibility(View.VISIBLE);
        tv.setText("");
    }


    public void disconnectFromFacebook() {

        LoginManager.getInstance().logOut();
    }


    @Override
    public void onImageLoaded(Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }

    @Override
    public void onError() {

    }
}

