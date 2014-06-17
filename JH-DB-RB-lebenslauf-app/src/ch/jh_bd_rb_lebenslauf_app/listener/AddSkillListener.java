package ch.jh_bd_rb_lebenslauf_app.listener;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsData;
import ch.jh_bd_rb_lebenslauf_app.daten.Zertifikat;
import ch.jh_bd_rb_lebenslauf_app.gui.SkillsActivity;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.Spinner;

public class AddSkillListener implements OnClickListener {

	private Activity skillsActivity;
	private Zertifikat zert;
	private String zertifikat;
	private Spinner skillSpinner;
	private SeekBar skillSeekBar;
	private ArrayList<SkillsData> skillsList;

	public AddSkillListener(SkillsActivity skills) {
		this.skillsActivity = skills;
		this.zert = skills.getZertifikat();
		skillsList = new ArrayList<SkillsData>();
		init();
	}

	private void init() {
		setSkillSpinner((Spinner) skillsActivity
				.findViewById(R.id.spinnersprachen));
		setSkillSeekBar((SeekBar) skillsActivity
				.findViewById(R.id.edt_skills_sprachen));
	}

	@Override
	public void onClick(View arg0) {
		SkillsData skillsData = new SkillsData(getSkillSpinner()
				.getSelectedItem().toString(), String.valueOf(getSkillSeekBar()
				.getProgress()), zert.getPath());
		skillsList.add(skillsData);
		
		getSkillSpinner().setSelection(0);
		getSkillSeekBar().setProgress(0);

	}

	public String getZertifikat() {
		return zertifikat;
	}

	public void setZertifikat(String zertifikat) {
		this.zertifikat = zertifikat;
	}

	public Spinner getSkillSpinner() {
		return skillSpinner;
	}

	public void setSkillSpinner(Spinner skillSpinner) {
		this.skillSpinner = skillSpinner;
	}

	public SeekBar getSkillSeekBar() {
		return skillSeekBar;
	}

	public void setSkillSeekBar(SeekBar skillSeekBar) {
		this.skillSeekBar = skillSeekBar;
	}

	public ArrayList<SkillsData> getSkillsList() {
		return skillsList;
	}

}
