package ch.jh_bd_rb_lebenslauf_app.listener;

import ch.jh_bd_rb_lebenslauf_app.gui.Skills;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class AddZertifikatListener implements OnClickListener {
	private Activity bildActivity;

	public AddZertifikatListener(Skills skills) {
		this.bildActivity = skills;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Klasse ausbauen
		shortToast("Neues Zertrifikat wurde erfasst");

	}
	
	/**
	 * 
	 * @param text
	 */
	private void shortToast(String text) {
		Context context = bildActivity;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

}
