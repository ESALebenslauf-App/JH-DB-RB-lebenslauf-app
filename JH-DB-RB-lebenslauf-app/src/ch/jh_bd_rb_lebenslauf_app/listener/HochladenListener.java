package ch.jh_bd_rb_lebenslauf_app.listener;

import ch.jh_bd_rb_lebenslauf_app.gui.BildActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class HochladenListener implements OnClickListener {
	private Activity bildActivity;

	public HochladenListener(BildActivity bild) {
		this.bildActivity = bild;
	}

	@Override
	public void onClick(View v) {
		pickPicture();

	}

	public void pickPicture() {
		final Intent photoPicker = new Intent(Intent.ACTION_PICK);
		photoPicker.setType("image/*");
		bildActivity.startActivityForResult(photoPicker, 2);
	}

	/**
	 * 
	 * @param text
	 */

}
