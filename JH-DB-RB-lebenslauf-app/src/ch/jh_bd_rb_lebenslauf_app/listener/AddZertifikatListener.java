package ch.jh_bd_rb_lebenslauf_app.listener;

import ch.jh_bd_rb_lebenslauf_app.gui.SkillsActivity;
import ch.jh_bd_rb_lebenslauf_app.resource.IntigerConst;
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
		makePicture();
	}

	// Methode die die Kamera öffnet
	// Das geschossene und ausgewählte Foto wird an die BildActivity gesendet.
	public void makePicture() {
		final Intent cameraInt = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

		skillsActivity.startActivityForResult(cameraInt, IntigerConst.PHOTO);
	}

}
