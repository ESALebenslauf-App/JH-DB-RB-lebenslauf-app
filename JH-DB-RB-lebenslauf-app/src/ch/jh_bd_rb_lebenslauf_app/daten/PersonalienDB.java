package ch.jh_bd_rb_lebenslauf_app.daten;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class PersonalienDB implements LebenslaufDB{
	
	private final DBHelper dbHelper;
	private SQLiteDatabase db;
	
	public PersonalienDB(Context context) {
		dbHelper = new DBHelper(context);
	}

	public void open() {
		if (db == null || !db.isOpen()) {
			db = dbHelper.getWritableDatabase();
		}
	}

	public void close() {
		dbHelper.close();
	}

	
	// Personalien ausgeben
	public Cursor getPersonalien(long id) {
		Cursor result = db.query(TABLE_PERS, LebenslaufDB.PROJECTION_PERS, PERS_ID + "=" + id, null, null, null, null);
		boolean found = result.moveToFirst();
		if (found) {
			return result;
		}
		else {
			result.close();
			return null;
		}
	}
	
	public Cursor getAllCursor() {
		return db.query(TABLE_PERS, new String[] { PERS_ID, PERS_ANREDE, PERS_NAME, PERS_VORNAME, PERS_STRASSE, PERS_PLZ, PERS_ORT, PERS_DATE, PERS_BILD}, null, null, null, null, null);
	}
	
	// loescht einen Eintrag in der Tabelle Personalien
		public boolean deletePersonalien(long id) {
			return db.delete(TABLE_PERS, PERS_ID + "=" + id, null) > 0;
	}
		
	
	// erzeugt einen Eintrag in der Tabelle Personalien
	public long insertPersonalien(String anrede, String name, String vorname, String strasse, int plz, String ort, String date, String bild) {
		ContentValues values = new ContentValues();
		values.put(PERS_ANREDE, anrede);
		values.put(PERS_NAME, name);
		values.put(PERS_VORNAME, vorname);
		values.put(PERS_STRASSE, strasse);
		values.put(PERS_PLZ, plz);
		values.put(PERS_ORT, ort);
		values.put(PERS_DATE, date);
		values.put(PERS_BILD, bild);
		
		long id = db.insert(TABLE_PERS, null, values);
		return id;
	}

	// aktualisiert einen Eintrag in der Tabelle Personalien
	public boolean updatePersonalien(long id, ContentValues updates) {
		return db.update(TABLE_PERS, updates, PERS_ID + "=" + id, null) > 0;
	}
}
