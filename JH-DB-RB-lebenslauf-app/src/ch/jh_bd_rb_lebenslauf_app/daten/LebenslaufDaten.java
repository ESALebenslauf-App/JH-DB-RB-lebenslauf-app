package ch.jh_bd_rb_lebenslauf_app.daten;

/**
 * @author bdervishi.jherzig.rbuess
 * 
 *         LebenslaufDaten hier müssen alle Objekte die Persistent abgespeichert
 *         werden sollen angegeben werden.
 */
public interface LebenslaufDaten {

	/**
	 * 
	 * @param bildung
	 */
	public void setBildung(Bildung bildung);

	/**
	 * 
	 * @return
	 */
	public Bildung getBildung();

}
