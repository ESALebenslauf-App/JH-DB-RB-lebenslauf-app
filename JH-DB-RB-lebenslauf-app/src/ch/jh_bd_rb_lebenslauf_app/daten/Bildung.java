package ch.jh_bd_rb_lebenslauf_app.daten;

import java.io.Serializable;
import java.util.Date;

public class Bildung implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ausbildungsart;
	private String nameschule;
	private String adresseSchule;
	private String datumVon;
	private String datumBis;
	
	
	public Bildung(String ausbildungsart, String nameschule,
			String adresseSchule, String datumVon, String datumBis) {
		super();
		this.ausbildungsart = ausbildungsart;
		this.nameschule = nameschule;
		this.adresseSchule = adresseSchule;
		this.datumVon = datumVon;
		this.datumBis = datumBis;
	}
	
	
	public String getAusbildungsart() {
		return ausbildungsart;
	}
	public void setAusbildungsart(String ausbildungsart) {
		this.ausbildungsart = ausbildungsart;
	}
	public String getNameschule() {
		return nameschule;
	}
	public void setNameschule(String nameschule) {
		this.nameschule = nameschule;
	}
	public String getAdresseSchule() {
		return adresseSchule;
	}
	public void setAdresseSchule(String adresseSchule) {
		this.adresseSchule = adresseSchule;
	}
	public String getDatumVon() {
		return datumVon;
	}
	public void setDatumVon(String datumVon) {
		this.datumVon = datumVon;
	}
	public String getDatumBis() {
		return datumBis;
	}
	public void setDatumBis(String datumBis) {
		this.datumBis = datumBis;
	}
	

}
