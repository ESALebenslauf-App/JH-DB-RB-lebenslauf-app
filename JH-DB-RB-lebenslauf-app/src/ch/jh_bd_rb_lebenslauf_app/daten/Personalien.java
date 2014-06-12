package ch.jh_bd_rb_lebenslauf_app.daten;

public class Personalien implements Cloneable {
	private Long ID;
	private String anrede;
	private String name;
	private String vorname;
	private String strasse;
	private String plz;
	private String ort;
	private String date;
	private String bild;

	public Personalien(String anrede, String name, String vorname,
			String strasse, String plz, String ort, String date, String bild) {
		super();
		this.anrede = anrede;
		this.name = name;
		this.vorname = vorname;
		this.strasse = strasse;
		this.plz = plz;
		this.ort = ort;
		this.date = date;
		this.bild = bild;
	}

	public Personalien(Long iD) {
		super();
		ID = iD;
	}
	
	public Personalien clone() {
		Personalien theClone = null;
		try {
			theClone = (Personalien) super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return theClone;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getAnrede() {
		return anrede;
	}

	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBild() {
		return bild;
	}

	public void setBild(String bild) {
		this.bild = bild;
	}

}
