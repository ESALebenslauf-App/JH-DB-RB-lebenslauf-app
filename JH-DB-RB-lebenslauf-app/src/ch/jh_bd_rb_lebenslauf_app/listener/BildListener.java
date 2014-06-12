package ch.jh_bd_rb_lebenslauf_app.listener;


import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienDB;
import ch.jh_bd_rb_lebenslauf_app.gui.Bild;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class BildListener extends Activity implements OnClickListener {
	@SuppressWarnings("unused")
	private Activity bildActivity;
	private PersonalienDB db;

	public BildListener(Bild bild) {
		this.bildActivity = bild;
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		db = new PersonalienDB(this);
		db.open();
		

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		db.open();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		db.close();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
