package ch.jh_bd_rb_lebenslauf_app.gui;

import java.text.SimpleDateFormat;
import java.util.Locale;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungData;
import ch.jh_bd_rb_lebenslauf_app.listener.*;
import ch.jh_bd_rb_lebenslauf_app.resource.StringConst;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * @author bdervishi.jherzig.rbuess
 * 
 * 
 */
public class BildungActivity extends FragmentActivity {

	private Button btnSelectDateVon;
	private Button btnSelectDateBis;
	private EditText edt_bildung_schule;
	private EditText edt_bildung_adresse;
	private EditText edt_bildung_plz;
	private RadioButton edt_radio_grund;
	private RadioButton edt_radio_ausb;
	private RadioButton edt_radio_weiter;
	private Button btnAddBildung;
	private Button btnBerufserfahrung;
	private Button btnSkills;
	private BildungAddBildungListener bildungListener;
	private Long persID;
	private Long ID;
	private RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bildung);
		setPersID(getIntent().getLongExtra(StringConst.PERSID, 0));
		setID(getIntent().getLongExtra(StringConst.ID, 0));

		// Initialisieren
		initActivityElemente();
		initActivityListener();
		loadData();
	}

	/**
	 * Initialisiert alle benötigten Elemente aus der Activity
	 * 
	 */
	private void initActivityElemente() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(StringConst.DATEFORMAT, Locale.GERMANY);
		String datum = dateFormat.format(new java.util.Date());
		btnAddBildung = (Button) findViewById(R.id.btn_add_bildung);
		btnBerufserfahrung = (Button) findViewById(R.id.btnBerufserfahrung);
		btnSkills = (Button) findViewById(R.id.btnSkills);

		setBtnSelectDateBis((Button) findViewById(R.id.btnSelectDateBis));
		getBtnSelectDateBis().setText(datum);
		setBtnSelectDateVon((Button) findViewById(R.id.btnSelectDateVon));
		getBtnSelectDateVon().setText(datum);
		setEdt_bildung_schule((EditText) findViewById(R.id.edt_bildung_schule));
		setEdt_bildung_adresse((EditText) findViewById(R.id.edt_bildung_ort));
		setEdt_bildung_plz((EditText) findViewById(R.id.edt_bildung_plz));
		setEdt_radio_ausb((RadioButton) findViewById(R.id.edt_radio_ausb));
		setEdt_radio_grund((RadioButton) findViewById(R.id.edt_radio_grund));
		setEdt_radio_weiter((RadioButton) findViewById(R.id.edt_radio_weiter));
		setRadioGroup((RadioGroup) findViewById(R.id.edt_radiogroup_ausbildung));
	}

	/**
	 * Initialisiert alle Listener aus der Activity
	 * 
	 */
	private void initActivityListener() {
		// Add Listener
		bildungListener = new BildungAddBildungListener(this, getPersID(), getID());
		btnAddBildung.setOnClickListener(bildungListener);

		btnBerufserfahrung.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clickBerufserfahrung();
			}
		});
		btnSkills.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clickSkills();
			}
		});

		// Start DatePicker
		btnSelectDateBis.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new DatePickerFragment();
				newFragment.show(getSupportFragmentManager(), StringConst.DATEPICKERBIS);
			}
		});

		btnSelectDateVon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new DatePickerFragment();
				newFragment.show(getSupportFragmentManager(), StringConst.DATEPICKERVON);
			}
		});
		// End DatePicker
	}

	private void loadData() {

		if (getID() > 0) {
			BildungData bildung = new BildungData(getID());

			BildungDB db = new BildungDB(this);
			db.open();
			bildung = db.getBildung(bildung);
			db.close();

			getEdt_bildung_schule().setText(bildung.getNameschule());
			getEdt_bildung_adresse().setText(bildung.getAdresseSchule());
			getEdt_bildung_plz().setText(bildung.getPlz());
			getBtnSelectDateBis().setText(bildung.getDatumBis());
			getBtnSelectDateVon().setText(bildung.getDatumVon());

			// Switch funktioniert leider nur mit Strings und nicht mit String
			// Variabeln daher musste ich die Strings im Code fix schreiben.
			switch (bildung.getAusbildungsart()) {
			case "Grundbildung":
				getRadioGroup().check(getEdt_radio_grund().getId());
				break;
			case "Ausbildung":
				getRadioGroup().check(getEdt_radio_ausb().getId());
				break;
			case "Weiterbildung":
				getRadioGroup().check(getEdt_radio_weiter().getId());
				break;

			default:
				break;
			}
		}

	}

	/**
	 * Öffnet die Berufserfahrung Activity
	 */
	private void clickBerufserfahrung() {
		final Intent intent = new Intent(this, BerufserfahrungActivity.class);
		intent.putExtra(StringConst.PERSID, getPersID());

		startActivity(intent);
	}

	/**
	 * Öffnet die Skills Activity
	 */
	private void clickSkills() {

		final Intent intent = new Intent(this, SkillsActivity.class);
		intent.putExtra(StringConst.PERSID, getPersID());

		startActivity(intent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.bildung, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_bildung_list:
			final Intent intent = new Intent(this, ListBildungenActivity.class);
			intent.putExtra(StringConst.PERSID, getPersID());
			this.startActivity(intent);
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private Long getID() {
		return ID;
	}

	private void setID(Long iD) {
		ID = iD;
	}

	public Long getPersID() {
		return persID;
	}

	private void setPersID(Long persID) {
		this.persID = persID;
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

	private EditText getEdt_bildung_plz() {
		return edt_bildung_plz;
	}

	private void setEdt_bildung_plz(EditText edt_bildung_plz) {
		this.edt_bildung_plz = edt_bildung_plz;
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

	private RadioGroup getRadioGroup() {
		return radioGroup;
	}

	private void setRadioGroup(RadioGroup radioGroup) {
		this.radioGroup = radioGroup;
	}

}
