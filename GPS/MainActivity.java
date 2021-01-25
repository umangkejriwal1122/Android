package net.apptronix.gps;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener{

    TextView lat,longi,time,accu,tp,cp;
    long it,ft,tt;
    LocationManager lm;
    List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lat=(TextView)findViewById(R.id.latitude);
        longi=(TextView)findViewById(R.id.longitude);
        time=(TextView)findViewById(R.id.time);
        accu=(TextView)findViewById(R.id.accuracy);
        tp=(TextView)findViewById(R.id.tp);
        cp=(TextView)findViewById(R.id.cp);
        lm=(LocationManager)getSystemService(LOCATION_SERVICE);
    }

    @Override
    protected void onResume() {

        StringBuffer sb=new StringBuffer();
        Criteria c=new Criteria();
        c.setAccuracy(Criteria.ACCURACY_COARSE);
        list=lm.getProviders(c,true);
        if(list.isEmpty())
        {
            tp.setText("No Provider Available");
        }
        else
        {
            for(String x:list)
            {
                sb.append(x).append(",");
                lm.requestSingleUpdate(x,MainActivity.this,null);
            }

            tp.setText("Total Providers :"+sb);
        }

        it= SystemClock.uptimeMillis();

        super.onResume();
    }

    @Override
    public void onLocationChanged(Location location) {

        lat.setText("Latitude : "+String.valueOf(location.getLatitude()));
        longi.setText("Longitude : "+String.valueOf(location.getLongitude()));
        accu.setText("Accuracy : "+String.valueOf(location.getAccuracy()));
        cp.setText("Current Provider : "+String.valueOf(location.getProvider()));
        ft=SystemClock.uptimeMillis();
        tt=(ft-it)/1000;
        time.setText("Time : "+String.valueOf(tt)+" secs");


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
