package ch.jh_bd_rb_lebenslauf_app.daten;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "lebenslaufApp.db";
	private static final int DATABASE_VERSION =1;
	
	
	private static final String TABLE_CREATE_PERSONALIEN =""
			+"CREATE TABLE PERSONALIEN("
			+"PERSID integer primary key autoimcrement,"
			+ "ANREDE enum('Herr','Frau') CHARACTER SET utf8,"
			+ "NAME not null,"
			+ "VORNAME TEXT not null,"
			+ "STRASSE TEXT not null,"
			+ "PLZ INT not null,"
			+ "ORT TEXT not null,"
			+ "GEB date not null "
			+ "BILD TEXT not null)";
	
	private static final String TABLE_CREATE_BERUFSERFAHRUNG =""
			+"CREATE TABLE BERUFSERFAHRUNG("
			+"BERUFSID INTEGER PRIMARY KEY) autoimcrement,"
			+ "FIRMA not null,"
			+ "TAETIGKEIT TEXT not null,"
			+ "BESCHREIBUNG TEXT nol null,"
			+ "PLZ INT not null,"
			+ "ORT TEXT not null,"
			+ "VON date not null,"
			+ "BIS date not null"
			+ "BERUFSERFAHRUNGVON int [FOREIGN KEY] references PERSONALIEN)";
	
	
	private static final String TABLE_CREATE_BILDUNG =""
			+"CREATE TABLE BILDUNG("
			+"BILDUNGSID INTEGER PRIMARY KEY autoimcrement,"
			+ "BILDUNGSART enum('Grundbildung','Ausbildung','Weiterbildung') not null,"
			+ "SCHULNAME TEXT not null,"
			+ "PLZ INT not null,"
			+ "ORT TEXT not null,"
			+ "VON DATE not null,"
			+ "BIS DATE not null"
			+ "BILDUNGVON int [FOREIGN KEY] references PERSONALIEN)";
	
	
	private static final String TABLE_CREATE_SKILLS =""
			+"CREATE TABLE SKILLS("
			+"SKILLSID INTEGER PRIMARY KEY autoimcrement,"
			+ "WAS not null,"
			+ "AUSMASS TEXT not null,"
			+ "ZERTIFIKAT TEXT nol null"
			+ "SKILLSVON int [FOREIGN KEY] references PERSONALIEN)";	

	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// erzeugt das DB-Schema
		// hier koennten bereits initiale daten abgefuellt werden
		db.execSQL(TABLE_CREATE_PERSONALIEN);
		db.execSQL(TABLE_CREATE_BERUFSERFAHRUNG);
		db.execSQL(TABLE_CREATE_BILDUNG);
		db.execSQL(TABLE_CREATE_SKILLS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// transform db from old to new version
		Log.w(DBHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS SCANITEM");
	}
}