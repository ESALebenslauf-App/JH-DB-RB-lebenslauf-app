package ch.jh_bd_rb_lebenslauf_app.gui;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsDB;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsData;
import ch.jh_bd_rb_lebenslauf_app.daten.Zertifikat;
import ch.jh_bd_rb_lebenslauf_app.listener.AddSkillListener;
import ch.jh_bd_rb_lebenslauf_app.listener.AddZertifikatListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */
public class SkillsActivity extends Activity {

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

	private Button btnZusammenfassung;
	private Button btnBildung;
	private Button btnAddSkill;
	private ImageButton btnAddZertifikat;
	private AddZertifikatListener zertifikatListener;
	private AddSkillListener addSkillListener;
	private Zertifikat zertifikat = new Zertifikat();

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

		initActivityElemente();
		initActivityListener();

	}

	private void initActivityElemente() {
		btnZusammenfassung = (Button) findViewById(R.id.buttonZusammenfassung);
		btnBildung = (Button) findViewById(R.id.imageView7);
		btnAddSkill = (Button) findViewById(R.id.btnAddSkill);
		btnAddZertifikat = (ImageButton) findViewById(R.id.btnImageAddZertifikat);
	}

	private void initActivityListener() {
		btnZusammenfassung.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View butten) {
				clickZusammenfassung(butten);
			}
		});

		btnBildung.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View button) {
				clickBildung(button);
			}
		});

		addSkillListener = new AddSkillListener(this);
		btnAddSkill.setOnClickListener(addSkillListener);
		zertifikatListener = new AddZertifikatListener(this);
		btnAddZertifikat.setOnClickListener(zertifikatListener);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {

		case 0: {
			zertifikat.setPath(data.getDataString());
			break;
		}
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
	public void clickBildung(View Button) {
		final Intent intent = new Intent(this, BildungActivity.class);
		startActivity(intent);
	}

	/**
	 * 
	 * @param Button
	 */
	public void clickZusammenfassung(View Button) {
		final Intent intent = new Intent(this, ZusammenfassungActivity.class);

		intent.putExtra(NAME, name);
		intent.putExtra(ADRESSE, adresse);

		intent.putStringArrayListExtra(BERUFSERFAHRUNGEN, berufserfahrungen);

		intent.putStringArrayListExtra(BILDUNGEN, bildungen);

		intent.putExtra(SKILL, skillGrad);

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

	public Zertifikat getZertifikat() {
		return zertifikat;
	}

	/**
	 * Daten können Persistent gespeichert werden
	 */
	private void datenSpeichern() {

		boolean save = false;
		ArrayList<SkillsData> killsList = addSkillListener.getSkillsList();
		if (killsList.size() > 0) {

			for (SkillsData current : killsList) {

				SkillsData skills = (SkillsData) current;

				// Datenbank
				SkillsDB skillsDB = new SkillsDB(this);
				skillsDB.open();
				skills = skillsDB.insertSkills(skills);
				skillsDB.close();
				if (skills.getID() > 1) {
					save = true;
				}
			}

			if (save) {
				Toast toast = Toast.makeText(this, "Daten wurden gespeichert.",
						Toast.LENGTH_LONG);
				toast.show();

			}

		}
	}

}
