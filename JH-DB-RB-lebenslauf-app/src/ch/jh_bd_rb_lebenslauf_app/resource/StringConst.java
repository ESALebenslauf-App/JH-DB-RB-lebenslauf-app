package ch.jh_bd_rb_lebenslauf_app.resource;

public class StringConst {
	
	private static final String PESRID = "persID";
	public static final String DATEN_WURDEN_GESPEICHERT = "Daten wurden gespeichert.";
	public static final String DATEN_WURDEN_NICHT_GESPEICHERT = "Daten wurden nicht gespeichert es muss ein Namen oder ein Vorname eingetragen sein!";
	public static final String IHRE_PRESID = " Ihre Personen ID lautat: ";

	public static String getDatenWurdenNichtGespeichert() {
		return DATEN_WURDEN_NICHT_GESPEICHERT;
	}

	public static String getIhrePresid() {
		return IHRE_PRESID;
	}

	public static String getPesrid() {
		return PESRID;
	}

	public static String getDatenWurdenGespeichert() {
		return DATEN_WURDEN_GESPEICHERT;
	}

}
