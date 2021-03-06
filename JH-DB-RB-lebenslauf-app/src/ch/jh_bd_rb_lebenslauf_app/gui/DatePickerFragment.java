package ch.jh_bd_rb_lebenslauf_app.gui;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.resource.StringConst;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;

/**
 * Erstellt einen DatePicker f�r die Activity Bildung f�rdie Von und Bis Butten.
 * Aendert die Buttenbeschrieftung auf das neu gewaelte Datum und Speichert
 * dieses ab. Die Fragmente muessen mit den Tags "datePickerVon" und
 * "datePickerBis" versehen sein.
 * 
 * @author jherzig
 * 
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

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
		case StringConst.DATEPICKERVON:
			updateBttonVon(date);
			break;
		case StringConst.DATEPICKERBIS:
			updateBttonBis(date);
			break;
		case StringConst.DATEPICKERGEBURTSDATUM:
			updateBttonGeburtsdatum(date);
			break;
		default:
			break;
		}
	}

	private void updateBttonGeburtsdatum(String date) {
		Button btnGeburtsdatum = (Button) getActivity().findViewById(R.id.btn_Bild_Geburtsdatum);
		btnGeburtsdatum.setText(date);
	}

	/**
	 * Aktualisiert den den Von Butten mit dem gew�hlten datum
	 * 
	 * @param date
	 */
	private void updateBttonVon(String date) {
		Button btnSelectDateVon = (Button) getActivity().findViewById(R.id.btnSelectDateVon);
		btnSelectDateVon.setText(date);
	}

	/**
	 * Aktualisiert den den Bis Butten mit dem gew�hlten datum
	 * 
	 * @param date
	 */
	private void updateBttonBis(String date) {
		Button btnSelectDateBis = (Button) getActivity().findViewById(R.id.btnSelectDateBis);
		btnSelectDateBis.setText(date);
	}

}
