package umang.kejriwal.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2;
    Sqlite sqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.editTextTextPersonName);
        e2 = findViewById(R.id.editTextPhone);
        sqlite = new Sqlite(MainActivity.this);
    }

    public void insert(View view) {
        String name = e1.getText().toString();
        String mob = e2.getText().toString();
        sqlite.open();
        sqlite.insert(name,mob);
        sqlite.close();
    }

    public void delete(View view) {
        String name = e1.getText().toString();
        sqlite.open();
        sqlite.delete(name);
        sqlite.close();
    }

    public void update(View view) {
        String name = e1.getText().toString();
        String mob = e2.getText().toString();
        sqlite.open();
        sqlite.update(name,mob);
        sqlite.close();
    }

    public void view(View view) {
        sqlite.open();
        String data = sqlite.viewMyData();
        sqlite.close();
        Dialog d = new Dialog(MainActivity.this);
        d.setTitle("My Data");
        TextView t = new TextView(MainActivity.this);
        t.setText(data);
        t.setTextSize(25);
        d.setContentView(t);
        d.show();
    }
}