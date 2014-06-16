package ch.jh_bd_rb_lebenslauf_app.gui;



import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.SendItem;
import ch.jh_bd_rb_lebenslauf_app.listener.CreatePdfListener;
import ch.jh_bd_rb_lebenslauf_app.listener.SendListener;
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
public class FinishActivity extends Activity {
	private Button btnZusammenfassung;
	private Button btnCreatePdf;
	private Button btnPreferences;
	private Button btnSenden;
	private Button btnPdfList;
	private CreatePdfListener createPdfListener;
	private SendItem sendItem;

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
		btnSenden = (Button) findViewById(R.id.btnSenden);
		btnSenden.setEnabled(false);
		btnPdfList = (Button) findViewById(R.id.btnPdfListAnzeigen);
	}

	private void initActivityListener() {
		// Listener für das Erstellen eines PDFs
		// getFilesDir() für die übergabe des Pfades(falls nötig)
		String dir = getFilesDir().toString();

		sendItem = new SendItem();
		createPdfListener = new CreatePdfListener(this, dir, sendItem);
		btnCreatePdf.setOnClickListener(createPdfListener);
		btnSenden.setOnClickListener(new SendListener(this, sendItem));

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

		btnPdfList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clickPdfList();
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finish, menu);
		return true;
	}

	private void clickPdfList() {
		final Intent intent = new Intent(this, ListPDFLebenslauf.class);
		this.startActivity(intent);
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
		final Intent intent = new Intent(this, ZusammenfassungActivity.class);
		startActivity(intent);
	}

}
