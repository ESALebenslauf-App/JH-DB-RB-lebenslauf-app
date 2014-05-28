package ch.jh_bd_rb_lebenslauf_app.daten;

public interface BerufTable {
	
	String DB_NAME = "lebenslauf";
	int DB_VERSION = 2;

	String TABLE_BERUFSBSERFAHRUNG = "Berufserfahrung";
	
	String FIELD_BERUFSID = "berufsid";
	String FIELD_FIRMA = "firma";
	String FIELD_TAETIGKEIT = "taetigkeit";
	String FIELD_BESCHREIBUNG ="beschreibung";
	String FIELD_BERUFPLZ =  "plz";
	String FIELD_BERUFORT = "ort";
	String FIELD_BERUFVON = "von";
	String FIELD_BERUFBIS = "bis";
	
	

	String[] PROJECTION_FULL = new String[] { FIELD_BERUFSID, FIELD_FIRMA, FIELD_TAETIGKEIT, FIELD_BESCHREIBUNG, FIELD_BERUFPLZ, FIELD_BERUFORT, FIELD_BERUFVON, FIELD_BERUFBIS };
	
	String SQL_DB_CREATE = "CREATE TABLE Personalien ("
			+ "firma not null, "
			+ "taetigkeit TEXT not null, "
			+ "beschreibung TEXT nol null"
			+ "plz INT not null"
			+ "ort TEXT not null, "
			+ "von date not null, "
			+ "bis date not null"
			+ "berufsid INTEGER PRIMARY KEY);";
}