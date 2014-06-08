package ch.jh_bd_rb_lebenslauf_app.listener;


import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienDB;
import ch.jh_bd_rb_lebenslauf_app.gui.Bild;
import android.R;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class BildListener extends Activity implements OnClickListener {
	private Activity bildActivity;
	private PersonalienDB db;

	public BildListener(Bild bild) {
		this.bildActivity = bild;
	}

	/*@Override
	public void onClick(View arg0) {
		shortToast("Neues Bild erstellen");

	}*/
	
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

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		db = new PersonalienDB(this);
		db.open();
		
		long id = getIntent().getLongExtra("_id", -1);
		

	}
	
	private void setViewText(int id, String text) {
		((TextView) findViewById(id)).setText(text);
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
