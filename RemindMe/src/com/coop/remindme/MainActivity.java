package com.coop.remindme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_screen);
    }
    
    public void createEvent(View view){
    	setContentView(R.layout.activity_create_event);
    }

}
