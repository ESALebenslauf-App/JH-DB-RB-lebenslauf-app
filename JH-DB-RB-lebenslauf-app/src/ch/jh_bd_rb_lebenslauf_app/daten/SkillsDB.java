package ch.jh_bd_rb_lebenslauf_app.daten;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SkillsDB implements LebenslaufDB {

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

	/**
	 * Läd anhand der ID im Objekt bildung die dazugehörigen Daten aus der DB
	 * und füllt diese ins OBjekt ab.
	 * 
	 * @param skills
	 * @return Skills mit Daten aus DB
	 */
	public SkillsData getSkill(SkillsData skills) {
		String[] dbID = new String[1];
		dbID[0] = skills.getID().toString();

		Cursor result = db.query(false, TABLE_SKILLS, PROJECTION_SKILLS,
				SKILLS_ID + "=?", dbID, null, null, null, null);

		if (result.moveToFirst()) {
			return setSkills(skills, result);
		} else {
			result.close();
			return null;
		}
	}

	/**
	 * Es werden alle Skils aus der DB geladen bei denen die where bedingen
	 * erfühlt sind. Die Spallte der Bedingen muss mitangeben werden und der
	 * wert der Bedingen im Skills Objekt.
	 * 
	 * @param skills
	 * @param columWhere
	 *            Colum Name der where abfrage
	 * @return ArrayList<Skills> alle treffer
	 */
	/**
	 * @param skills
	 * @param columWhere
	 * @return
	 */
	public ArrayList<SkillsData> getSkillsRows(SkillsData skills, String columWhere) {
		String[] dbWhere = new String[1];
		String dbColWhere = SKILLS_ID;

		switch (columWhere) {
		case SKILLS_ID:
			dbWhere[0] = skills.getID().toString();
			dbColWhere = SKILLS_ID;
			break;
		case SKILLS_WAS:
			dbWhere[0] = skills.getWas().toString();
			dbColWhere = SKILLS_WAS;
			break;
		case SKILLS_AUSMASS:
			dbWhere[0] = skills.getAusmass().toString();
			dbColWhere = SKILLS_AUSMASS;
			break;
		case SKILLS_ZERTIFIKAT:
			dbWhere[0] = skills.getZertifikat().toString();
			dbColWhere = SKILLS_ZERTIFIKAT;
			break;
		case SKILLS_PERS_ID:
			dbWhere[0] = skills.getPers_id().toString();
			dbColWhere = SKILLS_PERS_ID;
			break;

		default:
			break;
		}

		Cursor result = db.query(false, TABLE_SKILLS, PROJECTION_SKILLS,
				dbColWhere + "=?", dbWhere, null, null, null, null);

		ArrayList<SkillsData> skillsArr = new ArrayList<SkillsData>();
		if (result.moveToFirst()) {
			while (!result.isAfterLast()) {
				SkillsData skill = new SkillsData(result.getLong(0));
				skillsArr.add(setSkills(skill, result));
				result.moveToNext();
			}
		}
		return skillsArr;
	}

	/**
	 * Giebt alle Zeilen in der DB zurück.
	 * 
	 * @return ArrayList<Skills>
	 */
	public ArrayList<SkillsData> getAllSkills() {
		ArrayList<SkillsData> skills = new ArrayList<SkillsData>();

		Cursor result = db.query(false, TABLE_SKILLS, PROJECTION_SKILLS, null,
				null, null, null, null, null);

		if (result.moveToFirst()) {
			while (!result.isAfterLast()) {
				SkillsData skill = new SkillsData(result.getLong(0));
				skills.add(setSkills(skill, result));
				result.moveToNext();
			}
		}
		return skills;
	}

	/**
	 * loescht einen Eintrag in der Tabelle Skills anhand der ID
	 * 
	 * @param skills
	 * @return boolean konnte der Eintrag geloescht werden
	 */
	public boolean deleteSkills(SkillsData skills) {
		String[] dbID = new String[1];
		dbID[0] = skills.getID().toString();
		return db.delete(TABLE_SKILLS, SKILLS_ID + "=?", dbID) > 0;
	}

	/**
	 * erzeugt einen Eintrag in der Tabelle Bildung
	 * 
	 * @param skills
	 * @return Skills mit Daten aus DB abgefuehlt
	 */
	public SkillsData insertSkills(SkillsData skills) {
		ContentValues values = new ContentValues();

		values = putContentValues(skills, values);

		skills.setID(db.insert(TABLE_SKILLS, null, values));
		return skills;
	}

	/**
	 * Das übergebene Bildungs Objekt wird anhand der ID Aktualisiert es werden
	 * alle einträge des Objekts Aktualisiert.
	 * 
	 * @param bildung
	 * @return
	 */
	public SkillsData updateSkills(SkillsData skills) {

		ContentValues values = new ContentValues();
		values = putContentValues(skills, values);

		if (skills.getID() > 0) {
			db.update(TABLE_SKILLS, values, "_id=?", new String[] { skills
					.getID().toString() });

			return getSkill(skills);
		} else {
			return skills;
		}
	}

	private ContentValues putContentValues(SkillsData skills, ContentValues values) {
		values.put(SKILLS_WAS, skills.getWas());
		values.put(SKILLS_AUSMASS, skills.getAusmass());
		values.put(SKILLS_ZERTIFIKAT, skills.getZertifikat());
		values.put(SKILLS_PERS_ID, skills.getPers_id());

		return values;
	}

	private SkillsData setSkills(SkillsData skills, Cursor result) {
		skills.setWas(result.getString(1));
		skills.setAusmass(result.getString(2));
		skills.setZertifikat(result.getString(3));
		skills.setPers_id(result.getLong(4));

		return skills;
	}
}