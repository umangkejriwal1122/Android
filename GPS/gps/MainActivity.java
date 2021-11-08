package umang.kejriwal.gps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {

    TextView lat, longi, accu, time, tp, cp;
    long it, ft, tt;
    LocationManager lm;
    List<String> list;
    String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    double latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lat = findViewById(R.id.textView);
        longi = findViewById(R.id.textView1);
        accu = findViewById(R.id.textView2);
        time = findViewById(R.id.textView3);
        tp = findViewById(R.id.textView4);
        cp = findViewById(R.id.textView5);
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        checkPermissions();
    }

    @Override
    protected void onResume() {
        StringBuffer sb = new StringBuffer();
        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_COARSE);
        list = lm.getProviders(c, true);
        if (list.isEmpty()) {
            tp.setText("No Providers Available");
        } else {
            for (String x : list) {
                sb.append(x).append(",");
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                lm.requestSingleUpdate(x, MainActivity.this, null);
            }
            tp.setText("Total Providers : "+sb);
        }
        it = SystemClock.uptimeMillis();
        super.onResume();
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        lat.setText("Latitude : "+location.getLatitude());
        longi.setText("Longitude : "+location.getLongitude());
        accu.setText("Accuracy : "+location.getAccuracy());
        cp.setText("Current Provider : "+location.getProvider());
        ft = SystemClock.uptimeMillis();
        tt = (ft - it)/1000;
        time.setText("Time : "+tt+" secs");
        latitude = Double.parseDouble(""+location.getLatitude());
        longitude = Double.parseDouble(""+location.getLongitude());
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

    public void map(View view) {
        Intent i = new Intent(MainActivity.this,MapsActivity.class);
        i.putExtra("a",latitude);
        i.putExtra("b",longitude);
        startActivity(i);
    }
}