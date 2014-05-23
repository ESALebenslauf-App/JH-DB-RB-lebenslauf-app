package ch.jh_bd_rb_lebenslauf_app.daten;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BerufserfahrungenDAO extends ArrayList<LebenslaufDaten> implements
Serializable, I_DAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setID(String id) {
		// TODO Auto-generated method stub
		
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
