package ch.jh_bd_rb_lebenslauf_app.daten;

public class BerufserfahrungData implements Cloneable{

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

	public BerufserfahrungData(String txt_firma, String txt_titel,
			String txt_adresse, String txt_plz, String txt_ort,
			String txt_taetigkeit, String btnSelectDateVon,
			String btnSelectDateBis) {
		super();
		this.txt_firma = txt_firma;
		this.txt_titel = txt_titel;
		this.txt_adresse = txt_adresse;
		this.txt_plz = txt_plz;
		this.txt_ort = txt_ort;
		this.txt_taetigkeit = txt_taetigkeit;
		this.btnSelectDateVon = btnSelectDateVon;
		this.btnSelectDateBis = btnSelectDateBis;
	}

	public BerufserfahrungData() {
		// TODO Auto-generated constructor stub
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

	public Long getPersID() {
		return persID;
	}

	public void setPersID(Long perID) {
		this.persID = perID;
	}


}
