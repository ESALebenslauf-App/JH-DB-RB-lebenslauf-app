package ch.jh_bd_rb_lebenslauf_app.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungData;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungDB;
import ch.jh_bd_rb_lebenslauf_app.listener.*;
import ch.jh_bd_rb_lebenslauf_app.resource.StringConst;
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

	private Button btnSelectDateVon;
	private Button btnSelectDateBis;
	private Button btnAddBildung;
	private Button btnBerufserfahrung;
	private Button btnSkills;

	private BildungAddBildungListener bildungListener;
	private Long persID;
	private boolean save = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bildung);
		this.persID = getIntent().getLongExtra(StringConst.getPesrid(), 0);

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
		final Intent intent = new Intent(this, BerufserfahrungActivity.class);
		intent.putExtra(StringConst.getPesrid(), getPersID());

		startActivity(intent);
	}

	/**
	 * Öffnet die Skills Activity
	 */
	private void clickSkills() {

		final Intent intent = new Intent(this, SkillsActivity.class);
		intent.putExtra(StringConst.getPesrid(), getPersID());

		startActivity(intent);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (!save) {
			datenSpeichern();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (!save) {
			datenSpeichern();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bildung, menu);
		return true;
	}

	/**
	 * Daten können Persistent gespeichert werden
	 */
	private void datenSpeichern() {

		ArrayList<BildungData> bildungen = bildungListener.getBildungen();
		boolean save = false;

		if (getPersID() > 0) {
			if (bildungen.size() > 0) {
				for (BildungData current : bildungen) {
					BildungData bildung = (BildungData) current;
					bildung.setPersID(getPersID());

					// Datenbank
					BildungDB bildungDB = new BildungDB(this);
					bildungDB.open();
					bildung = bildungDB.insertBildung(bildung);
					bildungDB.close();

					if (bildung.getID() > 1) {
						save = true;
					}
				}
				if (save) {
					Toast toast = Toast.makeText(this,
							StringConst.getDatenWurdenGespeichert(),
							Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		} else {
			Toast toast = Toast.makeText(this,
					StringConst.getDatenWurdenNichtGespeichert(),
					Toast.LENGTH_LONG);
			toast.show();
		}
	}

	public Long getPersID() {
		return persID;
	}

}
