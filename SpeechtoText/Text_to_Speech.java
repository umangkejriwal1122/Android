package com.example.text_to_speech;

import java.util.Locale;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	// String[]
	// texts={"What's up Students !","How are You Amit ?","What are You doing?"};
	EditText e1;
	TextToSpeech tts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		e1 = (EditText) findViewById(R.id.editText1);

		tts = new TextToSpeech(MainActivity.this,
				new TextToSpeech.OnInitListener() {

					@Override
					public void onInit(int status) {
						// TODO Auto-generated method stub
						if (status != TextToSpeech.ERROR) {
							tts.setLanguage(Locale.US);
						}
					}
				});
	}

	public void a(View v) {

		// Random r=new Random();
		String text = e1.getText().toString();

		// String random=texts[r.nextInt(3)];
		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onPause();

	}
}
