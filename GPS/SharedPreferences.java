package com.example.sharedpreferences_umang;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button b1;
	TextView t1,t2,t3;
	EditText e1,e2;
	CheckBox c1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b1=(Button)findViewById(R.id.button1);
		t1=(TextView)findViewById(R.id.textView1);
		t2=(TextView)findViewById(R.id.textView2);
		t3=(TextView)findViewById(R.id.textView3);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		c1=(CheckBox)findViewById(R.id.checkBox1);
		
	}
	public void sign(View v)
	{
		if(c1.isChecked())
		{
			SharedPreferences settings=getSharedPreferences("user", Context.MODE_PRIVATE);		// it will create file "user" to display data..otherwise it will create
																		
			SharedPreferences.Editor editor=settings.edit();		                // for editing value saved in the file user
			editor.putString("username", e1.getText().toString());			// the value of e1 will be stored in username key
			editor.putString("userpass", e2.getText().toString());			// the value of e2 will be stored in userpass key
			editor.commit();												// to save the changes
			e1.setText("");
			e2.setText("");
			
		}
		
	}
	
	public void get(View v)
	{
		SharedPreferences settings=getSharedPreferences("user", Context.MODE_PRIVATE);
		t3.setText(settings.getString("username", "null")+" "+settings.getString("userpass", "null"));		//it will show default value null if no data is present
	}

	

}
