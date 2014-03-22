package com.coop.remindme;

import java.text.DateFormat;

public class Event {
	
	//private variables
	int _id;
	String _name;
	DateFormat _event_start;
	String _description;
	String _location;
	DateFormat _reminder;
	String _frequency;
	String _category;

	//Empty Constructor
	public Event() {

	}

	// Constructor
	public Event(int id, String name, DateFormat event_start, String description, String location, DateFormat reminder, String frequency, String category){
		this._id = id;
		this._name = name;
		this._event_start = event_start;
		this._description = description;
		this._location = location;
		this._reminder = reminder;
		this._frequency = frequency;
		this._category = category;
	}

	// Get ID
	public int getID(){
		return this._id;
	}

	// Set ID
	public void setID(int id){
		this._id = id;
	}

	// Get Name
	public String getName(){
		return this._name;
	}

	// Set Name
	public void setName(String name){
		this._name = name;
	}

	// Get Event Start
	public DateFormat getEventStart(){
		return this._event_start;
	}

	// Set Event Start
	public void setEventStart(DateFormat event_start){
		this._event_start = event_start;
	}

	// Get Description
	public String getDescription(){
		return this._description;
	}

	// Set Description
	public void setDescription(String description){
		this._description = description;
	}	

	// Get Location
	public String getLocation(){
		return this._location;
	}

	// Set Location
	public void setLocation(String location){
		this._location = location;
	}

	// Get Reminder
	public DateFormat getReminder(){
		return this._reminder;
	}

	// Set Reminder
	public void setReminder(DateFormat reminder){
		this._reminder = reminder;
	}	

	// Get Frequency
	public String getFrequency(){
		return this._frequency;
	}

	// Set Frequency
	public void setFrequency(String frequency){
		this._frequency = frequency;
	}

	// Get Category
	public String getCategory(){
		return this._category;
	}

	// Set Category
	public void setCategory(String category){
		this._category = category;
	}
}