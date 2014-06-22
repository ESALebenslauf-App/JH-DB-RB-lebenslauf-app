package ch.jh_bd_rb_lebenslauf_app.daten;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */

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

	/**
	 * Läd anhand der ID im Objekt bildung die dazugehörigen Daten aus der DB
	 * und füllt diese ins OBjekt ab.
	 * 
	 * @param bildung
	 * @return Bildung mit Daten aus DB
	 */
	public BerufserfahrungData getBerufserfahrung(BerufserfahrungData berufserfahrung) {
		String[] dbID = new String[1];
		dbID[0] = berufserfahrung.getID().toString();

		Cursor result = db.query(false, TABLE_BERUF, PROJECTION_BERUF, BERUF_ID + "=?", dbID, null, null, null, null);

		if (result.moveToFirst()) {
			return setBerufserfahrung(berufserfahrung, result);
		} else {
			result.close();
			return null;
		}
	}

	/**
	 * erzeugt einen Eintrag in der Tabelle Bildung
	 * 
	 * @param bildung
	 * @return Bildung mit Daten aus DB abgefuehlt
	 */
	public BerufserfahrungData insertBerufserfahrung(BerufserfahrungData berufserfahrung) {
		ContentValues values = new ContentValues();

		values = putContentValues(berufserfahrung, values);

		berufserfahrung.setID(db.insert(TABLE_BERUF, null, values));
		return berufserfahrung;
	}

	/**
	 * loescht einen Eintrag in der Tabelle Berufserfahrung anhand der ID
	 * 
	 * @param bildung
	 * @return boolean konnte der Eintrag geloescht werden
	 */
	public boolean deleteBerufserfahrung(BerufserfahrungData berufserfahrung) {
		String[] dbID = new String[1];
		dbID[0] = berufserfahrung.getID().toString();
		return db.delete(TABLE_BERUF, BERUF_ID + "=?", dbID) > 0;
	}

	/**
	 * Giebt alle Zeilen in der DB zurück.
	 * 
	 * @return ArrayList<BerufserfahrungData>
	 */
	public ArrayList<BerufserfahrungData> getAllBerufserfahrung() {
		ArrayList<BerufserfahrungData> berufserfahrungen = new ArrayList<BerufserfahrungData>();

		Cursor result = db.query(false, TABLE_BERUF, PROJECTION_BERUF, null, null, null, null, null, null);

		if (result.moveToFirst()) {
			while (!result.isAfterLast()) {
				BerufserfahrungData berufserfahrung = new BerufserfahrungData(result.getLong(0));
				berufserfahrungen.add(setBerufserfahrung(berufserfahrung, result));
				result.moveToNext();
			}
		}

		return berufserfahrungen;
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
	public ArrayList<BerufserfahrungData> getBerufserfarungRows(BerufserfahrungData berufserfarung, String columWhere) {
		String[] dbWhere = new String[1];
		String dbColWhere = BERUF_ID;

		switch (columWhere) {
		case BERUF_ID:
			dbWhere[0] = berufserfarung.getID().toString();
			dbColWhere = BERUF_ID;
			break;
		case BERUF_FIRMA:
			dbWhere[0] = berufserfarung.getTxt_firma().toString();
			dbColWhere = BERUF_FIRMA;
			break;
		case BERUF_TITEL:
			dbWhere[0] = berufserfarung.getTxt_titel().toString();
			dbColWhere = BERUF_TITEL;
			break;
		case BERUF_BESCHREIBUNG:
			dbWhere[0] = berufserfarung.getTxt_beschreibung().toString();
			dbColWhere = BERUF_BESCHREIBUNG;
			break;
		case BERUF_ADRESSE:
			dbWhere[0] = berufserfarung.getTxt_adresse().toString();
			dbColWhere = BERUF_ADRESSE;
			break;
		case BERUF_PLZ:
			dbWhere[0] = berufserfarung.getTxt_plz().toString();
			dbColWhere = BERUF_PLZ;
			break;
		case BERUF_ORT:
			dbWhere[0] = berufserfarung.getTxt_ort().toString();
			dbColWhere = BERUF_ORT;
			break;
		case BERUF_VON:
			dbWhere[0] = berufserfarung.getBtnSelectDateVon().toString();
			dbColWhere = BERUF_VON;
			break;
		case BERUF_BIS:
			dbWhere[0] = berufserfarung.getBtnSelectDateBis().toString();
			dbColWhere = BERUF_ID;
			break;
		case BERUF_PERS_ID:
			dbWhere[0] = berufserfarung.getPersID().toString();
			dbColWhere = BERUF_PERS_ID;
			break;

		default:
			break;
		}

		ArrayList<BerufserfahrungData> berufserfahrungen = new ArrayList<BerufserfahrungData>();

		Cursor result = db.query(false, TABLE_BERUF, PROJECTION_BERUF, dbColWhere + "=?", dbWhere, null, null, null,
				null);

		if (result.moveToFirst()) {
			while (!result.isAfterLast()) {
				BerufserfahrungData berufserfahrung = new BerufserfahrungData(result.getLong(0));
				berufserfahrungen.add(setBerufserfahrung(berufserfahrung, result));
				result.moveToNext();
			}
		}
		return berufserfahrungen;
	}

	/**
	 * Das übergebene Berufserfahrung Objekt wird anhand der ID Aktualisiert es
	 * werden alle einträge des Objekts Aktualisiert.
	 * 
	 * @param berufserfahrung
	 * @return
	 */
	public BerufserfahrungData updateBerufserfarung(BerufserfahrungData berufserfahrung) {

		ContentValues values = new ContentValues();
		values = putContentValues(berufserfahrung, values);

		if (berufserfahrung.getID() > 0) {
			db.update(TABLE_BERUF, values, "_id=?", new String[] { berufserfahrung.getID().toString() });

			return getBerufserfahrung(berufserfahrung);
		} else {
			return berufserfahrung;
		}
	}

	private BerufserfahrungData setBerufserfahrung(BerufserfahrungData berufserfahrung, Cursor result) {
		berufserfahrung.setID(result.getLong(0));

		berufserfahrung.setTxt_firma(result.getString(1));
		berufserfahrung.setTxt_titel(result.getString(2));
		berufserfahrung.setTxt_taetigkeit(result.getString(3));
		berufserfahrung.setTxt_beschreibung(result.getString(4));
		berufserfahrung.setTxt_adresse(result.getString(5));
		berufserfahrung.setTxt_plz(result.getString(6));
		berufserfahrung.setTxt_ort(result.getString(7));
		berufserfahrung.setBtnSelectDateVon(result.getString(8));
		berufserfahrung.setBtnSelectDateBis(result.getString(9));
		berufserfahrung.setPersID(result.getLong(10));

		return berufserfahrung;
	}

	/**
	 * @param bildung
	 * @param values
	 * @return ContentValues mit Daten aus bildung
	 */
	private ContentValues putContentValues(BerufserfahrungData berufserfahrung, ContentValues values) {

		values.put(BERUF_FIRMA, berufserfahrung.getTxt_firma());
		values.put(BERUF_TITEL, berufserfahrung.getTxt_titel());
		values.put(BERUF_TAETIGKEIT, berufserfahrung.getTxt_taetigkeit());
		values.put(BERUF_BESCHREIBUNG, berufserfahrung.getTxt_beschreibung());
		values.put(BERUF_ADRESSE, berufserfahrung.getTxt_adresse());
		values.put(BERUF_PLZ, berufserfahrung.getTxt_plz());
		values.put(BERUF_ORT, berufserfahrung.getTxt_ort());
		values.put(BERUF_VON, berufserfahrung.getBtnSelectDateVon());
		values.put(BERUF_BIS, berufserfahrung.getBtnSelectDateBis());
		values.put(BERUF_PERS_ID, berufserfahrung.getPersID());

		return values;
	}
}
