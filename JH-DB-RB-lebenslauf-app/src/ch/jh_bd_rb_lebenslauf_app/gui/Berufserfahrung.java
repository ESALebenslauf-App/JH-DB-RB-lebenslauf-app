package ch.jh_bd_rb_lebenslauf_app.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungData;
import ch.jh_bd_rb_lebenslauf_app.listener.BerufserfahrungListener;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */
public class Berufserfahrung extends FragmentActivity {

	String name;
	String adresse;
	public static final String NAME = "name";
	public static final String ADRESSE = "adresse";
	public ArrayList<String> berufserfahrungen = new ArrayList<String>();
	public static final String BERUFSERFAHRUNGEN = "berufserfahrungen";
	private Button btnBildung;
	private Button btnBild;
	private Button btnBeruferfahrung;
	private Button btnSelectDateBis;
	private Button btnSelectDateVon;
	private BerufserfahrungListener berufserfahrungListener;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_berufserfahrung);

		// Initialisieren
		initActivityElemente();
		initActivityListener();

	}

	private void initActivityElemente() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy",
				Locale.GERMANY);
		String datum = dateFormat.format(new java.util.Date());
		btnSelectDateBis = (Button) findViewById(R.id.btnSelectDateBis);
		btnSelectDateBis.setText(datum);
		btnSelectDateVon = (Button) findViewById(R.id.btnSelectDateVon);
		btnSelectDateVon.setText(datum);
		btnBeruferfahrung = (Button) findViewById(R.id.sf_add_berufserfahrung);
		btnBild = (Button) findViewById(R.id.buttonBild);
		btnBildung = (Button) findViewById(R.id.buttonBildungActivity);
	}

	private void initActivityListener() {
		berufserfahrungListener = new BerufserfahrungListener(this);
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
				newFragment.show(getSupportFragmentManager(), "datePickerBis");
			}
		});

		btnSelectDateVon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new DatePickerFragment();
				newFragment.show(getSupportFragmentManager(), "datePickerVon");
			}
		});
		// End DatePicker
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
		final Intent intent = new Intent(this, Bild.class);
		startActivity(intent);
	}

	/**
	 * 
	 * @param Button
	 */
	public void clickBildung(View Button) {
		final Intent intent = new Intent(this, BildungActivity.class);

		intent.putExtra(NAME, name);
		intent.putExtra(ADRESSE, adresse);

		intent.putStringArrayListExtra(BERUFSERFAHRUNGEN, berufserfahrungen);

		startActivity(intent);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		datenSpeichern();
	}

	@Override
	protected void onStop() {
		super.onStop();
		datenSpeichern();
	}

	/**
	 * Daten k�nnen Persistent gespeichert werden
	 */
	private void datenSpeichern() {
		// Datenobjekt aus demListener laden
		long dbID = 0;
		ArrayList<BerufserfahrungData> berufserfahrungen = berufserfahrungListener.getBerufserfahrungen();
		if (berufserfahrungen.size() > 0) {
			// TODO Erfasste Daten beim verlassen der Activity abspeichern
			// TODO Start Demo / Ausbauen
			String strToast = "";
			for (BerufserfahrungData current : berufserfahrungen) {
				// LebenslaufDaten in Bildung Casten
				BerufserfahrungData berufserfahrung = (BerufserfahrungData) current;
				strToast = strToast + berufserfahrung.getTxt_titel() + " / "
						+ berufserfahrung.getTxt_firma() + " / "
						+ berufserfahrung.getTxt_adresse() + " / "
						+ berufserfahrung.getTxt_plz() + " / "
						+ berufserfahrung.getTxt_ort() + " / "
						+ berufserfahrung.getTxt_taetigkeit() + " / "
						+ berufserfahrung.getBtnSelectDateVon() + " / "
						+ berufserfahrung.getBtnSelectDateBis() + " ENDE ";

				//Datenbank
				BerufserfahrungDB beruferfahrungDB = new BerufserfahrungDB(this);
				beruferfahrungDB.open();
				beruferfahrungDB.insertBerufserfahrung(berufserfahrung);
				beruferfahrungDB.close();
				
				//TODO Ausbauen
				strToast = strToast + "DATENBANK ID: " + dbID;
				
			}
			Toast toast = Toast.makeText(this, strToast, Toast.LENGTH_LONG);
			toast.show();
			// ENDE Demo
		}
	}

}