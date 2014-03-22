package com.coop.remindme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        DatabaseHandler database;
        
        setContentView(R.layout.main_screen);
    }
    
    public void createEvent(View view){
    	setContentView(R.layout.activity_create_event);
    }
    
    public void addEvent(View button){
    	
    	EditText inputField = (EditText) findViewById(R.id.nameField);
    	String name = inputField.getText().toString();

    	inputField = (EditText) findViewById(R.id.descriptionField);
    	String description = inputField.getText().toString();
    	
    	inputField = (EditText) findViewById(R.id.locationField);
    	String location = inputField.getText().toString();
    	
    	inputField = (EditText) findViewById(R.id.categoryField);
    	String category = inputField.getText().toString();
    	
    	Event newEvent = new Event(name, description, location, category);
    	
    	
    	System.out.println(newEvent._name);
    	System.out.println(newEvent._description);
    	System.out.println(newEvent._location);
    	System.out.println(newEvent._category);
    	
    }

}
