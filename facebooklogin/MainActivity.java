package umang.kejriwal.facebooklogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    LoginButton lb;
    TextView t1;
    CallbackManager cbm;
    String name,email,dob,gender,userID;
    ProfilePictureView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        lb = findViewById(R.id.login_button);
        t1 = findViewById(R.id.text);
        pic = findViewById(R.id.image);
        cbm = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions
                (this, Arrays.asList("public_profile"));
        lb.setReadPermissions(Arrays.asList("public_profile","email"));
        lb.registerCallback(cbm, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(MainActivity.this, "Login Success",
                        Toast.LENGTH_SHORT).show();
                userID = loginResult.getAccessToken().getUserId();
                pic.setProfileId(userID);
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    name = object.getString("name");
                                    email = object.getString("email");
                                    t1.setText(name+"\n"+email+"\n"+gender);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle b = new Bundle();
                b.putString("fields","id,name,email");
                request.setParameters(b);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Login Cancelled",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "No Internet",
                        Toast.LENGTH_SHORT).show();
            }
        });
        /*
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "umang.kejriwal.facebooklogin",
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