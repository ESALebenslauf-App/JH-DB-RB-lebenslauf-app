package ch.jh_bd_rb_lebenslauf_app.resource;

public class StringConst {
	
	private static final String PESRID = "persID";
	public static final String DATEN_WURDEN_GESPEICHERT = "Daten wurden gespeichert.";
	public static final String DATEN_WURDEN_NICHT_GESPEICHERT = "Daten wurden nicht gespeichert es muss ein Namen oder ein Vorname eingetragen sein!";
	public static final String IHRE_PRESID = " Ihre Personen ID lautat: ";
	public static final String UPDATE = "update";
	public static final String INSERT = "insert";
	public static final String DATEPICKERVON = "datePickerVon";
	public static final String DATEPICKERBIS = "datePickerBis";
	public static final String DATEPICKERGEBURTSDATUM = "datePickerGeburtsdatum";
	public static final String GEBURTSDATUM = "Geburtsdatum";
	
	

	

	public static String getDatepickergeburtsdatum() {
		return DATEPICKERGEBURTSDATUM;
	}

	public static String getDatepickervon() {
		return DATEPICKERVON;
	}

	public static String getDatepickerbis() {
		return DATEPICKERBIS;
	}

	public static String getUpdate() {
		return UPDATE;
	}

	public static String getInsert() {
		return INSERT;
	}

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
