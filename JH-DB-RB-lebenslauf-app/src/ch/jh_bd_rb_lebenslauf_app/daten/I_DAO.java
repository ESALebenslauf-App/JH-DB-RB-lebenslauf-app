package ch.jh_bd_rb_lebenslauf_app.daten;

import java.util.List;

/**
 * @author bdervishi.jherzig.rbuess
 * 
 *         Interface um Daten Persistent abzuspeichern
 */
public interface I_DAO {

	public String getID();

	public void setID(String id);

	public LebenslaufDaten searchBylID(String id);

	public LebenslaufDaten load(String id);

	public void save(LebenslaufDaten lebenslaufDaten);

	public boolean update(LebenslaufDaten lebenslaufDaten);

	public boolean delete(LebenslaufDaten lebenslaufDaten);

	public List<LebenslaufDaten> getAll();
}
