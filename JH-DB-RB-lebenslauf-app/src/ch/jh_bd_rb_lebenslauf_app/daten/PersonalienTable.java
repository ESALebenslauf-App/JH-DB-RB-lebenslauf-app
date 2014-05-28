package ch.jh_bd_rb_lebenslauf_app.daten;

public interface PersonalienTable {

	String DB_NAME = "lebenslauf";
	int DB_VERSION = 2;

	String TABLE_PERSONALIEN = "Personalien";
	
	String FIELD_PERSID = "persid";
	String FIELD_NAME = "name";
	String FIELD_VORNAME = "vorname";
	String FIELD_STRASSE ="strasse";
	String FIELD_PERSONPLZ =  "plz";
	String FIELD_PERSONORT = "ort";
	String FIELD_GEB = "geb";
	String FIELD_BILD = "bild";
	

	String[] PROJECTION_FULL = new String[] { FIELD_PERSID, FIELD_NAME, FIELD_VORNAME, FIELD_STRASSE, FIELD_PERSONPLZ, FIELD_PERSONORT, FIELD_GEB, FIELD_BILD };
	
	String SQL_DB_CREATE = "CREATE TABLE Personalien ("
			+ "name not null, "
			+ "vorname TEXT not null, "
			+ "strasse TEXT not null, "
			+ "plz INT not null, "
			+ "ort TEXT not null, "
			+ "geb date not null, "
			+ "bild TEXT not null"
			+ "persid INTEGER PRIMARY KEY);";
}