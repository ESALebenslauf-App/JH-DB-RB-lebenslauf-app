package ch.jh_bd_rb_lebenslauf_app.listener;

import java.text.SimpleDateFormat;
import java.util.Locale;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungData;
import ch.jh_bd_rb_lebenslauf_app.resource.StringConst;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * @author j.herzig
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
	private RadioGroup radioGroup;
	private Button btnSelectDateVon;
	private Button btnSelectDateBis;
	private Long persID;
	private Long ID;

	/**
	 * @param myActivity
	 * @param persId
	 * @param id
	 */
	public BildungAddBildungListener(Activity myActivity, Long persId, Long id) {
		this.bildungActivity = myActivity;
		setPersID(persId);
		setID(id);
		init();
	}

	private void init() {
		setEdt_bildung_schule((EditText) bildungActivity.findViewById(R.id.edt_bildung_schule));
		setEdt_bildung_adresse((EditText) bildungActivity.findViewById(R.id.edt_bildung_ort));
		setEdt_bildung_plz((EditText) bildungActivity.findViewById(R.id.edt_bildung_plz));
		setEdt_radio_ausb((RadioButton) bildungActivity.findViewById(R.id.edt_radio_ausb));
		setEdt_radio_grund((RadioButton) bildungActivity.findViewById(R.id.edt_radio_grund));
		setEdt_radio_weiter((RadioButton) bildungActivity.findViewById(R.id.edt_radio_weiter));
		setBtnSelectDateVon((Button) bildungActivity.findViewById(R.id.btnSelectDateVon));
		setBtnSelectDateBis((Button) bildungActivity.findViewById(R.id.btnSelectDateBis));
		setRadioGroup((RadioGroup) bildungActivity.findViewById(R.id.edt_radiogroup_ausbildung));
	}



	@Override
	public void onClick(View arg0) {
		saveData();
		//TODO wird das enötigt?
		setID(new Long(0));
	}

	public BildungData saveData() {
		BildungData bildung = new BildungData(getRadioButten(), getEdt_bildung_schule().getText().toString(),
				getEdt_bildung_plz().getText().toString(), getEdt_bildung_adresse().getText().toString(), getDateVon(),
				getDateBis());

		bildung.setID(getID());
		bildung.setPersID(getPersID());

		// Datenbank
		BildungDB db = new BildungDB(bildungActivity);
		db.open();

		if (bildung.getID() > 0) {
			bildung = db.updateBildung(bildung);
		} else {
			bildung = db.insertBildung(bildung);
		}
		db.close();
		setID(bildung.getID());

		activityBereinigen();

		shortToast(StringConst.DATEN_WURDEN_GESPEICHERT);

		return bildung;
	}

	private void activityBereinigen() {

		SimpleDateFormat dateFormat = new SimpleDateFormat(StringConst.DATEFORMAT, Locale.GERMANY);
		String datum = dateFormat.format(new java.util.Date());

		getEdt_bildung_schule().setText("");
		getEdt_bildung_adresse().setText("");
		getEdt_bildung_plz().setText("");
		getBtnSelectDateBis().setText(datum);
		getBtnSelectDateVon().setText(datum);
		getRadioGroup().check(getEdt_radio_grund().getId());

	}

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

	/**
	 * 
	 * @param text
	 */
	private void shortToast(String text) {
		Context context = bildungActivity;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
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

	private Long getID() {
		return ID;
	}

	private void setID(Long iD) {
		ID = iD;
	}

	private Long getPersID() {
		return persID;
	}

	private void setPersID(Long persID) {
		this.persID = persID;
	}
	
	private RadioGroup getRadioGroup() {
		return radioGroup;
	}

	private void setRadioGroup(RadioGroup radioGroup) {
		this.radioGroup = radioGroup;
	}
}
