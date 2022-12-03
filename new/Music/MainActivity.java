package umang.kejriwal.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    MediaPlayer mp;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.imageView);
        mp = MediaPlayer.create(MainActivity.this,R.raw.all);
    }

    public void action(View v){
        if(i==0){
            mp.start();
            img.setImageResource(R.mipmap.pause);
            i=1;
        }
        else{
            mp.pause();
            img.setImageResource(R.mipmap.play);
            i=0;
        }

    }
}
