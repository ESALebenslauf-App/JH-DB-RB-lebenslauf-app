package ch.jh_bd_rb_lebenslauf_app.gui;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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

	private Button btnSkills;
	private Button btnFinish;

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

		}

		initActivityElemente();
		initActivityListener();
	}

	private void initActivityElemente() {
		btnSkills = (Button) findViewById(R.id.buttonSkills);
		btnFinish = (Button) findViewById(R.id.buttonFinish);
	}

	private void initActivityListener() {
		btnFinish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View button) {
				clickFinish(button);
			}
		});

		btnSkills.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View button) {
				clickSkills(button);
			}
		});

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
	public void clickSkills(View Button) {
		final Intent intent = new Intent(this, Skills.class);

		startActivity(intent);

	}

	/**
	 * 
	 * @param Button
	 */
	public void clickFinish(View Button) {
		final Intent intent = new Intent(this, Finish.class);

		startActivity(intent);
	}
}
