package net.apptronix.umang.first.dateandtimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatePickerDialog date;
    TimePickerDialog time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast toast=new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0,0);
        LayoutInflater inflate=getLayoutInflater();
        View appearance=inflate.inflate(R.layout.toast,(ViewGroup) findViewById(R.id.l1));				//convert xml to java(View Object)
        toast.setView(appearance);
        toast.show();
    }

    public void a(View v)
    {
        date=new DatePickerDialog(this, datecall, 2015, 04, 02);		//default date
        date.show();
    }

    public void b(View v)
    {
        time=new TimePickerDialog(this, timecall, 02, 18, false);		//default time
        time.show();
    }

    private DatePickerDialog.OnDateSetListener datecall=new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            Toast.makeText(MainActivity.this, "The Date Selected is : "+dayOfMonth +"/"+ ++monthOfYear+"/"+ year, Toast.LENGTH_LONG).show();

        }
    };
    private TimePickerDialog.OnTimeSetListener timecall=new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // TODO Auto-generated method stub
            Toast.makeText(MainActivity.this, "The Time Selected is: "+hourOfDay+":"+minute, Toast.LENGTH_LONG).show();
        }
    };
}