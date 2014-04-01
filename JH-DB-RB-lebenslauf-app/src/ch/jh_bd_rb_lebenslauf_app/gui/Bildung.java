package ch.jh_bd_rb_lebenslauf_app.gui;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Bildung extends Activity {
	
	Button btnSelectDateVon, btnSelectDateBis;

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
		
		// Start DatePicker
		btnSelectDateBis = (Button) findViewById(R.id.btnSelectDateBis);
		btnSelectDateVon = (Button) findViewById(R.id.btnSelectDateVon);
		
		btnSelectDateBis.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				shortToast("btnSelectDateBis.setOnClickListener");
			}
		});

		btnSelectDateVon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				shortToast("btnSelectDateVon.setOnClickListener");
			}
		});
		// End DatePicker

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bildung, menu);
		return true;
	}

	public void onClickBerufserfahrung(View Button) {
		final Intent intent = new Intent(this, Berufserfahrung.class);
		
		
		intent.putExtra(NAME, "Name");
		intent.putExtra(ADRESSE, "Adresse");
		startActivity(intent);

	}

	public void onClickSkills(View Button) {
		
		final Intent intent = new Intent(this, Skills.class);
		
		intent.putExtra(NAME, name);
		intent.putExtra(ADRESSE, adresse);

		intent.putStringArrayListExtra(BERUFSERFAHRUNGEN, berufserfahrungen);
		
		intent.putStringArrayListExtra(BILDUNGEN, bildungen);

		startActivity(intent);
	
	}

	public void onClickAddBildung(View Button) {
		shortToast("onClickAddBildung(View Button)");

	}
	
	private void shortToast(String text) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

}
