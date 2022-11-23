package umang.kejriwal.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent i = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(i);
                    finish();
                }
                super.run();
            }
        };
        timer.start();
    }
}
