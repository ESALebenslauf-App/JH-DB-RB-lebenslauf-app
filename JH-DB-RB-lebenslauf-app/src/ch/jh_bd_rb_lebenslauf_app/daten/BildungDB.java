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

	/**
	 * Läd anhand der ID im Objekt bildung die dazugehörigen Daten aus der DB
	 * und füllt diese ins OBjekt ab.
	 * 
	 * @param bildung
	 * @return Bildung mit Daten aus DB
	 */
	public BildungData getBildung(BildungData bildung) {
		String[] dbID = new String[1];
		dbID[0] = bildung.getID().toString();

		Cursor result = db.query(false, TABLE_BILDUNG, PROJECTION_BILDUNG,
				BILDUNG_ID + "=?", dbID, null, null, null, null);

		if (result.moveToFirst()) {
			return setBildung(bildung, result);
		} else {
			result.close();
			return null;
		}
	}

	/**
	 * Es werden alle Bildungen aus der DB geladen bei denen die where bedingen
	 * erfühlt sind. Die Spallte der Bedingen muss mitangeben werden und der
	 * wert der Bedingen im Bildung Objekt.
	 * 
	 * @param bildung
	 * @param columWhere
	 *            Colum Name der where abfrage
	 * @return ArrayList<Bildung> alle treffer
	 */
	public ArrayList<BildungData> getBildungRows(BildungData bildung, String columWhere) {
		String[] dbWhere = new String[1];
		String dbColWhere = BILDUNG_ID;

		switch (columWhere) {
		case BILDUNG_ID:
			dbWhere[0] = bildung.getID().toString();
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
		case BILDUNG_PERS_ID:
			dbWhere[0] = bildung.getPersID().toString();
			dbColWhere = BILDUNG_PERS_ID;
			break;


		default:
			break;
		}

		Cursor result = db.query(false, TABLE_BILDUNG, PROJECTION_BILDUNG,
				dbColWhere + "=?", dbWhere, null, null, null, null);

		ArrayList<BildungData> bildungen = new ArrayList<BildungData>();
		if (result.moveToFirst()) {
			result.moveToFirst();
			while (!result.isAfterLast()) {
				BildungData resultbildung = new BildungData(result.getLong(0));
				bildungen.add(setBildung(resultbildung, result));
				result.moveToNext();
			}
			return bildungen;
		} else {
			result.close();
			return bildungen;
		}
	}

	/**
	 * Giebt alle Zeilen in der DB zurück.
	 * 
	 * @return ArrayList<Bildung>
	 */
	public ArrayList<BildungData> getAllBildungen() {
		ArrayList<BildungData> bildungen = new ArrayList<BildungData>();

		Cursor result = db.query(false, TABLE_BILDUNG, PROJECTION_BILDUNG,
				null, null, null, null, null, null);

		if (result.moveToFirst()) {
			while (!result.isAfterLast()) {
				BildungData bildung = new BildungData(result.getLong(0));
				bildungen.add(setBildung(bildung, result));
				result.moveToNext();
			}
		}

		return bildungen;
	}

	/**
	 * loescht einen Eintrag in der Tabelle Bildung anhand der ID
	 * 
	 * @param bildung
	 * @return boolean konnte der Eintrag geloescht werden
	 */
	public boolean deleteBildung(BildungData bildung) {
		String[] dbID = new String[1];
		dbID[0] = bildung.getID().toString();
		return db.delete(TABLE_BILDUNG, BILDUNG_ID + "=?", dbID) > 0;
	}

	/**
	 * erzeugt einen Eintrag in der Tabelle Bildung
	 * 
	 * @param bildung
	 * @return Bildung mit Daten aus DB abgefuehlt
	 */
	public BildungData insertBildung(BildungData bildung) {
		ContentValues values = new ContentValues();

		values = putContentValues(bildung, values);

		bildung.setID(db.insert(TABLE_BILDUNG, null, values));
		return bildung;
	}

	/**
	 * Das übergebene Bildungs Objekt wird anhand der ID Aktualisiert es werden
	 * alle einträge des Objekts Aktualisiert.
	 * 
	 * @param bildung
	 * @return
	 */
	public BildungData updateBildung(BildungData bildung) {

		ContentValues values = new ContentValues();
		values = putContentValues(bildung, values);

		if (bildung.getID() > 0) {
			db.update(TABLE_BILDUNG, values, "_id=?", new String[] { bildung
					.getID().toString() });

			return getBildung(bildung);
		} else {
			return bildung;
		}
	}

	/**
	 * @param bildung
	 * @param result
	 * @return Bildung mit Daten aus dem result
	 */
	private BildungData setBildung(BildungData bildung, Cursor result) {
		bildung.setID(result.getLong(0));
		bildung.setAusbildungsart(result.getString(1));
		bildung.setNameschule(result.getString(2));
		bildung.setPlz(result.getString(3));
		bildung.setAdresseSchule(result.getString(4));
		bildung.setDatumVon(result.getString(5));
		bildung.setDatumBis(result.getString(6));
		bildung.setPersID(result.getLong(7));

		return bildung;
	}

	/**
	 * @param bildung
	 * @param values
	 * @return ContentValues mit Daten aus bildung
	 */
	private ContentValues putContentValues(BildungData bildung, ContentValues values) {
		values.put(BILDUNG_BILDUNGSART, bildung.getAusbildungsart());
		values.put(BILDUNG_SCHULNAME, bildung.getNameschule());
		values.put(BILDUNG_PLZ, bildung.getPlz());
		values.put(BILDUNG_ORT, bildung.getAdresseSchule());
		values.put(BILDUNG_VON, bildung.getDatumVon());
		values.put(BILDUNG_BIS, bildung.getDatumBis());
		values.put(BILDUNG_PERS_ID, bildung.getPersID());

		return values;
	}
}