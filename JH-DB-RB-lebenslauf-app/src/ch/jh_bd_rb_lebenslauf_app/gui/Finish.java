package ch.jh_bd_rb_lebenslauf_app.gui;

import ch.jh_bd_rb_lebenslauf_app.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
/**
 * 
 * @author bdervishi.jherzig.rbuess
 *
 */
public class Finish extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finish, menu);
		return true;
	}
	
	/**
	 * 
	 * @param Button
	 */
	public void onClickZusammenfassung(View Button) {
		final Intent intent = new Intent(this, Zusammenfassung.class);
		startActivity(intent);
	}

}
