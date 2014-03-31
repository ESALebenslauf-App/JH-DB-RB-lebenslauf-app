package ch.jh_bd_rb_lebenslauf_app.gui;

import java.util.ArrayList;
import java.util.Calendar;

import ch.jh_bd_rb_lebenslauf_app.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class Berufserfahrung extends Activity {

	// Start DatePicker
	Button btnSelectDateVon, btnSelectDateBis;
	static final int DATE_VON_DIALOG_ID = 0;
	static final int DATE_BIS_DIALOG_ID = 1;
	public int yearVon, monthVon, dayVon, yearBis, monthBis, dayBis;
	private int mYear, mMonth, mDay;
	// End DatePicker

	String name;
	String adresse;
	static final String NAME = "name";
	static final String ADRESSE = "adresse";
	ArrayList<String> berufserfahrungen = new ArrayList<String>();
	static final String BERUFSERFAHRUNGEN = "berufserfahrungen";

	public Berufserfahrung() {
		// Start DatePicker
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		
		// End DatePicker
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_berufserfahrung);

		// Start DatePicker
		// get the references of buttons
		btnSelectDateBis = (Button) findViewById(R.id.btnSelectDateBis);
		btnSelectDateVon = (Button) findViewById(R.id.btnSelectDateVon);
		btnSelectDateBis.setText(mDay + "." + mMonth + "." + mYear);
		btnSelectDateVon.setText(mDay + "." + mMonth + "." + mYear);

		btnSelectDateBis.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO überarbeiten der veralteten Method 
				showDialog(DATE_BIS_DIALOG_ID);

			}
		});

		btnSelectDateVon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO überarbeiten der veralteten Method
				showDialog(DATE_VON_DIALOG_ID);

			}
		});
		// End DatePicker

		final Bundle extras = getIntent().getExtras();

		if (extras != null) {
			name = extras.getString(Bild.NAME);
			adresse = extras.getString(Bild.ADRESSE);
		}
	}

	// Start DatePicker
	// TODO überarbeiten der veralteten Method DatePicker
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_BIS_DIALOG_ID:
			shortToast("DATE_BIS_DIALOG_ID:");
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
			// create a new TimePickerDialog with values you want to show
		case DATE_VON_DIALOG_ID:
			shortToast("DATE_VON_DIALOG_ID:");
			return new DatePickerDialog(this, mDateSetListenerVon, mYear, mMonth,
					mDay);

		}
		return null;
	}

	// Register DatePickerDialog listener
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		// the callback received when the user "sets" the Date in the
		// DatePickerDialog
		public void onDateSet(DatePicker view, int yearSelected,
				int monthOfYear, int dayOfMonth) {
			yearBis = yearSelected;
			monthBis = monthOfYear;
			dayBis = dayOfMonth;
			// Set the Selected Date in Select date Button
			btnSelectDateBis.setText(dayBis + "." + monthBis + "." + yearBis);
		}
	};

	// Register DatePickerDialog listener
	private DatePickerDialog.OnDateSetListener mDateSetListenerVon = new DatePickerDialog.OnDateSetListener() {
		// the callback received when the user "sets" the Date in the
		// DatePickerDialog
		public void onDateSet(DatePicker view, int yearSelected,
				int monthOfYear, int dayOfMonth) {
			yearVon = yearSelected;
			monthVon = monthOfYear;
			dayVon = dayOfMonth;
			// Set the Selected Date in Select date Button
			btnSelectDateVon.setText(dayVon + "." + monthVon + "." + yearVon);
		}
	};
	// End DatePicker

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.berufserfahrung, menu);
		return true;
	}

	public void onClickBild(View Button) {
		final Intent intent = new Intent(this, Bild.class);
		startActivity(intent);
	}

	public void onClickBildung(View Button) {
		final Intent intent = new Intent(this, Bildung.class);

		intent.putExtra(NAME, name);
		intent.putExtra(ADRESSE, adresse);

		intent.putStringArrayListExtra(BERUFSERFAHRUNGEN, berufserfahrungen);

		startActivity(intent);
	}

	public void onClickAddBerufserfahrung(View Button) {

		shortToast("onClickAddBerufserfahrung(View Button)");

		/*
		 * String berufserfahrung;
		 * 
		 * 
		 * final EditText txtFirma = (EditText)
		 * findViewById(R.id.edt_berufserfahrung_firma); String firma =
		 * txtFirma.getText().toString();
		 * 
		 * final EditText txtDauer = (EditText)
		 * findViewById(R.id.edt_berufserfahrung_dauer); String dauer =
		 * txtDauer.getText().toString();
		 * 
		 * final EditText txtTatigkeit = (EditText)
		 * findViewById(R.id.edt_berufserfahrung_taetigkeit); String tatigkeit =
		 * txtTatigkeit.getText().toString();
		 * 
		 * berufserfahrung = firma + "/" + dauer + "/" + tatigkeit;
		 * 
		 * berufserfahrungen.add(berufserfahrung);
		 */

	}

	private void shortToast(String text) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
