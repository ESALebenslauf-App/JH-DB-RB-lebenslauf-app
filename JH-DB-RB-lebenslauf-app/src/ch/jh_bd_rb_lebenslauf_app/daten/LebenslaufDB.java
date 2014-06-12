package ch.jh_bd_rb_lebenslauf_app.daten;

public interface LebenslaufDB {

	static String DB_NAME = "lebenslauf";
	static int DB_VERSION = 2;

	// Tabellen definieren
	String TABLE_PERS = "Personalien";
	String TABLE_BERUF = "Berufserfahrung";
	String TABLE_BILDUNG = "Bildung";
	String TABLE_SKILLS = "Skills";
	
	
	// Spalten für Tabelle Personalien definieren
	String PERS_ID = "_id";
	String PERS_ANREDE = "anrede";
	String PERS_NAME = "title";
	String PERS_VORNAME = "text";
	String PERS_STRASSE = "strasse";
	String PERS_PLZ = "plz";
	String PERS_ORT =  "ort";
	String PERS_DATE = "date";
	String PERS_BILD = "bild";
	
	
	// Spalten für Tabelle Berufserfahrung definieren
	String BERUF_ID = "_id";
	String BERUF_FIRMA = "firma";
	String BERUF_TAETIGKEIT = "taetigkeit";
	String BERUF_BESCHREIBUNG = "beschreibung";
	String BERUF_PLZ = "plz";
	String BERUF_ORT =  "ort";
	String BERUF_VON = "von";
	String BERUF_BIS = "bis";
	
	// Spalten für Tabelle Bildung definieren
	String BILDUNG_ID = "_id";
	String BILDUNG_BILDUNGSART = "bildungsart";
	String BILDUNG_SCHULNAME = "schulname";
	String BILDUNG_PLZ = "plz";
	String BILDUNG_ORT =  "ort";
	String BILDUNG_VON = "von";
	String BILDUNG_BIS = "bis";
	String BILDUNG_PERS_ID = "pers_id";
	
	// Spalten für Tabelle Skills definieren
	String SKILLS_ID = "_id";
	String SKILLS_WAS = "was";
	String SKILLS_AUSMASS = "ausmass";
	String SKILLS_ZERTIFIKAT = "zertifikat";

	
	// Projektion auf einzelne Tabellen
	String[] PROJECTION_PERS = new String[] { PERS_ANREDE, PERS_NAME, PERS_VORNAME, PERS_STRASSE, PERS_PLZ, PERS_ORT, PERS_DATE, PERS_BILD};
	String[] PROJECTION_BERUF = new String[] { BERUF_FIRMA, BERUF_TAETIGKEIT, BERUF_BESCHREIBUNG,BERUF_PLZ, BERUF_ORT, BERUF_VON, BERUF_BIS};
	String[] PROJECTION_BILDUNG = new String[] { BILDUNG_BILDUNGSART, BILDUNG_SCHULNAME, BILDUNG_PLZ, BILDUNG_ORT, BILDUNG_VON, BILDUNG_BIS, BILDUNG_PERS_ID};
	String[] PROJECTION_SKILLS = new String[] { SKILLS_WAS, SKILLS_AUSMASS, SKILLS_ZERTIFIKAT};
	
	
	// Tabelle Personalien erstellen
	static String SQL_CREATE_TABLE_PERS = "CREATE TABLE PERSONALIEN("
			+ "PERSID INTEGER PRIMARY key autoincrement, "
			+ "ANREDE TEXT NOT NULL, "
			+ "NAME TEXT NOT NULL, "
			+ "VORNAME TEXT NOT NULL,"
			+ "STRASSE TEXT NOT NULL,"
			+ "PLZ INT NOT NULL,"
			+ "ORT TEXT NOT NULL,"
			+ "GEB INT NOT NULL,"
			+ "BILD TEXT NOT NULL)";
	
	
	// Tabelle Berufserfahrung erstellen
	static String SQL_CREATE_TABLE_BERUF = "CREATE TABLE BERUFSERFAHRUNG("
			+ "BERUFSID INTEGER PRIMARY key autoincrement,"
			+ "FIRMA TEXT NOT NULL,"
			+ "TAETIGKEIT TEXT NOT NULL,"
			+ "BESCHREIBUNG TEXT NOT NULL,"
			+ "PLZ INT NOT NULL,  ORT TEXT NOT NULL,"
			+ "VON TEXT NOT NULL,"
			+ "BIS TEXT NOT NULL,"
			+ "FOREIGN KEY(BERUFSID) REFERENCES PERSONALIEN(PERSID))";
	
	
	// Tabelle Bildung erstellen
	static String SQL_CREATE_TABLE_BILDUNG = "CREATE TABLE " + TABLE_BILDUNG + "("
			+ BILDUNG_ID + " INTEGER PRIMARY key autoincrement,"
			+ BILDUNG_BILDUNGSART + " TEXT NOT NULL,"
			+ BILDUNG_SCHULNAME + " TEXT NOT NULL,"
			+ BILDUNG_PLZ + " INT NOT NULL,"
			+ BILDUNG_ORT + " TEXT NOT NULL,"
			+ BILDUNG_VON + " TEXT NOT NULL,"
			+ BILDUNG_BIS + " TEXT NOT NULL,"
			+ BILDUNG_PERS_ID + " TEXT NOT NULL)";
			
			//+ "FOREIGN KEY(BILDUNGSID) REFERENCES PERSONALIEN(PERSID))";
	
	
	// Tabelle Skills erstellen
	static String SQL_CREATE_TABLE_SKILLS = "CREATE TABLE SKILLS("
			+ "SKILLSID INTEGER PRIMARY key autoincrement,"
			+ "WAS TEXT NOT NULL,"
			+ "AUSMASS INT NOT NULL,"
			+ "ZERTIFIKAT TEXT NOT NULL,"
			+ "FOREIGN KEY(SKILLSID) REFERENCES PERSONALIEN(PERSID))";
}