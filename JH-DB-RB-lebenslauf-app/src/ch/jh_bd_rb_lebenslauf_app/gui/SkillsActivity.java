package ch.jh_bd_rb_lebenslauf_app.gui;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsDB;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsData;
import ch.jh_bd_rb_lebenslauf_app.daten.Zertifikat;
import ch.jh_bd_rb_lebenslauf_app.listener.AddSkillListener;
import ch.jh_bd_rb_lebenslauf_app.listener.AddZertifikatListener;
import ch.jh_bd_rb_lebenslauf_app.resource.IntigerConst;
import ch.jh_bd_rb_lebenslauf_app.resource.StringConst;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */
public class SkillsActivity extends Activity {

	private Button btnZusammenfassung;
	private Button btnBildung;
	private Button btnAddSkill;
	private ImageButton btnAddZertifikat;
	private AddZertifikatListener zertifikatListener;
	private AddSkillListener addSkillListener;
	private Zertifikat zertifikat = new Zertifikat();
	private Spinner skillSpinner;
	private SeekBar skillSeekBar;
	private Long persID;
	private Long ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skills);
		setPersID(getIntent().getLongExtra(StringConst.PERSID, 0));
		setID(getIntent().getLongExtra(StringConst.ID, 0));

		initActivityElemente();
		initActivityListener();
		loadData();
	}

	private void initActivityElemente() {
		btnZusammenfassung = (Button) findViewById(R.id.buttonZusammenfassung);
		btnBildung = (Button) findViewById(R.id.imageView7);
		btnAddSkill = (Button) findViewById(R.id.btnAddSkill);
		btnAddZertifikat = (ImageButton) findViewById(R.id.btnImageAddZertifikat);
		btnAddZertifikat.setEnabled(false);

		setSkillSpinner((Spinner) findViewById(R.id.spinnersprachen));
		setSkillSeekBar((SeekBar) findViewById(R.id.edt_skills_sprachen));
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
		addSkillListener = new AddSkillListener(SkillsActivity.this, getPersID(), getID());
		btnAddSkill.setOnClickListener(addSkillListener);
		zertifikatListener = new AddZertifikatListener(this);
		btnAddZertifikat.setOnClickListener(zertifikatListener);

		skillSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if (skillSpinner.getSelectedItemId() > 0) {
					btnAddZertifikat.setEnabled(true);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				btnAddZertifikat.setEnabled(false);
			}
		});
	}

	private void loadData() {
		if (getID() > 0) {
			SkillsData skill = new SkillsData(getID());

			SkillsDB db = new SkillsDB(this);
			db.open();
			skill = db.getSkill(skill);
			db.close();

			Resources res = getResources();
			int selectet = 0;
			String[] skillsName = res.getStringArray(R.array.skills_array);

			for (int i = 0; i < skillsName.length; i++) {
				if (skillsName[i].equals(skill.getWas())) {
					selectet = i;
				}
			}

			getSkillSpinner().setSelection(selectet);
			getSkillSeekBar().setProgress(Integer.parseInt(skill.getAusmass()));

		}

	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// Wenn ein Foto neu geschossen wurde
		if (requestCode == IntigerConst.PHOTO && resultCode == RESULT_OK) {
			Bitmap image = (Bitmap) data.getExtras().get("data");
			getZertifikat().setImage(image);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.skills, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_skills_list:
			final Intent intent = new Intent(this, ListSkillsActivity.class);
			intent.putExtra(StringConst.PERSID, getPersID());
			this.startActivity(intent);
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * 
	 * @param Button
	 */
	public void clickBildung(View Button) {
		final Intent intent = new Intent(this, BildungActivity.class);
		intent.putExtra(StringConst.PERSID, getPersID());

		startActivity(intent);
	}

	/**
	 * 
	 * @param Button
	 */
	public void clickZusammenfassung(View Button) {
		final Intent intent = new Intent(this, ZusammenfassungActivity.class);
		intent.putExtra(StringConst.PERSID, getPersID());

		startActivity(intent);
	}

	public Zertifikat getZertifikat() {
		return zertifikat;
	}

	public Long getPersID() {
		return persID;
	}

	private Long getID() {
		return ID;
	}

	private void setID(Long iD) {
		ID = iD;
	}

	private void setPersID(Long persID) {
		this.persID = persID;
	}

	private Spinner getSkillSpinner() {
		return skillSpinner;
	}

	private void setSkillSpinner(Spinner skillSpinner) {
		this.skillSpinner = skillSpinner;
	}

	private SeekBar getSkillSeekBar() {
		return skillSeekBar;
	}

	private void setSkillSeekBar(SeekBar skillSeekBar) {
		this.skillSeekBar = skillSeekBar;
	}

}
