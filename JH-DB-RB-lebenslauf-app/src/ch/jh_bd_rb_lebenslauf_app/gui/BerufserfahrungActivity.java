package ch.jh_bd_rb_lebenslauf_app.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungData;
import ch.jh_bd_rb_lebenslauf_app.listener.BerufserfahrungListener;
import ch.jh_bd_rb_lebenslauf_app.resource.StringConst;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */
public class BerufserfahrungActivity extends FragmentActivity {

	private Button btnBildung;
	private Button btnBild;
	private Button btnBeruferfahrung;
	private Button btnSelectDateBis;
	private Button btnSelectDateVon;
	private Button btnBeschreibung;
	private BerufserfahrungListener berufserfahrungListener;
	private Long persID;
	private EditText txt_firma;
	private EditText txt_titel;
	private EditText txt_adresse;
	private EditText txt_plz;
	private EditText txt_ort;
	private EditText txt_taetigkeit;
	private String beschreibungText;
	private Long ID;

	


	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_berufserfahrung);
		this.persID = getIntent().getLongExtra(StringConst.getPesrid(), 0);
		setBeschreibungText(getIntent()
				.getStringExtra(StringConst.BESCHREIBUNG));
		setID(getIntent().getLongExtra(StringConst.ID, 0));

		// Initialisieren
		initActivityElemente();
		initActivityListener();
		loadData();

	}

	private void loadData() {
		BerufserfahrungData beruferfahrung = new BerufserfahrungData(
				getID());
		if (getID() > 0) {
			BerufserfahrungDB db = new BerufserfahrungDB(this);
			db.open();
			beruferfahrung = db.getBerufserfahrung(beruferfahrung);

			getTxt_firma().setText(beruferfahrung.getTxt_firma());
			getTxt_titel().setText(beruferfahrung.getTxt_titel());
			getTxt_adresse().setText(beruferfahrung.getTxt_adresse());
			getTxt_plz().setText(beruferfahrung.getTxt_plz());
			getTxt_ort().setText(beruferfahrung.getTxt_ort());
			getTxt_taetigkeit().setText(beruferfahrung.getTxt_taetigkeit());
			getBtnSelectDateBis().setText(beruferfahrung.getBtnSelectDateBis());
			getBtnSelectDateVon().setText(beruferfahrung.getBtnSelectDateVon());

			beruferfahrung.setTxt_beschreibung(getBeschreibungText());
		}
	}

	private void initActivityElemente() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy",
				Locale.GERMANY);
		String datum = dateFormat.format(new java.util.Date());
		
		btnBeruferfahrung = (Button) findViewById(R.id.sf_add_berufserfahrung);
		btnBild = (Button) findViewById(R.id.buttonBild);
		btnBildung = (Button) findViewById(R.id.buttonBildungActivity);
		btnBeschreibung = (Button) findViewById(R.id.btn_berufserfahrung_beschreibung);

		setBtnSelectDateBis((Button) findViewById(R.id.btnSelectDateBis));
		getBtnSelectDateBis().setText(datum);
		setBtnSelectDateVon((Button) findViewById(R.id.btnSelectDateVon));
		getBtnSelectDateVon().setText(datum);
		setTxt_firma((EditText) findViewById(R.id.txt_firma));
		setTxt_titel((EditText) findViewById(R.id.txt_titel));
		setTxt_adresse((EditText) findViewById(R.id.txt_adresse));
		setTxt_plz((EditText) findViewById(R.id.txt_plz));
		setTxt_ort((EditText) findViewById(R.id.txt_ort));
		setTxt_taetigkeit((EditText) findViewById(R.id.txt_taetigkeit));
	}

	private void initActivityListener() {
		berufserfahrungListener = new BerufserfahrungListener(this,
				getPersID(), getID());
		berufserfahrungListener.setBeschreibungText(beschreibungText);
		btnBeruferfahrung.setOnClickListener(berufserfahrungListener);

		btnBild.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View button) {
				clickBild(button);
			}
		});

		btnBildung.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View butten) {
				clickBildung(butten);
			}
		});

		// Start DatePicker
		btnSelectDateBis.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new DatePickerFragment();
				newFragment.show(getSupportFragmentManager(),
						StringConst.DATEPICKERBIS);
			}
		});

		btnSelectDateVon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new DatePickerFragment();
				newFragment.show(getSupportFragmentManager(),
						StringConst.DATEPICKERVON);
			}
		});
		// End DatePicker

		btnBeschreibung.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View butten) {
				clickBeschreibung(butten);

			}

		});
	}

	private void clickBeschreibung(View butten) {
		final Intent intent = new Intent(this,
				Berufserfahrung_beschreibungActivity.class);
		datenSpeichern();
		intent.putExtra(StringConst.getPesrid(), getPersID());
		intent.putExtra(StringConst.ID, berufserfahrungListener.getID());

		startActivity(intent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.berufserfahrung, menu);
		return true;
	}

	/**
	 * 
	 * @param Button
	 */
	public void clickBild(View Button) {
		final Intent intent = new Intent(this, BildActivity.class);
		intent.putExtra(StringConst.getPesrid(), getPersID());

		startActivity(intent);
	}

	/**
	 * 
	 * @param Button
	 */
	public void clickBildung(View Button) {
		final Intent intent = new Intent(this, BildungActivity.class);
		intent.putExtra(StringConst.getPesrid(), getPersID());

		startActivity(intent);
	}

	/**
	 * Daten können Persistent gespeichert werden
	 */
	private void datenSpeichern() {

		BerufserfahrungData data = berufserfahrungListener.saveData();
		setID(data.getID());
	}

	private Long getPersID() {
		return persID;
	}

	public String getBeschreibungText() {
		return beschreibungText;
	}

	public void setBeschreibungText(String beschreibungText) {
		this.beschreibungText = beschreibungText;
	}

	private EditText getTxt_firma() {
		return txt_firma;
	}

	private void setTxt_firma(EditText txt_firma) {
		this.txt_firma = txt_firma;
	}

	private EditText getTxt_titel() {
		return txt_titel;
	}

	private void setTxt_titel(EditText txt_titel) {
		this.txt_titel = txt_titel;
	}

	private EditText getTxt_adresse() {
		return txt_adresse;
	}

	private void setTxt_adresse(EditText txt_adresse) {
		this.txt_adresse = txt_adresse;
	}

	private EditText getTxt_plz() {
		return txt_plz;
	}

	private void setTxt_plz(EditText txt_plz) {
		this.txt_plz = txt_plz;
	}

	private EditText getTxt_ort() {
		return txt_ort;
	}

	private void setTxt_ort(EditText txt_ort) {
		this.txt_ort = txt_ort;
	}

	private EditText getTxt_taetigkeit() {
		return txt_taetigkeit;
	}

	private void setTxt_taetigkeit(EditText txt_taetigkeit) {
		this.txt_taetigkeit = txt_taetigkeit;
	}

	private Long getID() {
		return ID;
	}

	private void setID(Long iD) {
		ID = iD;
	}
	
	private Button getBtnSelectDateBis() {
		return btnSelectDateBis;
	}

	private void setBtnSelectDateBis(Button btnSelectDateBis) {
		this.btnSelectDateBis = btnSelectDateBis;
	}

	private Button getBtnSelectDateVon() {
		return btnSelectDateVon;
	}

	private void setBtnSelectDateVon(Button btnSelectDateVon) {
		this.btnSelectDateVon = btnSelectDateVon;
	}

}