package umang.kejriwal.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2;
    FirebaseAuth auth;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.email);
        e2 = findViewById(R.id.pass);
        auth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Please Wait");
        pd.setCancelable(false);

    }

    public void signup(View view) {
        String email = e1.getText().toString();
        String pass = e2.getText().toString();
        if(email.equals("") || pass.equals("")){
            Toast.makeText(this, "Enter All Data", Toast.LENGTH_SHORT).show();
        }
        else{
            pd.show();
            auth.createUserWithEmailAndPassword(email,pass).
                    addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            pd.dismiss();
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Signup Success", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void login(View view) {
        String email = e1.getText().toString();
        String pass = e2.getText().toString();
        if(email.equals("") || pass.equals("")){
            Toast.makeText(this, "Enter All Data", Toast.LENGTH_SHORT).show();
        }
        else{
            pd.show();
            auth.signInWithEmailAndPassword(email,pass).
                    addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            pd.dismiss();
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void reset(View view) {
        String email = e1.getText().toString();
        if(email.equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else{
            pd.show();
            auth.sendPasswordResetEmail(email).
                    addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            pd.dismiss();
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Reset Email Sent", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Email Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}