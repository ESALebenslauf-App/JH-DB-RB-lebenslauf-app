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
	String BERUF_TITEL = "titel";
	String BERUF_TAETIGKEIT = "taetigkeit";
	String BERUF_BESCHREIBUNG = "beschreibung";
	String BERUF_ADRESSE = "adresse";
	String BERUF_PLZ = "plz";
	String BERUF_ORT =  "ort";
	String BERUF_VON = "von";
	String BERUF_BIS = "bis";
	String BERUF_PERS_ID = "pers_id";
	
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
	String SKILLS_PERS_ID = "pers_id";

	
	// Projektion auf einzelne Tabellen
	String[] PROJECTION_PERS = new String[] { PERS_ID, PERS_ANREDE, PERS_NAME, PERS_VORNAME, PERS_STRASSE, PERS_PLZ, PERS_ORT, PERS_DATE, PERS_BILD};
	String[] PROJECTION_BERUF = new String[] { BERUF_ID, BERUF_FIRMA,BERUF_TITEL, BERUF_TAETIGKEIT, BERUF_BESCHREIBUNG,BERUF_ADRESSE, BERUF_PLZ, BERUF_ORT, BERUF_VON, BERUF_BIS, BERUF_PERS_ID};
	String[] PROJECTION_BILDUNG = new String[] { BILDUNG_ID, BILDUNG_BILDUNGSART, BILDUNG_SCHULNAME, BILDUNG_PLZ, BILDUNG_ORT, BILDUNG_VON, BILDUNG_BIS, BILDUNG_PERS_ID};
	String[] PROJECTION_SKILLS = new String[] { SKILLS_ID, SKILLS_WAS, SKILLS_AUSMASS, SKILLS_ZERTIFIKAT, SKILLS_PERS_ID};
	
	
	// Tabelle Personalien erstellen
	static String SQL_CREATE_TABLE_PERS = "CREATE TABLE " + TABLE_PERS + "("
			+ PERS_ID + " INTEGER PRIMARY key autoincrement, "
			+ PERS_ANREDE + " TEXT NOT NULL,"
			+ PERS_NAME + " TEXT NOT NULL,"
			+ PERS_VORNAME + " TEXT NOT NULL,"
			+ PERS_STRASSE + " TEXT NOT NULL,"
			+ PERS_PLZ + " TEXT NOT NULL,"
			+ PERS_ORT + " TEXT NOT NULL,"
			+ PERS_DATE + " TEXT NOT NULL,"
			+ PERS_BILD + " TEXT NOT NULL)";
			
	
	
	// Tabelle Berufserfahrung erstellen
	static String SQL_CREATE_TABLE_BERUF = "CREATE TABLE " + TABLE_BERUF + "("
			+ BERUF_ID + " INTEGER PRIMARY key autoincrement,"
			+ BERUF_FIRMA + " TEXT NOT NULL,"
			+ BERUF_TITEL + " TEXT NOT NULL,"
			+ BERUF_TAETIGKEIT + " TEXT NOT NULL,"
			+ BERUF_BESCHREIBUNG + " TEXT NOT NULL,"
			+ BERUF_ADRESSE + " TEXT NOT NULL,"
			+ BERUF_PLZ + " TEXT NOT NULL,"
			+ BERUF_ORT + " TEXT NOT NULL,"
			+ BERUF_VON + " TEXT NOT NULL,"
			+ BERUF_BIS + " TEXT NOT NULL,"
			+ BERUF_PERS_ID + " TEXT NOT NULL)";
	
	
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

	

	// Tabelle Skills erstellen
	static String SQL_CREATE_TABLE_SKILLS = "CREATE TABLE " + TABLE_SKILLS + "("
			+ SKILLS_ID + " INTEGER PRIMARY key autoincrement,"
			+ SKILLS_WAS + " TEXT NOT NULL,"
			+ SKILLS_AUSMASS + " TEXT NOT NULL,"
			+ SKILLS_ZERTIFIKAT + " TEXT NOT NULL,"
			+ SKILLS_PERS_ID + " TEXT NOT NULL)";
}