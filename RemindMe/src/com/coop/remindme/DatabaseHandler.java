package com.coop.remindme;

import android.content.Context;
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

	// SQL types
	private static final String INTEGER = " INTEGER";
	private static final String TEXT = " TEXT";
	private static final String PRIMARY_KEY = " PRIMARY KEY";
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
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
				+ KEY_ID +INTEGER +PRIMARY_KEY+"," + NAME + TEXT+ ","
				+ DESCRIPTION + TEXT + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);

		// Create tables again
		onCreate(db);
	}
}
