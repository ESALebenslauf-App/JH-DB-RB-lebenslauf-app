package ch.jh_bd_rb_lebenslauf_app.daten;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */

public class SkillsData implements Cloneable {

	private Long ID;
	private Long pers_id;
	private String was;
	private String ausmass;
	private Zertifikat zertifikat;

	public SkillsData(String was, String ausmass, Zertifikat zertifikat) {
		super();
		this.was = was;
		this.ausmass = ausmass;
		this.zertifikat = zertifikat;
	}

	public SkillsData() {

	}

	public SkillsData(Long iD) {
		super();
		ID = iD;
	}

	public SkillsData clone() {
		SkillsData theClone = null;
		try {
			theClone = (SkillsData) super.clone();
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

	public String getPers_id() {
		return pers_id.toString();
	}

	public void setPers_id(Long pers_id) {
		this.pers_id = pers_id;
	}

	public String getWas() {
		return was;
	}

	public void setWas(String was) {
		this.was = was;
	}

	public String getAusmass() {
		return ausmass;
	}

	public void setAusmass(String ausmass) {
		this.ausmass = ausmass;
	}

	public Zertifikat getZertifikat() {
		return zertifikat;
	}

	public void setZertifikat(Zertifikat zertifikat) {
		this.zertifikat = zertifikat;
	}

}
