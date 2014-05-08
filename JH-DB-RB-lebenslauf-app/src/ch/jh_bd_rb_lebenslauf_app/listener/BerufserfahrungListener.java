package ch.jh_bd_rb_lebenslauf_app.listener;

import ch.jh_bd_rb_lebenslauf_app.gui.Berufserfahrung;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class BerufserfahrungListener implements OnClickListener {
	private Activity bildActivity;

	public BerufserfahrungListener(Berufserfahrung berufserfahrung) {
		this.bildActivity = berufserfahrung;
	}

	@Override
	public void onClick(View arg0) {
		shortToast("Eine Berufserfahrung hinzufügen");

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
