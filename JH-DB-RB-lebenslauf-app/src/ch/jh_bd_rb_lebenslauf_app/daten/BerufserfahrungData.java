package ch.jh_bd_rb_lebenslauf_app.daten;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */

public class BerufserfahrungData implements Cloneable {

	private Long ID;
	private Long persID;
	private String txt_firma;
	private String txt_titel;
	private String txt_adresse;
	private String txt_plz;
	private String txt_ort;
	private String txt_taetigkeit;
	private String btnSelectDateVon;
	private String btnSelectDateBis;
	private String txt_beschreibung;

	public BerufserfahrungData(String txt_firma, String txt_titel, String txt_taetigkeit, String txt_beschreibung,
			String txt_adresse, String txt_plz, String txt_ort, String btnSelectDateVon, String btnSelectDateBis) {
		super();
		this.txt_firma = txt_firma;
		this.txt_titel = txt_titel;
		this.txt_taetigkeit = txt_taetigkeit;
		this.txt_beschreibung = txt_beschreibung;
		this.txt_adresse = txt_adresse;
		this.txt_plz = txt_plz;
		this.txt_ort = txt_ort;
		this.btnSelectDateVon = btnSelectDateVon;
		this.btnSelectDateBis = btnSelectDateBis;
	}

	public BerufserfahrungData() {
		
	}

	public BerufserfahrungData(Long id) {
		this.ID = id;
	}

	public BerufserfahrungData clone() {
		BerufserfahrungData theClone = null;
		try {
			theClone = (BerufserfahrungData) super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return theClone;
	}

	public String getTxt_firma() {
		return txt_firma;
	}

	public void setTxt_firma(String txt_firma) {
		this.txt_firma = txt_firma;
	}

	public String getTxt_titel() {
		return txt_titel;
	}

	public void setTxt_titel(String txt_titel) {
		this.txt_titel = txt_titel;
	}

	public String getTxt_adresse() {
		return txt_adresse;
	}

	public void setTxt_adresse(String txt_adresse) {
		this.txt_adresse = txt_adresse;
	}

	public String getTxt_plz() {
		return txt_plz;
	}

	public void setTxt_plz(String txt_plz) {
		this.txt_plz = txt_plz;
	}

	public String getTxt_ort() {
		return txt_ort;
	}

	public void setTxt_ort(String txt_ort) {
		this.txt_ort = txt_ort;
	}

	public String getTxt_taetigkeit() {
		return txt_taetigkeit;
	}

	public void setTxt_taetigkeit(String txt_taetigkeit) {
		this.txt_taetigkeit = txt_taetigkeit;
	}

	public String getTxt_beschreibung() {
		return txt_beschreibung;
	}

	public void setTxt_beschreibung(String txt_beschreibung) {
		this.txt_beschreibung = txt_beschreibung;
	}

	public String getBtnSelectDateVon() {
		return btnSelectDateVon;
	}

	public void setBtnSelectDateVon(String btnSelectDateVon) {
		this.btnSelectDateVon = btnSelectDateVon;
	}

	public String getBtnSelectDateBis() {
		return btnSelectDateBis;
	}

	public void setBtnSelectDateBis(String btnSelectDateBis) {
		this.btnSelectDateBis = btnSelectDateBis;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long berufserfahrungID) {
		this.ID = berufserfahrungID;
	}

	public String getPersID() {
		return persID.toString();
	}

	public void setPersID(Long perID) {
		this.persID = perID;
	}

}
