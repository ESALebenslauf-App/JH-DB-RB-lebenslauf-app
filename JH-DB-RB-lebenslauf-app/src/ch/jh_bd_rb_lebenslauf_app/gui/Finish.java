package ch.jh_bd_rb_lebenslauf_app.gui;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.listener.CreatePdfListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */
public class Finish extends Activity {
	private Button btnZusammenfassung;
	private Button btnCreatePdf;
	private Button btnPreferences;
	private CreatePdfListener createPdfListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);

		initActivityElemente();
		initActivityListener();
	}

	private void initActivityElemente() {
		btnZusammenfassung = (Button) findViewById(R.id.buttonZusammenfassung);
		btnCreatePdf = (Button) findViewById(R.id.btnCreatePdf);
		btnPreferences = (Button) findViewById(R.id.btnPreferences);
	}

	private void initActivityListener() {
		// Listener für das Erstellen eines PDFs
		// getFilesDir() für die übergabe des Pfades(falls nötig)
		String dir = getFilesDir().toString();

		createPdfListener = new CreatePdfListener(this, dir);
		btnCreatePdf.setOnClickListener(createPdfListener);

		btnPreferences.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View button) {

				openPreferences(button);

			}

		});

		btnZusammenfassung.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View button) {
				clickZusammenfassung(button);
			}

		});

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
	public void openPreferences(View Button) {
		final Intent intent = new Intent(this, PreferencesActivity.class);
		startActivity(intent);
	}
	
	public void clickZusammenfassung(View Button) {
		final Intent intent = new Intent(this, Zusammenfassung.class);
		startActivity(intent);
	}

}
