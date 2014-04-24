package ch.jh_bd_rb_lebenslauf_app.gui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import ch.jh_bd_rb_lebenslauf_app.R;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

/**
 * Erstellt einen DatePicker für die Activity Bildung fèrdie Von und Bis Butten.
 * Aendert die Buttenbeschrieftung auf das neu gewaelte Datum und Speichert
 * dieses ab. Die Fragmente muessen mit den Tags "datePickerVon" und
 * "datePickerBis" versehen sein.
 * 
 * @author j.herzig
 * 
 */
public class BildungDatePickerFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	private Calendar cal = Calendar.getInstance();
	private static final String DATE_FORMAT = "dd.MM.yyyy";
	boolean second = false;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		second = true;
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);

	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public void onDateSet(DatePicker datePicker, int year, int month, int day) {
		// Workaround da die Methode onDataSet zweimal aufgerufen wird
		if (second) {
			second = false;
			return;
		}
		second = true;
		cal.set(year, month, day);

		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		String date = dateFormat.format(cal.getTime());

		String tag = this.getTag();

		switch (tag) {
		case "datePickerVon":
			updateBttonVon(date);
			saveDateVon(date);
			break;
		case "datePickerBis":
			updateBttonBis(date);
			saveDateBis(date);
			break;
		default:
			break;
		}
	}

	/**
	 * Aktualisiert den den Von Butten mit dem gewählten datum
	 * 
	 * @param date
	 */
	private void updateBttonVon(String date) {
		Button btnSelectDateVon = (Button) getActivity().findViewById(
				R.id.btnSelectDateVon);
		btnSelectDateVon.setText(date);
	}

	/**
	 * Speichert das gewählte Von Datum ab
	 * 
	 * @param date
	 */
	private void saveDateVon(String date) {
		// TODO Erfasste Daten abspeichern
		String strToast = "Das Datum Von wird gespeichert: " + date;

		Toast toast = Toast
				.makeText(getActivity(), strToast, Toast.LENGTH_LONG);
		toast.show();
	}

	/**
	 * Aktualisiert den den Bis Butten mit dem gewählten datum
	 * 
	 * @param date
	 */
	private void updateBttonBis(String date) {
		Button btnSelectDateBis = (Button) getActivity().findViewById(
				R.id.btnSelectDateBis);
		btnSelectDateBis.setText(date);
	}

	/**
	 * Speichert das gewählte bis Datum ab
	 * 
	 * @param date
	 */
	private void saveDateBis(String date) {
		// TODO Erfasste Daten abspeichern
		String strToast = "Das Datum Bis wird gespeichert: " + date;

		Toast toast = Toast
				.makeText(getActivity(), strToast, Toast.LENGTH_LONG);
		toast.show();
	}
}
