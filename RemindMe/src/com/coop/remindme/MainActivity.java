package com.coop.remindme;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHandler database;
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
    	
    	Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
    	// Create an ArrayAdapter using the string array and a default spinner layout
    	ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
    	       R.array.Frequency_type, android.R.layout.simple_spinner_item);
    	// Specify the layout to use when the list of choices appears
    	adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Apply the adapter to the spinner
    	spinner1.setAdapter(adapter1);
    }
    
    //Return
    public void returnToHome(View view){
    	setContentView(R.layout.main_screen);
    }
    
    public void addEvent(View view){
    	
    	EditText inputField = (EditText) findViewById(R.id.nameField);
    	String name = inputField.getText().toString();

    	inputField = (EditText) findViewById(R.id.descriptionField);
    	String description = inputField.getText().toString();
    	
    	inputField = (EditText) findViewById(R.id.locationField);
    	String location = inputField.getText().toString();
    	
    	inputField = (EditText) findViewById(R.id.categoryField);
    	String category = inputField.getText().toString();
    	
    	Event newEvent = new Event(name, description, location, category);
   
    	//print stuff to show that it's being logged
    	System.out.println(newEvent._name);
    	System.out.println(newEvent._description);
    	System.out.println(newEvent._location);
    	System.out.println(newEvent._category);
    	
    	Log.v("ERROR", name);
    	Log.v("ERROR", description);
    	Log.v("ERROR", location);
    	Log.v("ERROR", category);
    	
    	//store Event in the db
    	
    }


}

