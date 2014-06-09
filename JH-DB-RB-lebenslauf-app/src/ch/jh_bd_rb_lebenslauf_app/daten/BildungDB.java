package ch.jh_bd_rb_lebenslauf_app.daten;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BildungDB implements LebenslaufDB{
	
	private final DBHelper dbHelper;
	private SQLiteDatabase db;
	
	public BildungDB(Context context) {
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

	
	// Bildung ausgeben
	public Cursor getBildung(String id) {
		String [] dbID = new String [1];
		dbID[0] = id;
		
	    Cursor result = db.query(false,"Bildung", new String []{"_id", "bildungsart"}, "_id=?", dbID, null, null, null,null);

		//Cursor result = db.rawQuery("SELECT * FROM Bildung WHERE _id = 35;", null);
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
		//return db.query(TABLE_BILDUNG, new String[] { BILDUNG_ID, BILDUNG_BILDUNGSART, BILDUNG_SCHULNAME, BILDUNG_PLZ, BILDUNG_ORT, BILDUNG_VON, BILDUNG_BIS}, null, null, null, null, null);
		return db.rawQuery("SELECT * FROM Bildung", null);
	}
	
	// loescht einen Eintrag in der Tabelle Bildung
		public boolean deleteBildung(long id) {
			return db.delete(TABLE_BILDUNG, BILDUNG_ID + "=" + id, null) > 0;
	}
		
	
	// erzeugt einen Eintrag in der Tabelle Bildung
	public long insertBildung(String anrede, String bildungsart, String schulname, int plz, String ort, String von, String bis) {
		ContentValues values = new ContentValues();
		values.put(BILDUNG_BILDUNGSART, bildungsart);
		values.put(BILDUNG_SCHULNAME, schulname);
		values.put(BILDUNG_PLZ, plz);
		values.put(BILDUNG_ORT, ort);
		values.put(BILDUNG_VON, von);
		values.put(BILDUNG_BIS, bis);
		
		long id = db.insert(TABLE_BILDUNG, null, values);
		return id;
	}

	// aktualisiert einen Eintrag in der Tabelle Bildung
	public boolean updateBildung(long id, ContentValues updates) {
		return db.update(TABLE_BILDUNG, updates, BILDUNG_ID + "=" + id, null) > 0;
	}
}