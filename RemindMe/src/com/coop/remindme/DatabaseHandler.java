package com.coop.remindme;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "RemindMe";

	// Events table name
	private static final String TABLE_EVENTS = "events";
	// Categories table name
	private static final String TABLE_CATEGORIES = "categories";

	// SQL types
	private static final String INTEGER = " INTEGER";
	private static final String TEXT = " TEXT";
	private static final String PRIMARY_KEY = " PRIMARY KEY";
	private static final String DATETIME = "DATETIME";
	/*
	 * Table Format
	 * int id  - Primary Key
	 * text name
	 * datetime start
	 * text description
	 * text location
	 * text category
	 * datetime reminder
	 * text frequency 
	 */
	
	// Events Table Columns names
	private static final String KEY_ID = "id";
	private static final String NAME = "name";
	private static final String START_TIME = "start";
	private static final String DESCRIPTION = "description";
	private static final String LOCATION = "location";
	private static final String CATEGORY = "category";
	private static final String REMINDER = "reminder";
	private static final String FREQUENCY = "frequency";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
				+ KEY_ID +INTEGER +PRIMARY_KEY+"," + NAME +TEXT+ "," + START_TIME +DATETIME+","
				+ DESCRIPTION +TEXT+","+ LOCATION +TEXT+","+ CATEGORY +TEXT+","+ REMINDER 
				+DATETIME+","+ FREQUENCY +TEXT+ ")";
		db.execSQL(CREATE_EVENTS_TABLE);
		String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "(" + NAME+ TEXT + PRIMARY_KEY+")";
		db.execSQL(CREATE_CATEGORIES_TABLE);
		addCategory("Household");
		addCategory("Appointment");
		addCategory("Meeting");
		addCategory("Car");
		addCategory("Health");
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);

		// Create tables again
		onCreate(db);
	}
	
	// Adding new event
	public void addEvents(Event event) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(NAME, event.getName()); // Event Name
		values.put(START_TIME, event.getEventStart().toString());
		values.put(DESCRIPTION, event.getDescription());
		values.put(LOCATION, event.getLocation());
		values.put(CATEGORY, event.getCategory());
		values.put(REMINDER, event.getReminder().toString());
		values.put(FREQUENCY, event.getFrequency());
		
		// Inserting Row
		db.insert(TABLE_EVENTS, null, values);
		db.close(); // Closing database connection
	}
	
	public void addCategory(String category) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues value = new ContentValues();
		value.put(NAME, category);
		db.insert(TABLE_CATEGORIES, null, value);
		db.close();
	}
	
	// Getting single event
	public Event getEvent(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_EVENTS, new String[] { KEY_ID,NAME, START_TIME, 
				DESCRIPTION, LOCATION, CATEGORY, REMINDER, FREQUENCY }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		//Event event = new Event(Integer.parseInt(cursor.getString(0)),
		//		cursor.getString(1), cursor.getString(2));
		
		Event event = new Event();
		event.setID(Integer.parseInt(cursor.getString(0)));
		event.setName(cursor.getString(1));
		//TODO: Figure out how to convert datetimes
		//event.setEventStart(DateFormat.cursor.getString(2));
		event.setDescription(cursor.getString(3));
		event.setLocation(cursor.getString(4));
		event.setCategory(cursor.getString(5));
		//event.setReminder(cursor.getString(6));
		event.setFrequency(cursor.getString(7));
		// return event
		return event;
	}
		
	// Getting All Events
	public List<Event> getAllEvents() {
		List<Event> eventList = new ArrayList<Event>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_EVENTS;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
		do {
			Event event = new Event();
			event.setID(Integer.parseInt(cursor.getString(0)));
			event.setName(cursor.getString(1));
			//TODO: Figure out how to convert datetimes
			//event.setEventStart(DateFormat.cursor.getString(2));
			event.setDescription(cursor.getString(3));
			event.setLocation(cursor.getString(4));
			event.setCategory(cursor.getString(5));
			//event.setReminder(cursor.getString(6));
			event.setFrequency(cursor.getString(7));
			// Adding event to list
			eventList.add(event);
		} while (cursor.moveToNext());
	}

	// return event list
	return eventList;
}
	
	// Getting All Categories
	public List<String> getAllCategories() {
		List<String> categories = new ArrayList<String>();
		// Select All Query
		String selectQuery = "SELECT * FROM " + TABLE_CATEGORIES;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery,  null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				// Adding event to list
				categories.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		// return category list
		return categories;
	}
	
	// Getting categories count
	public int getCategoriesCount() {
		String countQuery = "SELECT * FROM " + TABLE_CATEGORIES;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		// return count
		return cursor.getCount();
	}
	
	// Getting events Count
	public int getEventsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_EVENTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}
	
	// Updating single event
	public int updateEvent(Event event) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(NAME, event.getName()); // Event Name
		values.put(START_TIME, event.getEventStart().toString());
		values.put(DESCRIPTION, event.getDescription());
		values.put(LOCATION, event.getLocation());
		values.put(CATEGORY, event.getCategory());
		values.put(REMINDER, event.getReminder().toString());
		values.put(FREQUENCY, event.getFrequency());

		// updating row
		return db.update(TABLE_EVENTS, values, KEY_ID + " = ?",
			new String[] { String.valueOf(event.getID()) });
	}
	
	// Deleting single event
	public void deleteEvent(Event event) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_EVENTS, KEY_ID + " = ?",
			new String[] { String.valueOf(event.getID()) });
		db.close();
	}
}
