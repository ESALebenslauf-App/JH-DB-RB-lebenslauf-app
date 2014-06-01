package ch.jh_bd_rb_lebenslauf_app.daten;

import java.sql.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LebenslaufDB implements PersonalienTable, BildungTable, BerufTable, SkillsTable {

		private final DbHelper dbHelper;
		private SQLiteDatabase db;

		public LebenslaufDB(Context context) {
			dbHelper = new DbHelper(context);
		}

		public void open() {
			if (db == null || !db.isOpen()) {
				db = dbHelper.getWritableDatabase();
			}
		}

		public void close() {
			dbHelper.close();
		}

		// Listet Personalien auf
		public Cursor getPersonalien(long id) {
			Cursor result = db.query(TABLE_PERSONALIEN, PersonalienTable.PROJECTION_FULL, FIELD_PERSID + "=" + id, null, null, null, null, null);
			boolean found = result.moveToFirst();
			if (found) {
				return result;
			}
			else {
				result.close();
				return null;
			}
		}

		// Listet die Bildung auf
		public Cursor getBildung(long id) {
			Cursor result = db.query(TABLE_BILDUNG, BildungTable.PROJECTION_FULL, FIELD_BILDUNGID + "=" + id, null, null, null, null, null);
			boolean found = result.moveToFirst();
			if (found) {
				return result;
			}
			else {
				result.close();
				return null;
			}
		}
		
		// Listet die Berufserfahrungen auf
		public Cursor getBeruf(long id) {
			Cursor result = db.query(TABLE_BERUFSBSERFAHRUNG, BerufTable.PROJECTION_FULL, FIELD_BERUFSID + "=" + id, null, null, null, null, null);
			boolean found = result.moveToFirst();
			if (found) {
				return result;
			}
			else {
				result.close();
				return null;
			}
		}
		// Listet die Skills auf
		public Cursor getSkills(long id) {
			Cursor result = db.query(TABLE_SKILLS, SkillsTable.PROJECTION_FULL, FIELD_SKILLSID + "=" + id, null, null, null, null, null);
			boolean found = result.moveToFirst();
			if (found) {
				return result;
			}
			else {
				result.close();
				return null;
			}
		}
		
		
		// Listet alles auf -> Zusammenfassung
		public Cursor getAllCursor() {
			return db.query(TABLE_PERSONALIEN, new String[]{ 
					FIELD_NAME, FIELD_VORNAME, FIELD_STRASSE, FIELD_PERSONPLZ, FIELD_PERSONORT, FIELD_GEB, FIELD_BILD}
					, null, null, null, null, null);
		}

		// loescht eine Berufserfahrung in der DB
		public boolean deleteBerufserfahrung(long id) {
			return db.delete(TABLE_BERUFSBSERFAHRUNG, FIELD_BERUFSID + "=" + id, null) > 0;
		}
		
		// loescht eine Bildung in der DB
				public boolean deleteBildung(long id) {
					return db.delete(TABLE_BILDUNG, FIELD_BILDUNGID + "=" + id, null) > 0;
		}
		
		// loescht eine Berufserfahrung in der DB
		public boolean deleteSkills(long id) {
			return db.delete(TABLE_SKILLS, FIELD_SKILLSID + "=" + id, null) > 0;
		}

		
		// erzeugt die Personalien in der DB
		public long insertPersonalien(String name, String vorname, String strasse, String plz, String ort, String geb, String bild) {
			ContentValues values = new ContentValues();
			values.put(FIELD_NAME, name);
			values.put(FIELD_VORNAME, vorname);
			values.put(FIELD_STRASSE, strasse);
			values.put(FIELD_PERSONPLZ, plz);
			values.put(FIELD_PERSONORT, ort);
			values.put(FIELD_GEB, geb);
			values.put(FIELD_BILD, bild);
			long id = db.insert(TABLE_PERSONALIEN, null, values);
			return id;
		}

		// erzeugt die Bildung in der DB
		public long insertBildung(String bildungsart, String schulname, String plz, String ort, String von, String bis) {
			ContentValues values = new ContentValues();
			values.put(FIELD_BILDUGNSART, bildungsart);
			values.put(FIELD_SCHULNAME, schulname);
			values.put(FIELD_BILDUNGPLZ, plz);
			values.put(FIELD_BILDUNGORT, ort);
			values.put(FIELD_BILDUNGVON, von);
			values.put(FIELD_BILDUNGBIS, bis);
			long id = db.insert(TABLE_BILDUNG, null, values);
			return id;
		}		

		// erzeugt die Berufserfahrung in der DB
		public long insertBerufserfahrung(String firma, String taetigkeit, String beschreibung, String plz, String ort, String von, String bis) {
			ContentValues values = new ContentValues();
			values.put(FIELD_FIRMA, firma);
			values.put(FIELD_TAETIGKEIT, taetigkeit);
			values.put(FIELD_BESCHREIBUNG, beschreibung);
			values.put(FIELD_BERUFPLZ, plz);
			values.put(FIELD_BERUFORT, ort);
			values.put(FIELD_BERUFVON, von);
			values.put(FIELD_BERUFBIS, bis);
			long id = db.insert(TABLE_BERUFSBSERFAHRUNG, null, values);
			return id;
		}
	
		
		// erzeugt die Skills in der DB
		public long insertSkills(String was, String schulname, String ausmass, String zertifikat) {
			ContentValues values = new ContentValues();
			values.put(FIELD_WAS, was);
			values.put(FIELD_AUSMASS, ausmass);
			values.put(FIELD_ZERTIFIKAT, zertifikat);
			long id = db.insert(TABLE_SKILLS, null, values);
			return id;
		}

		
		// aktualisiert die Personalien in der DB
		public boolean updatePersonalien(long id, ContentValues updates) {
			return db.update(TABLE_PERSONALIEN, updates, FIELD_PERSID + "=" + id, null) > 0;
		}
		
		// aktualisiert die Bildung in der DB
		public boolean updateBildung(long id, ContentValues updates) {
			return db.update(TABLE_BILDUNG, updates, FIELD_BILDUNGID + "=" + id, null) > 0;
		}
		
		// aktualisiert die Berufserfahrung in der DB
		public boolean updateBerufserfahrung(long id, ContentValues updates) {
			return db.update(TABLE_BERUFSBSERFAHRUNG, updates, FIELD_BERUFSID + "=" + id, null) > 0;
		}
		
		// aktualisiert die Skills in der DB
		public boolean updateSkills(long id, ContentValues updates) {
			return db.update(TABLE_SKILLS, updates, FIELD_SKILLSID + "=" + id, null) > 0;
		}
	}