package ch.jh_bd_rb_lebenslauf_app.daten;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SkillsDB implements LebenslaufDB{
	
	private final DBHelper dbHelper;
	private SQLiteDatabase db;
	
	public SkillsDB(Context context) {
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

	
	// Skills ausgeben
	public Cursor getSkills(long id) {
		Cursor result = db.query(TABLE_SKILLS, LebenslaufDB.PROJECTION_SKILLS, SKILLS_ID + "=" + id, null, null, null, null);
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
		return db.query(TABLE_SKILLS, new String[] { SKILLS_ID, SKILLS_WAS, SKILLS_AUSMASS, SKILLS_ZERTIFIKAT}, null, null, null, null, null);
	}
	
	// loescht einen Eintrag in der Tabelle Skills
		public boolean deleteSkills(long id) {
			return db.delete(TABLE_SKILLS, SKILLS_ID + "=" + id, null) > 0;
	}
		
	
	// erzeugt einen Eintrag in der Tabelle Skills
	public long insertSkills(String was, String ausmass, String zertifikat) {
		ContentValues values = new ContentValues();
		values.put(SKILLS_WAS, was);
		values.put(SKILLS_AUSMASS, ausmass);
		values.put(SKILLS_ZERTIFIKAT, zertifikat);
		
		long id = db.insert(TABLE_SKILLS, null, values);
		return id;
	}

	// aktualisiert einen Eintrag in der Tabelle Skills
	public boolean updateSkills(long id, ContentValues updates) {
		return db.update(TABLE_SKILLS, updates, SKILLS_ID + "=" + id, null) > 0;
	}
}