package ch.jh_bd_rb_lebenslauf_app.daten;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BerufserfahrungDB implements LebenslaufDB {

	private final DBHelper dbHelper;
	private SQLiteDatabase db;
	
	public BerufserfahrungDB(Context context) {
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
	public Cursor getBerufserfahrung(long id) {
		Cursor result = db.query(TABLE_BERUF, LebenslaufDB.PROJECTION_BERUF, BERUF_ID + "=" + id, null, null, null, null);
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
		return db.query(TABLE_BERUF, new String[] { BERUF_ID, BERUF_FIRMA, BERUF_TAETIGKEIT, BERUF_BESCHREIBUNG, BERUF_PLZ, BERUF_ORT, BERUF_VON, BERUF_BIS}, null, null, null, null, null);
	}
	
	// loescht einen Eintrag in der Tabelle Personalien
		public boolean deleteBeruf(long id) {
			return db.delete(TABLE_BERUF, BERUF_ID + "=" + id, null) > 0;
	}
		
	
	// erzeugt einen Eintrag in der Tabelle Personalien
	public long insertPersonalien(String firma, String taetigkeit, String beschreibung, int plz, String ort, String von, String bis) {
		ContentValues values = new ContentValues();
		values.put(BERUF_FIRMA, firma);
		values.put(BERUF_TAETIGKEIT, taetigkeit);
		values.put(BERUF_BESCHREIBUNG, beschreibung);
		values.put(BERUF_PLZ, plz);
		values.put(BERUF_ORT, ort);
		values.put(BERUF_VON, von);
		values.put(BERUF_BIS, bis);
		
		long id = db.insert(TABLE_BERUF, null, values);
		return id;
	}

	// aktualisiert einen Eintrag in der Tabelle Personalien
	public boolean updateBeruf(long id, ContentValues updates) {
		return db.update(TABLE_BERUF, updates, BERUF_ID + "=" + id, null) > 0;
	}
}
