package ch.jh_bd_rb_lebenslauf_app.daten;

public interface SkillsTable {

	String DB_NAME = "lebenslauf";
	int DB_VERSION = 2;

	String TABLE_SKILLS = "Skills";
	
	String FIELD_SKILLSID = "skillsid";
	String FIELD_WAS = "was";
	String FIELD_AUSMASS = "ausmass";
	String FIELD_ZERTIFIKAT ="zertifikat";

	
	

	String[] PROJECTION_FULL = new String[] { FIELD_SKILLSID, FIELD_WAS, FIELD_AUSMASS, FIELD_ZERTIFIKAT };
	
	String SQL_DB_CREATE = "CREATE TABLE Personalien ("
			+ "was not null, "
			+ "ausmass TEXT not null, "
			+ "zertifikat TEXT nol null, "
			+ "skillsid INTEGER PRIMARY KEY);";
}