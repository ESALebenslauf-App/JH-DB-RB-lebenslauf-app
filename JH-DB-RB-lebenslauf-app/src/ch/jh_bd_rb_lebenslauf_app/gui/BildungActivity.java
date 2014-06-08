package ch.jh_bd_rb_lebenslauf_app.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.Bildung;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungenDAO;
import ch.jh_bd_rb_lebenslauf_app.daten.LebenslaufDaten;
import ch.jh_bd_rb_lebenslauf_app.listener.*;
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
 * @author bdervishi.jherzig.rbuess
 * 
 *         In dieser Activity können mehrer Ausbildungen hinterlegt werden.
 */
public class BildungActivity extends FragmentActivity {

	Button btnSelectDateVon;
	Button btnSelectDateBis;
	Button btnAddBildung;
	Button btnBerufserfahrung;
	Button btnSkills;

	String name;
	String adresse;
	static final String NAME = "name";
	static final String ADRESSE = "adresse";
	ArrayList<String> berufserfahrungen = new ArrayList<String>();
	static final String BERUFSERFAHRUNGEN = "berufserfahrungen";
	ArrayList<String> bildungen = new ArrayList<String>();
	static final String BILDUNGEN = "bildung";
	private BildungAddBildungListener bildungListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bildung);

		// Initialisieren
		initActivityElemente();
		initActivityListener();
	}

	/**
	 * Initialisiert alle Listener aus der Activity
	 * 
	 */
	private void initActivityListener() {
		// Add Listener
		bildungListener = new BildungAddBildungListener(this);
		btnAddBildung.setOnClickListener(bildungListener);

		// Add Listener mit intent als interne Klassen da sonst die Klasse über
		// Ihren ganzne Namen mit Packet aufgerufen werden muss und dies nicht
		// vom Copiler geprüft wird.
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

	/**
	 * Initialisiert alle benötigten Elemente aus der Activity
	 * 
	 */
	private void initActivityElemente() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy",
				Locale.GERMANY);
		String datum = dateFormat.format(new java.util.Date());
		btnSelectDateBis = (Button) findViewById(R.id.btnSelectDateBis);
		btnSelectDateBis.setText(datum);
		btnSelectDateVon = (Button) findViewById(R.id.btnSelectDateVon);
		btnSelectDateVon.setText(datum);
		btnAddBildung = (Button) findViewById(R.id.btn_add_bildung);
		btnBerufserfahrung = (Button) findViewById(R.id.btnBerufserfahrung);
		btnSkills = (Button) findViewById(R.id.btnSkills);
	}

	/**
	 * Öffnet die Berufserfahrung Activity
	 */
	private void clickBerufserfahrung() {
		final Intent intent = new Intent(this, Berufserfahrung.class);

		intent.putExtra(NAME, "Name");
		intent.putExtra(ADRESSE, "Adresse");
		startActivity(intent);
	}

	/**
	 * Öffnet die Skills Activity
	 */
	private void clickSkills() {

		final Intent intent = new Intent(this, Skills.class);

		intent.putExtra(NAME, name);
		intent.putExtra(ADRESSE, adresse);

		intent.putStringArrayListExtra(BERUFSERFAHRUNGEN, berufserfahrungen);

		intent.putStringArrayListExtra(BILDUNGEN, bildungen);

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
	 * Daten können Persistent gespeichert werden
	 */
	private void datenSpeichern() {
		long dbID;
		// Datenobjekt aus demListener laden
		BildungenDAO bildungen = bildungListener.getBildungen();
		if (bildungen.size() > 0) {
			// TODO Erfasste Daten beim verlassen der Activity abspeichern
			// TODO Start Demo / Ausbauen
			String strToast = "";
			for (LebenslaufDaten current : bildungen) {
				// LebenslaufDaten in Bildung Casten
				Bildung bildung = (Bildung) current;
				strToast = strToast + bildung.getAusbildungsart() + " / "
						+ bildung.getNameschule() + " / "
						+ bildung.getAdresseSchule() + " / "
						+ bildung.getDatumVon() + " / " + bildung.getDatumBis()
						+ " ENDE ";

				// TODO Datenbank
				BildungDB bildungDB = new BildungDB(this);
				bildungDB.open();
				dbID = bildungDB.insertBildung("anrede",
						bildung.getAusbildungsart(), bildung.getNameschule(),
						4624, bildung.getAdresseSchule(),
						bildung.getDatumVon(), bildung.getDatumBis());
				bildungDB.close();
				strToast = strToast + " DATENBANK ID: " + dbID;
			}

			Toast toast = Toast.makeText(this, strToast, Toast.LENGTH_LONG);
			toast.show();
			// ENDE Demo
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bildung, menu);
		return true;
	}

}
