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
	private BerufserfahrungListener berufserfahrungListener;
	private Long persID;
	private boolean save = false;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_berufserfahrung);
		this.persID = getIntent().getLongExtra(StringConst.getPesrid(), 0);

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

	/**
	 * Daten können Persistent gespeichert werden
	 */
	private void datenSpeichern() {

		ArrayList<BerufserfahrungData> berufserfahrungen = berufserfahrungListener
				.getBerufserfahrungen();

		if (getPersID() > 0) {
			if (berufserfahrungen.size() > 0) {
				for (BerufserfahrungData current : berufserfahrungen) {

					BerufserfahrungData berufserfahrung = (BerufserfahrungData) current;
					berufserfahrung.setPersID(getPersID());

					// Datenbank
					BerufserfahrungDB beruferfahrungDB = new BerufserfahrungDB(
							this);
					beruferfahrungDB.open();
					berufserfahrung = beruferfahrungDB
							.insertBerufserfahrung(berufserfahrung);
					beruferfahrungDB.close();

					Toast toast = Toast.makeText(this,
							berufserfahrung.getID().toString() + "PersID= "
									+ berufserfahrung.getPersID(),
							Toast.LENGTH_SHORT);
					toast.show();

					if (berufserfahrung.getID() > 0) {
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