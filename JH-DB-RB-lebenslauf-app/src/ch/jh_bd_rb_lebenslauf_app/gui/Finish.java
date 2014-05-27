package ch.jh_bd_rb_lebenslauf_app.gui;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.listener.CreatePdfListener;
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
public class Finish extends Activity {
	private Button btnZusammenfassung;
	private Button btnCreatePdf;
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
	}

	private void initActivityListener() {
		//Listener f�r das Erstellen eines PDFs
		//getFilesDir() f�r die �bergabe des Pfades(falls n�tig)
		String dir = getFilesDir().toString();
		createPdfListener = new CreatePdfListener(this, dir);
		btnCreatePdf.setOnClickListener(createPdfListener);

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
	public void clickZusammenfassung(View Button) {
		final Intent intent = new Intent(this, Zusammenfassung.class);
		startActivity(intent);
	}

}
