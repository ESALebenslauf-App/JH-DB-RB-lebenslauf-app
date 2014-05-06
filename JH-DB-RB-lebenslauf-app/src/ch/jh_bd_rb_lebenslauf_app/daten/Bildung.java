package ch.jh_bd_rb_lebenslauf_app.daten;

/**
 * Klasse zum speichern der Daten aus der Activity Bildung.
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */
public class Bildung implements LebenslaufDaten {

	private String ausbildungsart;
	private String nameschule;
	private String adresseSchule;
	private String datumVon;
	private String datumBis;

	/**
	 * Konstruktor Klasse Blidung
	 * 
	 * @param ausbildungsart
	 * @param nameschule
	 * @param adresseSchule
	 * @param datumVon
	 * @param datumBis
	 */
	public Bildung(String ausbildungsart, String nameschule,
			String adresseSchule, String datumVon, String datumBis) {
		super();
		this.ausbildungsart = ausbildungsart;
		this.nameschule = nameschule;
		this.adresseSchule = adresseSchule;
		this.datumVon = datumVon;
		this.datumBis = datumBis;
	}

	/**
	 * @return ausbildungsart
	 */
	public String getAusbildungsart() {
		return ausbildungsart;
	}

	/**
	 * @param ausbildungsart
	 */
	public void setAusbildungsart(String ausbildungsart) {
		this.ausbildungsart = ausbildungsart;
	}

	/**
	 * @return nameschule
	 */
	public String getNameschule() {
		return nameschule;
	}

	/**
	 * @param nameschule
	 */
	public void setNameschule(String nameschule) {
		this.nameschule = nameschule;
	}

	/**
	 * @return adresseSchule
	 */
	public String getAdresseSchule() {
		return adresseSchule;
	}

	/**
	 * @param adresseSchule
	 */
	public void setAdresseSchule(String adresseSchule) {
		this.adresseSchule = adresseSchule;
	}

	/**
	 * @return datumVon
	 */
	public String getDatumVon() {
		return datumVon;
	}

	/**
	 * @param datumVon
	 */
	public void setDatumVon(String datumVon) {
		this.datumVon = datumVon;
	}

	/**
	 * @return datumBis
	 */
	public String getDatumBis() {
		return datumBis;
	}

	/**
	 * @param datumBis
	 */
	public void setDatumBis(String datumBis) {
		this.datumBis = datumBis;
	}

	public void setBildung(Bildung bildung) {
		// TODO Auto-generated method stub
	}

	public Bildung getBildung() {
		Bildung bildung = this;
		return bildung;
	}

}
