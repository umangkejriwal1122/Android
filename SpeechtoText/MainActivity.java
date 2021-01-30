package net.apptronix.umang.first.speechtotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int SPEECH = 1;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.textView1);
    }

    public void a(View v)
    {
        Intent in = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);    //Constants for supporting speech recognition through starting an Intent=
        in.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-us");
        try {
            startActivityForResult(in, SPEECH);
            t1.setText("");
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Your Device Does not support speech to text", Toast.LENGTH_LONG).show();
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && null != data && requestCode == SPEECH) {
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String x = text.get(0);
            t1.setText(x);
        }
    }
}