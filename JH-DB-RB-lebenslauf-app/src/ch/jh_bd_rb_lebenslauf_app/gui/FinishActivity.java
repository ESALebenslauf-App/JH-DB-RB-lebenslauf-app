package ch.jh_bd_rb_lebenslauf_app.gui;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.SendItem;
import ch.jh_bd_rb_lebenslauf_app.listener.CreatePdfListener;
import ch.jh_bd_rb_lebenslauf_app.listener.SendListener;
import ch.jh_bd_rb_lebenslauf_app.resource.StringConst;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
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
	private CreatePdfListener createPdfListener;
	private SendItem sendItem;
	private Long persID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);
		this.persID = getIntent().getLongExtra(StringConst.PERSID, 0);

		initActivityElemente();
		initActivityListener();
	}

	private void initActivityElemente() {
		btnZusammenfassung = (Button) findViewById(R.id.buttonZusammenfassung);
		btnCreatePdf = (Button) findViewById(R.id.btnCreatePdf);
		btnPreferences = (Button) findViewById(R.id.btnPreferences);
		btnSenden = (Button) findViewById(R.id.btnSenden);
		btnSenden.setEnabled(false);
	}

	private void initActivityListener() {
		// Listener für das Erstellen eines PDFs

		sendItem = new SendItem();
		createPdfListener = new CreatePdfListener(this, persID, sendItem);
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

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.finish, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_finish_pdfliste:
			final Intent intent = new Intent(this, ListPDFLebenslaufActivity.class);
			intent.putExtra(StringConst.PERSID, getPersID());
			this.startActivity(intent);
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
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
		intent.putExtra(StringConst.PERSID, getPersID());
		startActivity(intent);
	}

	public Long getPersID() {
		return persID;
	}

	public void setPersID(Long persID) {
		this.persID = persID;
	}

}
