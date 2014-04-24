package ch.jh_bd_rb_lebenslauf_app.gui;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.listener.*;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Bildung extends FragmentActivity {

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bildung);

		// Initialisieren
		initActivityElemente();
		initActivityListener();

		// TODO wir dieser Code noch benötigt?
		final Bundle extras = getIntent().getExtras();
		if (extras != null) {
			berufserfahrungen = extras
					.getStringArrayList(Berufserfahrung.BERUFSERFAHRUNGEN);
			name = extras.getString(Berufserfahrung.NAME);
			adresse = extras.getString(Berufserfahrung.ADRESSE);
		}
		/*
		 * if (berufserfahrungen.isEmpty() != true) { Log.e("meldung",
		 * "bevore GET"); berufserfahrung = berufserfahrungen.get(0);
		 * Log.e("MELDUNG", "Data: " + berufserfahrung); String[] a =
		 * berufserfahrung.split("/"); final TextView txtSchule = (TextView)
		 * findViewById(R.id.edt_bildung_schule_x);
		 * txtSchule.setText(String.valueOf(a[0]));
		 * 
		 * final TextView txtDauer = (TextView)
		 * findViewById(R.id.edt_bildung_dauer_x);
		 * txtDauer.setText(String.valueOf(a[1]));
		 * 
		 * final TextView txtAdresse = (TextView)
		 * findViewById(R.id.edt_bildung_adresse_x);
		 * txtAdresse.setText(String.valueOf(a[2])); }
		 */
	}

	/**
	 * Initialisiert alle Listener aus der Activity
	 * 
	 */
	private void initActivityListener() {
		// Add Listener
		btnAddBildung.setOnClickListener(new BildungAddBildungListener(this));

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
				DialogFragment newFragment = new BildungDatePickerFragment();
				newFragment.show(getSupportFragmentManager(), "datePickerBis");
			}
		});

		btnSelectDateVon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new BildungDatePickerFragment();
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
		btnSelectDateBis = (Button) findViewById(R.id.btnSelectDateBis);
		btnSelectDateVon = (Button) findViewById(R.id.btnSelectDateVon);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bildung, menu);
		return true;
	}

}
