package ch.jh_bd_rb_lebenslauf_app.daten;

public interface BildungTable {

	String DB_NAME = "lebenslauf";
	int DB_VERSION = 2;

	String TABLE_BILDUNG = "Bildung";
	
	String FIELD_BILDUNGID = "bildungsid";
	String FIELD_BILDUGNSART = "bildungsart";
	String FIELD_SCHULNAME = "schulname";
	String FIELD_BILDUNGPLZ =  "plz";
	String FIELD_BILDUNGORT = "ort";
	String FIELD_BILDUNGVON = "von";
	String FIELD_BILDUNGBIS = "bis";
	
	

	String[] PROJECTION_FULL = new String[] { FIELD_BILDUNGID , FIELD_BILDUGNSART, FIELD_SCHULNAME, FIELD_BILDUNGPLZ, FIELD_BILDUNGORT, FIELD_BILDUNGVON, FIELD_BILDUNGBIS };
	
	String SQL_DB_CREATE = "CREATE TABLE Bildung ("
			+ "bildungsart TEXT not null, "
			+ "schulname TEXT not null"
			+ "plz INT not null, "
			+ "ort TEXT not null, "
			+ "von DATE not null"
			+ "bis DATE not nuull"
			+ "bildungsid INTEGER PRIMARY KEY);";
}
