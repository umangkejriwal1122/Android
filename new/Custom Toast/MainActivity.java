package net.apptronix.umang.first.customtoast;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView t1;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  getSupportActionBar().setTitle("My Custom Toast Message");
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">"+getString(R.string.app_name)+"</font>"));
     //   getSupportActionBar().hide();
     //   getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#123456")));

    }

    public void show(View view) {
        Toast toast = new Toast(MainActivity.this);
        toast.setDuration(Toast.LENGTH_LONG);
      //  toast.setGravity(Gravity.CENTER,100,100);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.toast,null);
        t1 = v.findViewById(R.id.textView);
        img = v.findViewById(R.id.imageView);
        t1.setText("This is my message");
        img.setImageResource(R.drawable.image);
        toast.setView(v);
        toast.show();
    }
}