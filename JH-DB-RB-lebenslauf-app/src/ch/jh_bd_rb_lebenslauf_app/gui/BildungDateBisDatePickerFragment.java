package ch.jh_bd_rb_lebenslauf_app.gui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import ch.jh_bd_rb_lebenslauf_app.R;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class BildungDateBisDatePickerFragment extends DialogFragment implements
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

	@Override
	public void onDateSet(DatePicker datePicker, int year, int month, int day) {
		// Workaround da die Methode onDataSet zweimal aufgerufen wird
		if (second) {
			second = false;
			return;
		}
		second = true;
		cal.set(year, month, day);
		updateBtton();
		saveDate();
	}

	/**
	 * Aktualisiert den den bis Butten mit dem gwählten datum
	 */
	private void updateBtton() {
		Button btnSelectDateBis = (Button) getActivity().findViewById(
				R.id.btnSelectDateBis);
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		String dateButton = dateFormat.format(cal.getTime());
		btnSelectDateBis.setText(dateButton);
	}

	/**
	 * Speichert das gewählte Datum ab
	 */
	private void saveDate() {
		// TODO Erfasste Daten abspeichern
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		String dateSave = dateFormat.format(cal.getTime());
		String strToast = "Das Datum wird gespeichert: " + dateSave;

		Toast toast = Toast
				.makeText(getActivity(), strToast, Toast.LENGTH_LONG);
		toast.show();
	}
}
