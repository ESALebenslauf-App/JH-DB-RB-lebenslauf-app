package ch.jh_bd_rb_lebenslauf_app.listener;

import ch.jh_bd_rb_lebenslauf_app.gui.BildActivity;
import ch.jh_bd_rb_lebenslauf_app.resource.IntigerConst;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */

public class BildListener extends Activity implements OnClickListener {
	private Activity bildActivity;

	public BildListener(BildActivity bild) {
		this.bildActivity = bild;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onClick(View v) {
		makePicture();

	}

	// Methode die die Kamera öffnet
	// Das geschossene und ausgewählte Foto wird an die BildActivity gesendet.
	public void makePicture() {
		final Intent cameraInt = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

		bildActivity.startActivityForResult(cameraInt, IntigerConst.PHOTO);
	}

}
