package com.coop.remindme;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
	
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
        	((Button)findViewById(R.id.selectTime)).setText(String.format("%02d", hour)+":"+String.format("%02d", minute));
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

    	//starttime
    	String eventStart = year+"-"+String.format("%02d",month)+"-"+String.format("%02d",day)+" "+String.format("%02d",hour)+":"+String.format("%02d",minute)+":00";
    	
    	inputField = (EditText) findViewById(R.id.frequencyField);
    	int frequencyNumber = Integer.parseInt(inputField.getText().toString());
    	Spinner spin = (Spinner) findViewById(R.id.spinFreq);
    	String freq = spin.getSelectedItem().toString();
    	String frequency = freq + " " + frequencyNumber;
    	
    	//category
    	spin = (Spinner) findViewById(R.id.spinCat);
    	String category = spin.getSelectedItem().toString();
    	
    	//reminder
    	spin = (Spinner) findViewById(R.id.spinReminder);
    	String stringReminder = spin.getSelectedItem().toString();
    	String reminder = getDateFormatForReminder(eventStart, stringReminder);
    	
    	
    	//debug logging
    	Log.d("Name: ",name);
    	Log.d("Description: ",description);
    	Log.d("Location: ",location);
    	Log.d("Category: ",category);
    	Log.d("Reminder: ",reminder);
    	Log.d("Event Start: ",eventStart);
    	Log.d("Frequency: ",frequency);
    	/*
    	System.out.println(name);
    	System.out.println(description);
    	System.out.println(location);
    	System.out.println(category);
    	System.out.println(reminder);
    	System.out.println(eventStart);
    	System.out.println(frequency);*/
    	
    	
    	boolean isValid = false;
    	
    	//do error checking here
    	isValid = true;
    	
    	//if all fields are ok, create new event, save, and return to home screen
    	if (isValid) {
    		Event newEvent = new Event(name, eventStart, description, location, reminder, frequency, category);
    		//store Event in the db
    		db.addEvents(newEvent);
    	   
    		setContentView(R.layout.main_screen);
    	} else {
    		//put a pop-up here to say something is effed up.
    	}
    	
    }
    
    //helper method to generate DateFormat from string that the form gives us
    private String getDateFormatForReminder(String eventStart, String reminderString){
    	//parse time reminder out of string
    	//calculate difference based on event start
    	//store in output
    	Date start;
    	try{
    		start= dateFormat.parse(eventStart);
    		Calendar cal = Calendar.getInstance();
        	cal.setTime(start);
        	if(reminderString.equals("0 min"))
        		cal.add(Calendar.MINUTE, 0);
        	else if(reminderString.equals("5 min"))
        		cal.add(Calendar.MINUTE, 5);
        	else if(reminderString.equals("15 min"))
        		cal.add(Calendar.MINUTE, 15);
        	else if(reminderString.equals("30 min"))
        		cal.add(Calendar.MINUTE, 30);
        	else if(reminderString.equals("1 hr"))
        		cal.add(Calendar.HOUR, 1);
        	else if(reminderString.equals("2 hr"))
        		cal.add(Calendar.HOUR, 2);
        	else if(reminderString.equals("6 hr"))
        		cal.add(Calendar.HOUR, 6);
        	else if(reminderString.equals("1 day"))
        		cal.add(Calendar.DAY_OF_MONTH, 1);
        	else if(reminderString.equals("1 week"))
        		cal.add(Calendar.WEEK_OF_YEAR, 1);
        	
        	return dateFormat.format(cal.getTime());
    	}
    	catch(ParseException pe)
    	{
    		System.out.println(pe);
    	}
    	return eventStart;
    	
    }

}

