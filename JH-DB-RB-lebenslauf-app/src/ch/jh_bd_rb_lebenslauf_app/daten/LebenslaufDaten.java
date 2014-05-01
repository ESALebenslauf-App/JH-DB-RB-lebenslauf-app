package ch.jh_bd_rb_lebenslauf_app.daten;

/**
 * @author j.herzig
 * 
 *         LebenslaufDaten hier müssen alle Objekte die Persistent abgespeichert
 *         werden sollen angegeben werden.
 */
public interface LebenslaufDaten {

	public void setBildung(Bildung bildung);

	public Bildung getBildung();

}
