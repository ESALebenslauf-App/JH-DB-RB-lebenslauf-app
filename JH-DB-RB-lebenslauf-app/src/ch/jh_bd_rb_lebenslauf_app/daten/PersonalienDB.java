package ch.jh_bd_rb_lebenslauf_app.daten;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonalienDB implements LebenslaufDB {

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

	/**
	 * erzeugt einen Eintrag in der Tabelle Personalien
	 * 
	 * @param personalien
	 * @return Personalien mit Daten aus DB abgefuehlt
	 */
	public PersonalienData insertPersonalieng(PersonalienData personalien) {
		ContentValues values = new ContentValues();

		values = putContentValues(personalien, values);

		personalien.setID(db.insert(TABLE_PERS, null, values));
		return personalien;
	}

	/**
	 * Das übergebene Personalien Objekt wird anhand der ID Aktualisiert es
	 * werden alle einträge des Objekts Aktualisiert.
	 * 
	 * @param personalien
	 * @return
	 */
	public PersonalienData updatePersonalien(PersonalienData personalien) {

		ContentValues values = new ContentValues();
		values = putContentValues(personalien, values);

		if (personalien.getID() > 0) {
			db.update(TABLE_PERS, values, "_id=?", new String[] { personalien
					.getID().toString() });

			return getPersonalien(personalien);
		} else {
			return personalien;
		}
	}

	/**
	 * loescht einen Eintrag in der Tabelle Personalien anhand der ID
	 * 
	 * @param personalien
	 * @return boolean konnte der Eintrag geloescht werden
	 */
	public boolean deletePersonalien(PersonalienData personalien) {
		String[] dbID = new String[1];
		dbID[0] = personalien.getID().toString();
		return db.delete(TABLE_PERS, PERS_ID + "=?", dbID) > 0;
	}

	/**
	 * Läd anhand der ID im Objekt bildung die dazugehörigen Daten aus der DB
	 * und füllt diese ins OBjekt ab.
	 * 
	 * @param personalien
	 * @return Personalien mit Daten aus DB
	 */
	public PersonalienData getPersonalien(PersonalienData personalien) {
		String[] dbID = new String[1];
		dbID[0] = personalien.getID().toString();

		Cursor result = db.query(false, TABLE_PERS, PROJECTION_PERS, PERS_ID
				+ "=?", dbID, null, null, null, null);

		if (result.moveToFirst()) {
			return setPersonalien(personalien, result);
		} else {
			result.close();
			return null;
		}
	}

	/**
	 * Giebt alle Zeilen in der DB zurück.
	 * 
	 * @return ArrayList<Personalien>
	 */
	public ArrayList<PersonalienData> getAllPersonalien() {
		ArrayList<PersonalienData> personalien = new ArrayList<PersonalienData>();

		Cursor result = db.query(false, TABLE_PERS, PROJECTION_PERS, null,
				null, null, null, null, null);

		if (result.moveToFirst()) {
			while (!result.isAfterLast()) {
				PersonalienData personalie = new PersonalienData(result.getLong(0));
				personalien.add(setPersonalien(personalie, result));
				result.moveToNext();
			}
		}

		return personalien;
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
	public ArrayList<PersonalienData> getPersonalienRows(PersonalienData personalie,
			String columWhere) {
		String[] dbWhere = new String[1];
		String dbColWhere = PERS_ID;

		switch (columWhere) {
		case PERS_ID:
			dbWhere[0] = personalie.getID().toString();
			dbColWhere = PERS_ID;
			break;
		case PERS_ANREDE:
			dbWhere[0] = personalie.getAnrede().toString();
			dbColWhere = PERS_ANREDE;
			break;
		case PERS_NAME:
			dbWhere[0] = personalie.getName().toString();
			dbColWhere = PERS_NAME;
			break;
		case PERS_VORNAME:
			dbWhere[0] = personalie.getVorname().toString();
			dbColWhere = PERS_VORNAME;
			break;
		case PERS_STRASSE:
			dbWhere[0] = personalie.getStrasse().toString();
			dbColWhere = PERS_STRASSE;
			break;
		case PERS_PLZ:
			dbWhere[0] = personalie.getPlz().toString();
			dbColWhere = PERS_PLZ;
			break;
		case PERS_ORT:
			dbWhere[0] = personalie.getOrt().toString();
			dbColWhere = PERS_ORT;
			break;
		case PERS_DATE:
			dbWhere[0] = personalie.getDate().toString();
			dbColWhere = PERS_DATE;
			break;
		case PERS_BILD:
			dbWhere[0] = personalie.getBild().toString();
			dbColWhere = PERS_BILD;
			break;

		default:
			break;
		}

		ArrayList<PersonalienData> personalien = new ArrayList<PersonalienData>();

		Cursor result = db.query(false, TABLE_PERS, PROJECTION_PERS, dbColWhere
				+ "=?", dbWhere, null, null, null, null);

		if (result.moveToFirst()) {
			while (!result.isAfterLast()) {
				PersonalienData pers = new PersonalienData(result.getLong(0));
				personalien.add(setPersonalien(pers, result));
				result.moveToNext();
			}
		}

		return personalien;
	}

	private PersonalienData setPersonalien(PersonalienData personalien, Cursor result) {

		personalien.setAnrede(result.getString(1));
		personalien.setName(result.getString(2));
		personalien.setVorname(result.getString(3));
		personalien.setStrasse(result.getString(4));
		personalien.setPlz(result.getString(5));
		personalien.setOrt(result.getString(6));
		personalien.setDate(result.getString(7));
		personalien.setBild(result.getString(8));

		return personalien;
	}

	private ContentValues putContentValues(PersonalienData personalien,
			ContentValues values) {
		values.put(PERS_ANREDE, personalien.getAnrede());
		values.put(PERS_NAME, personalien.getName());
		values.put(PERS_VORNAME, personalien.getVorname());
		values.put(PERS_STRASSE, personalien.getStrasse());
		values.put(PERS_PLZ, personalien.getPlz());
		values.put(PERS_ORT, personalien.getOrt());
		values.put(PERS_DATE, personalien.getDate());
		values.put(PERS_BILD, personalien.getBild());

		return values;
	}
}
