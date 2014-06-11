package ch.jh_bd_rb_lebenslauf_app.listener;

import java.text.SimpleDateFormat;
import java.util.Locale;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.Bildung;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungenDAO;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Listener Klasse für den Butten Bildung hinzufügen die daten werden in eine
 * BidlungenDAO Objekt abgespeichert.
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */
public class BildungAddBildungListener implements OnClickListener {
	private Activity bildungActivity;
	private EditText edt_bildung_schule;
	private EditText edt_bildung_adresse;
	private EditText edt_bildung_plz;
	private RadioButton edt_radio_grund;
	private RadioButton edt_radio_ausb;
	private RadioButton edt_radio_weiter;
	private Button btnSelectDateVon;
	private Button btnSelectDateBis;
	private BildungenDAO bildungen;
	private String ID;

	/**
	 * Konstruktor Klasse BildungAddBildungListener
	 * 
	 * @param myActivity
	 */
	public BildungAddBildungListener(Activity myActivity) {
		this.bildungActivity = myActivity;
		bildungen = new BildungenDAO();
		bildungen.setID(getID());
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
				.findViewById(R.id.edt_bildung_ort));
		setEdt_bildung_plz((EditText) bildungActivity
				.findViewById(R.id.edt_bildung_plz));
		setEdt_radio_ausb((RadioButton) bildungActivity
				.findViewById(R.id.edt_radio_ausb));
		setEdt_radio_grund((RadioButton) bildungActivity
				.findViewById(R.id.edt_radio_grund));
		setEdt_radio_weiter((RadioButton) bildungActivity
				.findViewById(R.id.edt_radio_weiter));
		setBtnSelectDateVon((Button) bildungActivity
				.findViewById(R.id.btnSelectDateVon));
		setBtnSelectDateBis((Button) bildungActivity
				.findViewById(R.id.btnSelectDateBis));
	}

	@Override
	public void onClick(View arg0) {

		Bildung bildung = new Bildung(getRadioButten(),
				getEdt_bildung_schule().getText().toString(),getEdt_bildung_plz().getText().toString(),
				getEdt_bildung_adresse().getText().toString(), getDateVon(),
				getDateBis());

		// Bidlung wird in ein ArrayList Objekt abgespeichert das beim verlassen
		// der Activity Persistent gespeichert werden kann.
		bildungen.add(bildung);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy",
				Locale.GERMANY);
		String datum = dateFormat.format(new java.util.Date());

		getEdt_bildung_schule().setText("");
		getEdt_bildung_adresse().setText("");
		getBtnSelectDateBis().setText(datum);
		getBtnSelectDateVon().setText(datum);
	}

	/**
	 * Ein bestehndes Objekt Bildungen übergeben
	 * 
	 * @param bildungen
	 */
	public void setBildungen(BildungenDAO bildungen) {
		this.bildungen = bildungen;
	}

	/**
	 * Rückgabe alle gespeicherten Bildungen
	 * 
	 * @return Bildungen
	 */
	public BildungenDAO getBildungen() {
		return bildungen;
	}

	public String getID() {
		// TODO überarbeiten
		ID = "ObjektID";
		return ID;

	}

	// TODO ID in den Konstruktor aufnehmen
	public void setID(String iD) {
		this.ID = iD;
	}

	// TODO überarbeiten und schönere lösung finden
	private String getRadioButten() {
		String text = "";
		if (getEdt_radio_ausb().isChecked()) {
			text = getEdt_radio_ausb().getText().toString();
		}

		if (getEdt_radio_grund().isChecked()) {
			text = getEdt_radio_grund().getText().toString();
		}

		if (getEdt_radio_weiter().isChecked()) {
			text = getEdt_radio_weiter().getText().toString();
		}

		return text;
	}

	private EditText getEdt_bildung_schule() {
		return edt_bildung_schule;
	}

	private void setEdt_bildung_schule(EditText edt_bildung_schule) {
		this.edt_bildung_schule = edt_bildung_schule;
	}

	private EditText getEdt_bildung_adresse() {
		return edt_bildung_adresse;
	}

	private void setEdt_bildung_adresse(EditText edt_bildung_adresse) {
		this.edt_bildung_adresse = edt_bildung_adresse;
	}

	private RadioButton getEdt_radio_grund() {
		return edt_radio_grund;
	}

	private void setEdt_radio_grund(RadioButton edt_radio_grund) {
		this.edt_radio_grund = edt_radio_grund;
	}

	private RadioButton getEdt_radio_ausb() {
		return edt_radio_ausb;
	}

	private void setEdt_radio_ausb(RadioButton edt_radio_ausb) {
		this.edt_radio_ausb = edt_radio_ausb;
	}

	private RadioButton getEdt_radio_weiter() {
		return edt_radio_weiter;
	}

	private void setEdt_radio_weiter(RadioButton edt_radio_weiter) {
		this.edt_radio_weiter = edt_radio_weiter;
	}

	private Button getBtnSelectDateVon() {
		return btnSelectDateVon;
	}

	private void setBtnSelectDateVon(Button btnSelectDateVon) {
		this.btnSelectDateVon = btnSelectDateVon;
	}

	private Button getBtnSelectDateBis() {
		return btnSelectDateBis;
	}

	private void setBtnSelectDateBis(Button btnSelectDateBis) {
		this.btnSelectDateBis = btnSelectDateBis;
	}

	private String getDateVon() {
		return btnSelectDateVon.getText().toString();
	}

	private String getDateBis() {
		return btnSelectDateBis.getText().toString();
	}

	private EditText getEdt_bildung_plz() {
		return edt_bildung_plz;
	}

	private void setEdt_bildung_plz(EditText edt_bildung_plz) {
		this.edt_bildung_plz = edt_bildung_plz;
	}
}
