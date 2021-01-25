package com.umang;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;


import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Practical_SmsActivity extends Activity {
	EditText e1,e2;
	String a,b;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        
        registerReceiver(new BroadcastReceiver() {				//creating broadcast reciever
			
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				// TODO Auto-generated method stub
				switch(getResultCode()){			//checks either send or not
				case Activity.RESULT_OK:Toast.makeText(arg0, "Sent", 5000).show();
				break;
				 default:Toast.makeText(arg0, "Failure", 5000).show();
				break;
				}
			}
		}, new IntentFilter("I sent a msg"));
 registerReceiver(new BroadcastReceiver() {				//creating broadcast reciever
			
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				// TODO Auto-generated method stub
				switch(getResultCode()){			
				case Activity.RESULT_OK:Toast.makeText(arg0, "Delivered", 5000).show();
				break;
				 default:Toast.makeText(arg0, "Failure", 5000).show();
				break;
				}
			}
		}, new IntentFilter("I recvd re"));
    }
   public void send (View v)
   {
	   
	a=e1.getText().toString();
	b=e2.getText().toString();
	
	Intent in=new Intent("I sent a msg");		//sends broadcast
	Intent de=new Intent("I recvd re ");
	PendingIntent sending =PendingIntent.getBroadcast(getApplicationContext(), 0,in , 0);
	PendingIntent delivery =PendingIntent.getBroadcast(getApplicationContext(), 0,de , 0);
	SmsManager sms=SmsManager.getDefault();
	sms.sendTextMessage(a, null, b, sending,delivery);
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
   }


}