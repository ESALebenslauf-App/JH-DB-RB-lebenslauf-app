package ch.jh_bd_rb_lebenslauf_app.gui;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.listener.BerufserfahrungListener;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_berufserfahrung);

		final Bundle extras = getIntent().getExtras();
		// Initialisieren
		initActivityElemente();
		initActivityListener();

	}

	private void initActivityElemente() {
		btnSelectDateBis = (Button) findViewById(R.id.btnSelectDateBis);
		btnSelectDateVon = (Button) findViewById(R.id.btnSelectDateVon);
		btnBeruferfahrung = (Button) findViewById(R.id.sf_add_berufserfahrung);
		btnBild = (Button) findViewById(R.id.buttonBild);
		btnBildung = (Button) findViewById(R.id.buttonBildungActivity);
	}

	private void initActivityListener() {
		btnBeruferfahrung.setOnClickListener(new BerufserfahrungListener(this));

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

}