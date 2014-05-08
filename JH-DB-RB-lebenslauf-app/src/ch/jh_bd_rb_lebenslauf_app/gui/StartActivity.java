package ch.jh_bd_rb_lebenslauf_app.gui;

import ch.jh_bd_rb_lebenslauf_app.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 *
 */
public class StartActivity extends Activity {
	private Button btnErfassen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		initActivityElemente();
		initActivityListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}


	
	/**
	 * Initialisiert alle benötigten Elemente aus der Activity
	 * 
	 */
	private void initActivityElemente() {
		btnErfassen = (Button) findViewById(R.id.button_erfassen);
	}
	
	/**
	 * Initialisiert alle Listener aus der Activity
	 * 
	 */
	private void initActivityListener() {
		btnErfassen.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View button) {
				clickErfassen(button);
				
			}
		});
	
	}
	
	/**
	 * 
	 * @param buttonErfassen
	 */
	public void clickErfassen(View button) {

		final Intent intent = new Intent(this, Bild.class);
		
		startActivity(intent);
	}

}
