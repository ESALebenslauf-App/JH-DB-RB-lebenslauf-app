package ch.jh_bd_rb_lebenslauf_app.daten;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author j.herzig
 *
 */
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
			return setBildung(bildung, result);
		} else {
			result.close();
			return null;
		}
	}



	/**
	 * @return ArrayList<Bildung>
	 */
	public ArrayList<Bildung> getAllBildungen() {
		ArrayList<Bildung> bildungen = new ArrayList<Bildung>();
		
		Cursor result = db.query(false, TABLE_BILDUNG, PROJECTION_BILDUNG,
				null, null, null, null, null, null);

		result.moveToFirst();
		while (! result.isAfterLast()) {
			Bildung bildung = new Bildung(result.getLong(0));
			bildungen.add(setBildung(bildung, result));
			result.moveToNext();
		}
		
		return bildungen;
	}


	/**
	 * loescht einen Eintrag in der Tabelle Bildung
	 * 
	 * @param bildung
	 * @return boolean konnte der Eintrag geloescht werden
	 */
	public boolean deleteBildung(Bildung bildung) {
		String[] dbID = new String[1];
		dbID[0] = bildung.getId().toString();
		return db.delete(TABLE_BILDUNG, BILDUNG_ID + "=?", dbID) > 0;
	}


	/**
	 * erzeugt einen Eintrag in der Tabelle Bildung
	 * 
	 * @param bildung
	 * @return Bildung mit Daten aus DB abgefuehlt
	 */
	public Bildung insertBildung(Bildung bildung) {
		ContentValues values = new ContentValues();
		values.put(BILDUNG_BILDUNGSART, bildung.getAusbildungsart());
		values.put(BILDUNG_SCHULNAME, bildung.getNameschule());
		values.put(BILDUNG_PLZ, bildung.getPlz());
		values.put(BILDUNG_ORT, bildung.getAdresseSchule());
		values.put(BILDUNG_VON, bildung.getDatumVon());
		values.put(BILDUNG_BIS, bildung.getDatumBis());
		values.put(BILDUNG_PERS_ID, bildung.getPersID());

		bildung.setId(db.insert(TABLE_BILDUNG, null, values));
		return bildung;
	}

	//TODO Testen und überarbeiten
	// aktualisiert einen Eintrag in der Tabelle Bildung
	public boolean updateBildung(long id, ContentValues updates) {
		return db.update(TABLE_BILDUNG, updates, BILDUNG_ID + "=" + id, null) > 0;
	}
	
	
	/**
	 * @param bildung
	 * @param result
	 * @return Bildung mit Daten aus dem result
	 */
	private Bildung setBildung(Bildung bildung, Cursor result) {
		bildung.setId(result.getLong(0));
		bildung.setAusbildungsart(result.getString(1));
		bildung.setNameschule(result.getString(2));
		bildung.setPlz(result.getString(3));
		bildung.setAdresseSchule(result.getString(4));
		bildung.setDatumVon(result.getString(5));
		bildung.setDatumBis(result.getString(6));
		bildung.setPersID(result.getLong(7));
		
		return bildung;	
	}
}