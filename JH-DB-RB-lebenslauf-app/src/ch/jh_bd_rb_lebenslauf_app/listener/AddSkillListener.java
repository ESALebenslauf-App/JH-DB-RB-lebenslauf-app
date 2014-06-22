package ch.jh_bd_rb_lebenslauf_app.listener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsDB;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsData;
import ch.jh_bd_rb_lebenslauf_app.gui.SkillsActivity;
import ch.jh_bd_rb_lebenslauf_app.resource.FileConst;
import ch.jh_bd_rb_lebenslauf_app.resource.StringConst;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * @author j.herzig
 * 
 */
public class AddSkillListener implements OnClickListener {

	final int PHOTO = 1;
	private SkillsActivity skillsActivity;
	private String zertifikat;
	private Spinner skillSpinner;
	private SeekBar skillSeekBar;
	private Long persID;
	private Long ID;

	public AddSkillListener(SkillsActivity skills, Long persId, Long id) {
		this.skillsActivity = skills;
		setPersID(persId);
		setID(id);
		init();

	}

	private void init() {
		setSkillSpinner((Spinner) skillsActivity.findViewById(R.id.spinnersprachen));
		setSkillSeekBar((SeekBar) skillsActivity.findViewById(R.id.edt_skills_sprachen));
	}

	@Override
	public void onClick(View arg0) {
		saveData();
		setID(new Long(0));
	}

	private SkillsData saveData() {
		String zert = "";

		

		SkillsData skillsData = new SkillsData(getSkillSpinner().getSelectedItem().toString(),
				String.valueOf(getSkillSeekBar().getProgress()), zert);

		skillsData.setID(getID());
		skillsData.setPers_id(getPersID());

		// Datenbank
		if (skillsData.getWas().equals("")) {
			shortToast(StringConst.DATEN_WURDEN_NICHT_GESPEICHERT_SKILLS);
		} else {
			SkillsDB db = new SkillsDB(skillsActivity);
			db.open();

			if (skillsData.getID() > 0) {
				skillsData = db.updateSkills(skillsData);
			} else {
				skillsData = db.insertSkills(skillsData);
			}
			db.close();
			setID(skillsData.getID());

			activityBereinigen();

			shortToast(StringConst.DATEN_WURDEN_GESPEICHERT);
		}
		
		if (skillsActivity.getImageZertifikat() != null) {
			zert = saveImage(skillsData);
		}

		return skillsData;

	}

	public String saveImage(SkillsData skillsData) {

		String zert = "_";
		zert = zert + skillsData.getWas();

		String fotoName = zert + "Zertifikat.jpg";

		String pfad = FileConst.getPdfPath();

		String fileName = pfad + "/" + skillsData.getPers_id() + "_" + skillsData.getID() + fotoName;

		File fotoFile = new File(fileName);

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		skillsActivity.getImageZertifikat().compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		try {
			FileOutputStream fos = new FileOutputStream(fotoFile);
			fos.write(byteArray);
			fos.close();
		} catch (Exception error) {
		}

		return zert;

	}

	private void activityBereinigen() {
		getSkillSpinner().setSelection(0);
		getSkillSeekBar().setProgress(0);
	}

	/**
	 * 
	 * @param text
	 */
	private void shortToast(String text) {
		Context context = skillsActivity;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
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

	private Long getPersID() {
		return persID;
	}

	private void setPersID(Long persID) {
		this.persID = persID;
	}

	private Long getID() {
		return ID;
	}

	private void setID(Long iD) {
		ID = iD;
	}

}
