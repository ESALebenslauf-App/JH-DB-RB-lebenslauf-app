package ch.jh_bd_rb_lebenslauf_app.listener;

import ch.jh_bd_rb_lebenslauf_app.R;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


/**
 * Listener Klasse für den Butten Bildung hinzufügen
 * 
 * @author j.herzig
 * 
 */
public class BildungAddBildungListener implements OnClickListener {
	private final Context bildung;
	private Activity bildungActivity;
	private EditText edt_bildung_schule;
	private EditText edt_bildung_adresse;
	private RadioButton edt_radio_grund;
	private RadioButton edt_radio_ausb;
	private RadioButton edt_radio_weiter;

	public BildungAddBildungListener(Activity myActivity) {
		this.bildung = myActivity;
		this.bildungActivity = myActivity;
		init();
	}

	/**
	 * Initialisiert alle benötigen Ellemente aus der Activity damit mit diesen
	 * gearbeitet werden kann.
	 * 
	 */
	private void init() {
		setEdt_bildung_schule((EditText) bildungActivity
				.findViewById(R.id.edt_bildung_schule));
		setEdt_bildung_adresse((EditText) bildungActivity
				.findViewById(R.id.edt_bildung_adresse));

		setEdt_radio_ausb((RadioButton) bildungActivity
				.findViewById(R.id.edt_radio_ausb));
		setEdt_radio_grund((RadioButton) bildungActivity
				.findViewById(R.id.edt_radio_grund));
		setEdt_radio_weiter((RadioButton) bildungActivity
				.findViewById(R.id.edt_radio_weiter));
	}

	@Override
	public void onClick(View arg0) {

		// TODO Erfasste Daten abspeichern
		String strToast = "Eingabe im Feld Schule: "
				+ getEdt_bildung_schule().getText().toString()
				+ " / Eingabe im Feld Adresse: "
				+ getEdt_bildung_adresse().getText().toString();

		Toast toast = Toast.makeText(bildung, strToast, Toast.LENGTH_LONG);
		toast.show();
	}

	public EditText getEdt_bildung_schule() {
		return edt_bildung_schule;
	}

	public void setEdt_bildung_schule(EditText edt_bildung_schule) {
		this.edt_bildung_schule = edt_bildung_schule;
	}

	public EditText getEdt_bildung_adresse() {
		return edt_bildung_adresse;
	}

	public void setEdt_bildung_adresse(EditText edt_bildung_adresse) {
		this.edt_bildung_adresse = edt_bildung_adresse;
	}

	@SuppressWarnings("unused")
	private RadioButton getEdt_radio_grund() {
		return edt_radio_grund;
	}

	private void setEdt_radio_grund(RadioButton edt_radio_grund) {
		this.edt_radio_grund = edt_radio_grund;
	}

	@SuppressWarnings("unused")
	private RadioButton getEdt_radio_ausb() {
		return edt_radio_ausb;
	}

	private void setEdt_radio_ausb(RadioButton edt_radio_ausb) {
		this.edt_radio_ausb = edt_radio_ausb;
	}

	@SuppressWarnings("unused")
	private RadioButton getEdt_radio_weiter() {
		return edt_radio_weiter;
	}

	private void setEdt_radio_weiter(RadioButton edt_radio_weiter) {
		this.edt_radio_weiter = edt_radio_weiter;
	}

}
