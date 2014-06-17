package ch.jh_bd_rb_lebenslauf_app.daten;

/**
 * Klasse zum speichern der Daten aus der Activity Bildung.
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */
public class BildungData implements Cloneable {

	private Long id;
	private Long persID;
	private String ausbildungsart;
	private String nameschule;
	private String plz;
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
	public BildungData(String ausbildungsart, String nameschule, String plz,
			String adresseSchule, String datumVon, String datumBis) {
		super();
		this.ausbildungsart = ausbildungsart;
		this.nameschule = nameschule;
		this.plz = plz;
		this.adresseSchule = adresseSchule;
		this.datumVon = datumVon;
		this.datumBis = datumBis;
	}

	public BildungData clone() {
		BildungData theClone = null;
		try {
			theClone = (BildungData) super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return theClone;
	}

	public BildungData(Long id) {
		this.id = id;
	}

	public Long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public Long getPersID() {
		return persID;
	}

	public void setPersID(Long persID) {
		this.persID = persID;
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

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
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

}
