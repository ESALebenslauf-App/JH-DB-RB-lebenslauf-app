package ch.jh_bd_rb_lebenslauf_app.gui;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 *
 */
public class Zusammenfassung extends Activity {

	String name;
	String adresse;
	ArrayList<String> berufserfahrungen = new ArrayList<String>();
	ArrayList<String> bildungen = new ArrayList<String>();
	String skillGrad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zusammenfassung);

		final Bundle extras = getIntent().getExtras();
		if (extras != null) {
			berufserfahrungen = extras
					.getStringArrayList(Skills.BERUFSERFAHRUNGEN);
			name = extras.getString(Skills.NAME);
			adresse = extras.getString(Skills.ADRESSE);
			bildungen = extras.getStringArrayList(Skills.BILDUNGEN);
			skillGrad = extras.getString(Skills.SKILL);
			
			/*
			String[] firmaTeile = berufserfahrungen.get(0).split("/"); 
			
			String[] schuleTeile = bildungen.get(0).split("/");

			
			// Personalien

			final TextView txtName = (TextView) findViewById(R.id.edt_zusammenfassung_person_name);
			txtName.setText(String.valueOf(name));

			final TextView txtAdresse = (TextView) findViewById(R.id.edt_zusammenfassung_person_adresse);
			txtAdresse.setText(String.valueOf(adresse));

			//Berufserfahrung

			final TextView txtFirma = (TextView) findViewById(R.id.edt_zusammenfassung_firma);
			txtFirma.setText(String.valueOf(firmaTeile[0]));

			final TextView txtFirmaDauer = (TextView) findViewById(R.id.edt_zusammenfassung_firma_dauer);
			txtFirmaDauer.setText(String.valueOf(firmaTeile[1]));

			final TextView txtFirmaTaetigkeit = (TextView) findViewById(R.id.edt_zusammenfassung_firma_taetigkeit);
			txtFirmaTaetigkeit.setText(String.valueOf(firmaTeile[2]));
			
			//Bildung
			
			final TextView txtSchuleArt = (TextView) findViewById(R.id.edt_zusammenfassung_schule_art);
			txtSchuleArt.setText(String.valueOf(schuleTeile[0]));
			
			final TextView txtSchule = (TextView) findViewById(R.id.edt_zusammenfassung_schule);
			 txtSchule.setText(String.valueOf(schuleTeile[1]));
			
			final TextView txtSchuleDauer = (TextView) findViewById(R.id.edt_zusammenfassung_schule_dauer);
			txtSchuleDauer.setText(String.valueOf(schuleTeile[2]));
			
			final TextView txtSchuleAdresse = (TextView) findViewById(R.id.edt_zusammenfassung_schule_adresse);
			txtSchuleAdresse.setText(String.valueOf(schuleTeile[3]));
			
			//Skills
			
			final TextView txtSkill = (TextView) findViewById(R.id.edt_zusammenfassung_itkenntnisse);
			txtSkill.setText(String.valueOf(skillGrad));
			*/
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zusammenfassung, menu);
		return true;
	}

	/**
	 * 
	 * @param Button
	 */
	public void onClickSkills(View Button) {
		final Intent intent = new Intent(this, Skills.class);

		startActivity(intent);

	}

	/**
	 * 
	 * @param Button
	 */
	public void onClickFinish(View Button) {
		final Intent intent = new Intent(this, Finish.class);

		startActivity(intent);
	}
	
	/**
	 * 
	 * @param text
	 */
	private void shortToast(String text) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

}
