package net.apptronix.umang.first.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button b1;
    String[] permission ={Manifest.permission.CALL_PHONE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button);
        checkPermissions();
    }

    public void action(View view) {
        //sendEmail();
        //dial();
        //call("8470968612");
        //openCamera();
        openVideo();
    }

    public void openCamera(){
        Intent i = new Intent();
        i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(i);
    }

    public void openVideo(){
        Intent i = new Intent();
        i.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivity(i);
    }

    public void call(String num){
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:"+num));
        startActivity(i);
    }

    public void dial(){
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:8470968612"));
        startActivity(i);
    }

    public void sendEmail(){
        b1.setText("Send Email");
        Intent i = new Intent(Intent.ACTION_SEND);
        String emails = "umang20072007@gmail.com";
        i.putExtra(Intent.EXTRA_EMAIL,new String[]{emails});
        i.putExtra(Intent.EXTRA_SUBJECT,"Test Mail");
        i.putExtra(Intent.EXTRA_TEXT,"This is a test email sent through Implicit Intent");
        i.setType("message/rfc822");
        startActivity(Intent.createChooser(i,"Choose an Email App to Send"));
    }

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permission) {
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
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // do something
            }
            return;
        }
    }
}