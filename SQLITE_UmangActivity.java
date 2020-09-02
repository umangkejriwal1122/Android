package com.umang.c;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SQLITE_UmangActivity extends Activity {
	EditText e1,e2,e3,e4;
	Sql sql;
	String x;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        sql=new Sql(SQLITE_UmangActivity.this);
        
    }
    public void view(View v)
    {
    	try {
			sql.open();
			x=sql.getmydata();
			sql.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dialog d=new Dialog(this);
		d.setTitle("Data");
		TextView tv=new TextView(getApplicationContext());
		tv.setText(x);
		d.setContentView(tv);
		d.show();
    }
    public void save(View v)
    {
    String roll=e1.getText().toString();
    String name=e2.getText().toString();
    String age=e3.getText().toString();
    try {
		sql.open();
		sql.savemydata(roll,name,age);
		sql.close();
		Toast.makeText(this, "Succesfully Added", 5000).show();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Toast.makeText(this, e.toString(), 5000).show();
	}
    }
    public void del(View v)
    {
    	try {
			sql.open();
			sql.deletemydata(e4.getText().toString());
			sql.close();
			Toast.makeText(getApplicationContext(), "Data Deleted Succesfully", 5000).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    public void update(View v)
	{
		try {
			sql.open();
			sql.updatemydata(e4.getText().toString(),e1.getText().toString(),e2.getText().toString(),e3.getText().toString());
			sql.close();
			Toast.makeText(getApplicationContext(), "Data Updated Succesfully", 5000).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
    
    
    
    
    
    
    
    
    
    
}