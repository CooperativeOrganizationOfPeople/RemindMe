package com.coop.remindme;

public class Event {
	
	//private variables
	int _id;
	String _name;
	String _event_start;
	String _description;
	String _location;
	String _reminder;
	String _frequency;
	String _category;

	//Empty Constructor
	public Event() {

	}

	// Constructor
	public Event(int id, String name, String event_start, String description, String location, String reminder, String frequency, String category){
		this._id = id;
		this._name = name;
		this._event_start = event_start;
		this._description = description;
		this._location = location;
		this._reminder = reminder;
		this._frequency = frequency;
		this._category = category;
	}

	public Event(String name, String event_start, String description, String location, String reminder, String frequency, String category){
		this._name = name;
		this._event_start = event_start;
		this._description = description;
		this._location = location;
		this._reminder = reminder;
		this._frequency = frequency;
		this._category = category;
	}
	
	//temp version that just takes teh basic info we have in the form.
	public Event(String name, String description, String location, String category){
		this._name = name;
		this._description = description;
		this._location = location;
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
	public String getEventStart(){
		return this._event_start;
	}

	// Set Event Start
	public void setEventStart(String event_start){
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
	public String getReminder(){
		return this._reminder;
	}

	// Set Reminder
	public void setReminder(String reminder){
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