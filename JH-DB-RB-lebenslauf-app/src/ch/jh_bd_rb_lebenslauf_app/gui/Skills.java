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

public class Skills extends Activity {

	String name;
	String adresse;
	public static final String NAME = "name";
	public static final String ADRESSE = "adresse";
	public ArrayList<String> berufserfahrungen = new ArrayList<String>();
	public static final String BERUFSERFAHRUNGEN = "berufserfahrungen";
	public ArrayList<String> bildungen = new ArrayList<String>();
	public static final String BILDUNGEN = "bildung";
	public String skillGrad;
	public static final String SKILL = "skill";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skills);

		final Bundle extras = getIntent().getExtras();
		if (extras != null) {
			berufserfahrungen = extras
					.getStringArrayList(Bildung.BERUFSERFAHRUNGEN);
			name = extras.getString(Bildung.NAME);
			adresse = extras.getString(Bildung.ADRESSE);
			bildungen = extras.getStringArrayList(Bildung.BILDUNGEN);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.skills, menu);
		return true;
	}

	public void onClickBildung(View Button) {
		final Intent intent = new Intent(this, Bildung.class);
		startActivity(intent);
	}

	public void onClickZusammenfassung(View Button) {
		final Intent intent = new Intent(this, Zusammenfassung.class);
		
		intent.putExtra(NAME, name);
		intent.putExtra(ADRESSE, adresse);

		intent.putStringArrayListExtra(BERUFSERFAHRUNGEN, berufserfahrungen);
		
		intent.putStringArrayListExtra(BILDUNGEN, bildungen);
		
		intent.putExtra(SKILL, skillGrad);

		startActivity(intent);
	}

	public void onClickAddSkill(View Button) {
		shortToast("onClickAddSkill(View Button)");
	

	}
	private void shortToast(String text) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

}
