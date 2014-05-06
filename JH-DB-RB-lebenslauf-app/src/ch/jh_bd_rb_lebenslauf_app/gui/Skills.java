package ch.jh_bd_rb_lebenslauf_app.gui;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 *
 */
public class Skills extends Activity {

	String name;
	String adresse;
	static final String NAME = "name";
	static final String ADRESSE = "adresse";
	ArrayList<String> berufserfahrungen = new ArrayList<String>();
	static final String BERUFSERFAHRUNGEN = "berufserfahrungen";
	ArrayList<String> bildungen = new ArrayList<String>();
	static final String BILDUNGEN = "bildung";
	String skillGrad;
	static final String SKILL = "skill";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skills);

		
		final Bundle extras = getIntent().getExtras();
		if (extras != null) {
			berufserfahrungen = extras
					.getStringArrayList(BildungActivity.BERUFSERFAHRUNGEN);
			name = extras.getString(BildungActivity.NAME);
			adresse = extras.getString(BildungActivity.ADRESSE);
			bildungen = extras.getStringArrayList(BildungActivity.BILDUNGEN);
		}

	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.skills, menu);
		return true;
	}

	/**
	 * 
	 * @param Button
	 */
	public void onClickBildung(View Button) {
		final Intent intent = new Intent(this, BildungActivity.class);
		startActivity(intent);
	}

	/**
	 * 
	 * @param Button
	 */
	public void onClickZusammenfassung(View Button) {
		final Intent intent = new Intent(this, Zusammenfassung.class);
		
		intent.putExtra(NAME, name);
		intent.putExtra(ADRESSE, adresse);

		intent.putStringArrayListExtra(BERUFSERFAHRUNGEN, berufserfahrungen);
		
		intent.putStringArrayListExtra(BILDUNGEN, bildungen);
		
		intent.putExtra(SKILL, skillGrad);

		startActivity(intent);
	}

	/**
	 * 
	 * @param Button
	 */
	public void onClickAddSkill(View Button) {
		shortToast("onClickAddSkill(View Button)");
	}
	
	/**
	 * 
	 * @param Button
	 */
	public void onClickAddZertifikat(View Button) {
		shortToast("onClickAddZertifikat(View Button)");
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
