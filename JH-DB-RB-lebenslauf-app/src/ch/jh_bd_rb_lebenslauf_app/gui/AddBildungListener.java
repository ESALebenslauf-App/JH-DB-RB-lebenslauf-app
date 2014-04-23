package ch.jh_bd_rb_lebenslauf_app.gui;

import ch.jh_bd_rb_lebenslauf_app.R;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class AddBildungListener implements OnClickListener {
	private final Context bildung;
	private Activity bildungActivity;

	public AddBildungListener(Activity myActivity) {
		this.bildung = myActivity;
		this.bildungActivity = myActivity;
	}

	@Override
	public void onClick(View arg0) {
		TextView edt_bildung_schule = (TextView) bildungActivity
				.findViewById(R.id.edt_bildung_schule);

		Toast toast = Toast.makeText(bildung, "Eingabe im Feld Schule: "
				+ edt_bildung_schule.getText(), Toast.LENGTH_LONG);
		toast.show();

	}

}
