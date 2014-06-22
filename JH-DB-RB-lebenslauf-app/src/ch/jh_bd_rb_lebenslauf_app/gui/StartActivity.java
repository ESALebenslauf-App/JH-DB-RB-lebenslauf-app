package ch.jh_bd_rb_lebenslauf_app.gui;

import ch.jh_bd_rb_lebenslauf_app.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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

		final Intent intent = new Intent(this, BildActivity.class);

		startActivity(intent);
	}

}
