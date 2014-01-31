package com.example.speach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/**
 * This is the first activity that loads on boot.
 * It has three big buttons: Medical Mode, Training Mode, Combined Mode
 * @author Riya
 *
 */
public class MainMenu extends Activity {
	
	/** AUTO GENERATED **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    
    /** AUTO GENERATED **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    
    /**
     * Starts the Medical Mode activity
     * @param view
     */
    public void startMedicalMode(View view) {
    	Intent intent = new Intent(this, MedicalMode.class);
    	startActivity(intent);
    }
    
}
