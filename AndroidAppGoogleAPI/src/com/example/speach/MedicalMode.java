package com.example.speach;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MedicalMode extends Activity {
	private SpeechRecognizer sr;
	private Intent intent;
	
	private class listener implements RecognitionListener {

		@Override
		public void onBeginningOfSpeech() {
			//System.out.println("onBeginningOfSpeech");
		}

		@Override
		public void onBufferReceived(byte[] arg0) {
			//System.out.println("onBufferReceived");
		}

		@Override
		public void onEndOfSpeech() {
			//System.out.println("onEndOfSpeech");
		}

		@Override
		public void onError(int error) {
			//System.out.println("onError");
			String mError = "";
	        String mStatus = "Error detected";
	        switch (error) {
	        case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:                
	            mError = " network timeout"; 
	            //startListening();
	            break;
	        case SpeechRecognizer.ERROR_NETWORK: 
	            mError = " network" ;
	            //toast("Please check data bundle or network settings");
	            return;
	        case SpeechRecognizer.ERROR_AUDIO: 
	            mError = " audio"; 
	            break;
	        case SpeechRecognizer.ERROR_SERVER: 
	            mError = " server"; 
	            //startListening();
	            break;
	        case SpeechRecognizer.ERROR_CLIENT: 
	            mError = " client"; 
	            break;
	        case SpeechRecognizer.ERROR_SPEECH_TIMEOUT: 
	            mError = " speech time out" ; 
	            break;
	        case SpeechRecognizer.ERROR_NO_MATCH: 
	            mError = " no match" ; 
	            sr.startListening(intent);
	            break;
	        case SpeechRecognizer.ERROR_RECOGNIZER_BUSY: 
	            mError = " recogniser busy" ; 
	            break;
	        case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS: 
	            mError = " insufficient permissions" ; 
	            break;

	        }
	        System.out.println("Error: " +  error + " - " + mError);
		}

		@Override
		public void onEvent(int arg0, Bundle arg1) {
			//System.out.println("onEvent");
		}

		@Override
		public void onPartialResults(Bundle arg0) {
			//System.out.println("onPartialResults");
		}

		@Override
		public void onReadyForSpeech(Bundle arg0) {
			//System.out.println("onReadyForSpeech");
		}

		@Override
		public void onResults(Bundle data) {
			//System.out.println("onResults");
			ArrayList<String> text = data.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
			EditText displayText = (EditText) findViewById(R.id.display_message);
            displayText.append(text.get(0)+"\n");
            sr.startListening(intent);
		}

		@Override
		public void onRmsChanged(float arg0) {
			//System.out.println("onRmsChanged");
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medical_mode);
		sr = SpeechRecognizer.createSpeechRecognizer(this);       
        sr.setRecognitionListener(new listener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.medical_mode, menu);
		return true;
	}
	
	public void clearText(View view) {
		EditText displayText = (EditText) findViewById(R.id.display_message);
        displayText.setText("");
	}
	
	public void stopRecognizer(View view) {
		sr.stopListening();
		sr.cancel();
	}
	
	public void startRecognizer(View view) {
		intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        sr.startListening(intent);
    }
	
//	private void startRecognizerHelper() {
//		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
//        try {
//            startActivityForResult(intent, 1);
//        } catch (ActivityNotFoundException a) {
//            Toast t = Toast.makeText(getApplicationContext(),
//                    "Oopps! Your device doesn't support Speech to Text",
//                    Toast.LENGTH_SHORT);
//            t.show();
//        }
//	}
//	
//	@Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
// 
//        switch (requestCode) {
//        case 1: {
//            if (resultCode == RESULT_OK && null != data) {
//
//                ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                EditText displayText = (EditText) findViewById(R.id.display_message);
//                displayText.append(text.get(0)+"\n");
//                startRecognizerHelper();
//            }
//            break;
//        }
// 
//        }
//    }

}
