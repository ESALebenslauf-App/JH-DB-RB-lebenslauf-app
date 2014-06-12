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

		if (result.moveToFirst()) {
			return setBildung(bildung, result);
		} else {
			result.close();
			return null;
		}
	}

	// Bildung ausgeben
	public ArrayList<Bildung> getBildungRows(Bildung bildung, String columWhere) {
		String[] dbWhere = new String[1];
		String dbColWhere = BILDUNG_ID;

		switch (columWhere) {
		case BILDUNG_ID:
			dbWhere[0] = bildung.getId().toString();
			dbColWhere = BILDUNG_ID;
			break;
		case BILDUNG_BILDUNGSART:
			dbWhere[0] = bildung.getAusbildungsart().toString();
			dbColWhere = BILDUNG_BILDUNGSART;
			break;
		case BILDUNG_SCHULNAME:
			dbWhere[0] = bildung.getNameschule().toString();
			dbColWhere = BILDUNG_SCHULNAME;
			break;
		case BILDUNG_PLZ:
			dbWhere[0] = bildung.getPlz().toString();
			dbColWhere = BILDUNG_PLZ;
			break;
		case BILDUNG_ORT:
			dbWhere[0] = bildung.getAdresseSchule().toString();
			dbColWhere = BILDUNG_ORT;
			break;
		case BILDUNG_VON:
			dbWhere[0] = bildung.getDatumVon().toString();
			dbColWhere = BILDUNG_VON;
			break;
		case BILDUNG_BIS:
			dbWhere[0] = bildung.getDatumBis().toString();
			dbColWhere = BILDUNG_BIS;
			break;

		default:
			break;
		}

		Cursor result = db.query(false, TABLE_BILDUNG, PROJECTION_BILDUNG,
				dbColWhere + "=?", dbWhere, null, null, null, null);
		
		if (result.moveToFirst()) {
			ArrayList<Bildung> bildungen = new ArrayList<Bildung>();
			result.moveToFirst();
			while (!result.isAfterLast()) {
				Bildung resultbildung = new Bildung(result.getLong(0));
				bildungen.add(setBildung(resultbildung, result));
				result.moveToNext();
			}
			return bildungen;
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

		if (result.moveToFirst()) {
			while (!result.isAfterLast()) {
				Bildung bildung = new Bildung(result.getLong(0));
				bildungen.add(setBildung(bildung, result));
				result.moveToNext();
			}
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

	// TODO Testen und überarbeiten
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