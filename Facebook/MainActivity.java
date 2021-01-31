package net.apptronix.www.facebook_login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.nfc.Tag;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    LoginButton lb;
    TextView tv;
    CallbackManager cbm;
 //   ProgressDialog progressDialog;
    String email, name, dob, gender, userId;
    ProfilePictureView profilePictureView;
  //  AccessTokenTracker accessTokenTracker;
  //  String status;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);
        lb = (LoginButton) findViewById(R.id.login_button);
        tv = (TextView) findViewById(R.id.textView);

        profilePictureView = (ProfilePictureView) findViewById(R.id.image);
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));


        lb.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday"));
        //   LoginManager.getInstance().logInWithReadPermissions(
        //         MainActivity.this,
        //        Arrays.asList("public_profile", "email", "user_birthday","user_gender"));
        cbm = CallbackManager.Factory.create();


        lb.registerCallback(cbm, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                tv.setText("Success \n" + loginResult.getAccessToken().getUserId() + "\n" + loginResult.getAccessToken().getToken());
                // the value of e1 will be stored in username key

                userId = loginResult.getAccessToken().getUserId();


                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                Log.v("LoginActivity Response ", response.toString());

                                try {
                                    name = object.getString("name");
                                    email = object.getString("email");
                                    dob = object.getString("birthday");
                                    gender = object.getString("gender");
                                    settings = getSharedPreferences("user", Context.MODE_PRIVATE);        // it will create file "user" to display data..otherwise it will create

                                    SharedPreferences.Editor editor = settings.edit();                        // for editing value saved in the file user
                                    editor.putString("name", name);
                                    editor.putString("email", email);
                                    editor.putString("birthday", dob);
                                    editor.putString("gender", gender);
                                    editor.putString("userid", userId);
                                    editor.commit();

                                    Log.v("Email = ", " " + email);
                                    Toast.makeText(getApplicationContext(), "Name:" + name + "\nEmail:" + email + "\nD.O.B : " + dob
                                            + "\nGender:" + gender, Toast.LENGTH_LONG).show();
                                    profilePictureView.setProfileId(userId);
                                    tv.setText("Name:" + name + "\nEmail:" + email + "\nD.O.B : " + dob
                                            + "\nGender:" +  gender);
                                   startActivity(new Intent(MainActivity.this,Main2Activity.class));
                                  finish();


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                tv.setText("Cancelled");
            }

            @Override
            public void onError(FacebookException error) {
                tv.setText("No Internet");
            }
        });


/*
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "net.apptronix.www.facebook_login",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        */
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        cbm.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}