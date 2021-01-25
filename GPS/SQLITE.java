package com.example.sqlite_apptronix;



import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText e1,e2,e3;
	Sql sq;
	String x;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		sq=new Sql(MainActivity.this);
		
	}
	
	public void insert(View v)
	{
		String name=e1.getText().toString();
		String marks=e2.getText().toString();
		sq.open();
		sq.insert(name,marks);
		sq.close();
		Toast.makeText(this, "Data Inserted Succesfully", Toast.LENGTH_LONG).show();
	}
	public void delete(View v)
	{
		sq.open();
		sq.delete(e3.getText().toString());
		sq.close();
		Toast.makeText(this, "Data Deleted Succesfully", Toast.LENGTH_LONG).show();
	}
	public void update(View v)
	{
		sq.open();
		sq.update(e3.getText().toString(),e1.getText().toString(),e2.getText().toString());
		sq.close();
		Toast.makeText(this, "Data Update Succesfully", Toast.LENGTH_LONG).show();
	}
	public void view(View v)
	{
		sq.open();
		x=sq.view();
		sq.close();
		Dialog d=new Dialog(this);
		d.setTitle("Data");
		TextView tv=new TextView(getApplicationContext());
		tv.setText(x);
		d.setContentView(tv);
		d.show();
	}
	public void login(View v)
	{
		String name=e1.getText().toString();
		String marks=e2.getText().toString();
		Cursor cr=sq.getinfo();
		cr.moveToFirst();
		boolean loginstatus = false;
        do
        {
     	   
     	   if(name.equals(cr.getString(0))&&(marks.equals(cr.getString(1))))
     	   {
     		   loginstatus =true;
     	   }
     	  

        }while(cr.moveToNext());
        if(loginstatus)
        {
     	   Toast.makeText(getBaseContext(), "Login success...", Toast.LENGTH_LONG).show();     
        }      
      
      else{
     
         Toast.makeText(getBaseContext(), "Wrong Credentials", Toast.LENGTH_LONG).show();
         
         }
     	
      }
	}
	
	

	


