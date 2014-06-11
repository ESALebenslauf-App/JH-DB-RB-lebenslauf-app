package ch.jh_bd_rb_lebenslauf_app.daten;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BildungDB implements LebenslaufDB {

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
	public Bildung getBildung(Bildung bildung) {
		String[] dbID = new String[1];
		dbID[0] = bildung.getId().toString();

		Cursor result = db.query(false, TABLE_BILDUNG, PROJECTION_BILDUNG,
				BILDUNG_ID + "=?", dbID, null, null, null, null);
		boolean found = result.moveToFirst();
		if (found) {
			bildung.setAusbildungsart(result.getString(0));
			bildung.setNameschule(result.getString(1));
			bildung.setPlz(result.getString(2));
			bildung.setAdresseSchule(result.getString(3));
			bildung.setDatumVon(result.getString(4));
			bildung.setDatumBis(result.getString(5));
			
			return bildung;
		} else {
			result.close();
			return null;
		}
	}

	//TODO umschreiben auf Bildung Objekt
	public Cursor getAllCursor() {
		Cursor result = db.query(false, TABLE_BILDUNG, PROJECTION_BILDUNG,
				null, null, null, null, null, null);
		return result;
	}

	// loescht einen Eintrag in der Tabelle Bildung
	public boolean deleteBildung(Bildung bildung) {
		String[] dbID = new String[1];
		dbID[0] = bildung.getId().toString();
		return db.delete(TABLE_BILDUNG, BILDUNG_ID + "=?", dbID) > 0;
	}

	// erzeugt einen Eintrag in der Tabelle Bildung
	public Bildung insertBildung(Bildung bildung) {
		ContentValues values = new ContentValues();
		values.put(BILDUNG_BILDUNGSART, bildung.getAusbildungsart());
		values.put(BILDUNG_SCHULNAME, bildung.getNameschule());
		values.put(BILDUNG_PLZ, bildung.getPlz());
		values.put(BILDUNG_ORT, bildung.getAdresseSchule());
		values.put(BILDUNG_VON, bildung.getDatumVon());
		values.put(BILDUNG_BIS, bildung.getDatumBis());

		bildung.setId(db.insert(TABLE_BILDUNG, null, values));
		return bildung;
	}

	//TODO Testen und überarbeiten
	// aktualisiert einen Eintrag in der Tabelle Bildung
	public boolean updateBildung(long id, ContentValues updates) {
		return db.update(TABLE_BILDUNG, updates, BILDUNG_ID + "=" + id, null) > 0;
	}
}