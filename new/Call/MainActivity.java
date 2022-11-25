package my.app.call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText e1;
    String[] permissions = new String[]{Manifest.permission.CALL_PHONE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.editTextPhone);
        checkPermissions();
    }

    public void call(View v){
        String num = e1.getText().toString();
        if(num.equals("") || num.length()!=10){
            e1.setError("Please Enter Correct 10 digit Mobile Number");
        }else{
            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+num));
            startActivity(i);
        }
    }

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(MainActivity.this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(MainActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // do something
            }
            return;
        }
    }

    public void email(View view) {
        String[] emails =  new String[]{"umang20072007@gmail.com," +
                "kejriwal.umang@gmail.com,abc@gmail.com"};
        Intent i = new Intent(Intent.ACTION_SEND,Uri.parse("mailto:"));
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_EMAIL,emails);
        i.putExtra(Intent.EXTRA_SUBJECT,"Test Email");
        i.putExtra(Intent.EXTRA_TEXT,"This is a test email sent through my app");
        startActivity(i);
    }
}