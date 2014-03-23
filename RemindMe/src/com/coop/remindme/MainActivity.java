package com.coop.remindme;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	
	static final int DATE_PICKER_ID = 1;
	static final int TIME_PICKER_ID = 2;
	DatabaseHandler db;
	static final List<String> list = new ArrayList<String>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHandler(this);
        list.add("Household");
        list.add("Appointment");
        list.add("Car");
        list.add("Meeting");
        list.add("Health");
        setContentView(R.layout.main_screen);
        
    }
    
    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
        	year = selectedYear;
        	month = selectedMonth + 1;
        	day = selectedDay;
        	((Button)findViewById(R.id.selectDate)).setText(month+"/"+day+"/"+year);
        	
        }
    };
    private TimePickerDialog.OnTimeSetListener pickerListener2 = new TimePickerDialog.OnTimeSetListener(){
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
        	hour = selectedHour;
        	minute = selectedMinute;
        	((Button)findViewById(R.id.selectTime)).setText(hour+":"+minute);
        }
    };
    
    protected Dialog onCreateDialog(int id) {
    	switch(id) {
    	case DATE_PICKER_ID:
    		return new DatePickerDialog(this, pickerListener, year, month, day);
    	case TIME_PICKER_ID:
    		return new TimePickerDialog(this, pickerListener2, hour, minute, false);
    	}
    	return null;
    }
    public void selectDate(View view) {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
    	showDialog(DATE_PICKER_ID);
    }
    public void selectTime(View view) {
    	final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        showDialog(TIME_PICKER_ID);
    }

    
    public void createEvent(View view){
    	setContentView(R.layout.activity_create_event);
    	
    	Spinner spinner1 = (Spinner) findViewById(R.id.spinCat);
    	// Create an ArrayAdapter using the string array and a default spinner layout
    	ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
    	adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Specify the layout to use when the list of choices appears
    	// Apply the adapter to the spinner
    	spinner1.setAdapter(adapter1);
    }
    
    //Return
    public void returnToHome(View view){
    	setContentView(R.layout.main_screen);
    }
    
    public void addEvent(View view){
    	
    	//name
    	EditText inputField = (EditText) findViewById(R.id.nameField);
    	String name = inputField.getText().toString();

    	//description
    	inputField = (EditText) findViewById(R.id.descriptionField);
    	String description = inputField.getText().toString();
    	
    	//location
    	inputField = (EditText) findViewById(R.id.locationField);
    	String location = inputField.getText().toString();

    	//date - TODO

    	//time - TODO
    	
    	//freq - TODO
    	
    	//category
    	Spinner spin = (Spinner) findViewById(R.id.spinCat);
    	String category = spin.getSelectedItem().toString();
    	
    	//reminder
    	spin = (Spinner) findViewById(R.id.spinReminder);
    	String stringReminder = inputField.getText().toString();
    	DateFormat reminder = getDateFormatForReminder(stringReminder);
    	
    	
    	boolean isValid = false;
    	//do error checking here
    	
   
    	//print stuff to show that it's being logged
    	System.out.println(name);
    	System.out.println(description);
    	System.out.println(location);
    	System.out.println(category);
    	System.out.println(reminder);
    	
    	if (isValid) {
    		Event newEvent = new Event(name, description, location, category);
    		//store Event in the db
    	
    	   
    		setContentView(R.layout.main_screen);
    	} else {
    		//put a pop-up here to say something is effed up.
    	}
    	
    }
    
    private DateFormat getDateFormatForReminder(String reminderString){
    	DateFormat output = new DateFormat();
    	return output;
    }

}

