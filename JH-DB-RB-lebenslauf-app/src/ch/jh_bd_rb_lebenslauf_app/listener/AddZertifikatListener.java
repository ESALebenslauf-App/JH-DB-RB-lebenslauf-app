package ch.jh_bd_rb_lebenslauf_app.listener;

import ch.jh_bd_rb_lebenslauf_app.gui.SkillsActivity;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class AddZertifikatListener implements OnClickListener {
	private Activity skillsActivity;

	public AddZertifikatListener(SkillsActivity skills) {
		this.skillsActivity = skills;
	}

	@Override
	public void onClick(View arg0) {

		// Zertifikat Laden
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_PICK);
		skillsActivity.startActivityForResult(intent, 0);
	}

}
