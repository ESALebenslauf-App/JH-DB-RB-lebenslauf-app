package ch.jh_bd_rb_lebenslauf_app.daten;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author j.herzig
 * 
 *         Daten Objekt mit allen Bildungs Objekten in einer ArrayListe zum
 *         abspeichern und Laden.
 */
public class BildungenDAO extends ArrayList<LebenslaufDaten> implements
		Serializable, I_DAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8343508447703744400L;
	private String ID;

	@Override
	public String getID() {
		return this.ID;
	}

	@Override
	public void setID(String id) {
		this.ID = id;
	}

	@Override
	public LebenslaufDaten searchBylID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LebenslaufDaten load(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(LebenslaufDaten lebenslaufDaten) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean update(LebenslaufDaten lebenslaufDaten) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(LebenslaufDaten lebenslaufDaten) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<LebenslaufDaten> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
