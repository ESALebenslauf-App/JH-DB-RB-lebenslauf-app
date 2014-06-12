package ch.jh_bd_rb_lebenslauf_app.listener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungData;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BerufserfahrungListener implements OnClickListener {

	private Activity berufserfahrungenActivity;
	private EditText txt_firma;
	private EditText txt_titel;
	private EditText txt_adresse;
	private EditText txt_plz;
	private EditText txt_ort;
	private EditText txt_taetigkeit;
	private Button btnSelectDateVon;
	private Button btnSelectDateBis;
	private ArrayList<BerufserfahrungData> berufserfahrungen;


	public BerufserfahrungListener(Activity myActivity) {
		this.berufserfahrungenActivity = myActivity;
		berufserfahrungen = new ArrayList<BerufserfahrungData>();
		init();
	}

	@Override
	public void onClick(View arg0) {
		BerufserfahrungData berufserfahrungData = new BerufserfahrungData(
				getTxt_firma().getText().toString(), getTxt_titel().getText()
						.toString(), getTxt_adresse().getText().toString(),
				getTxt_plz().getText().toString(), getTxt_ort().getText()
						.toString(), getTxt_taetigkeit().getText().toString(),
				getBtnSelectDateVon().getText().toString(),
				getBtnSelectDateBis().getText().toString());
		
		berufserfahrungen.add((BerufserfahrungData) berufserfahrungData);
		
		getTxt_firma().setText("");
		getTxt_titel().setText("");
		getTxt_adresse().setText("");
		getTxt_plz().setText("");
		getTxt_ort().setText("");
		getTxt_taetigkeit().setText("");
		getBtnSelectDateVon().setText("");
		getBtnSelectDateBis().setText("");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy",Locale.GERMANY);
        String datum = dateFormat.format(new java.util.Date());
		getBtnSelectDateBis().setText(datum);;
		getBtnSelectDateVon().setText(datum);
		
		shortToast("Eine Berufserfahrung hinzufügen");

	}

	private void init() {
		setTxt_firma((EditText) berufserfahrungenActivity
				.findViewById(R.id.txt_firma));
		setTxt_titel((EditText) berufserfahrungenActivity
				.findViewById(R.id.txt_titel));
		setTxt_adresse((EditText) berufserfahrungenActivity
				.findViewById(R.id.txt_adresse));
		setTxt_plz((EditText) berufserfahrungenActivity
				.findViewById(R.id.txt_plz));
		setTxt_ort((EditText) berufserfahrungenActivity
				.findViewById(R.id.txt_ort));
		setTxt_taetigkeit((EditText) berufserfahrungenActivity
				.findViewById(R.id.txt_taetigkeit));
		setBtnSelectDateVon((Button) berufserfahrungenActivity
				.findViewById(R.id.btnSelectDateVon));
		setBtnSelectDateBis((Button) berufserfahrungenActivity
				.findViewById(R.id.btnSelectDateBis));

	}

	public EditText getTxt_firma() {
		return txt_firma;
	}

	public void setTxt_firma(EditText txt_firma) {
		this.txt_firma = txt_firma;
	}

	public EditText getTxt_titel() {
		return txt_titel;
	}

	public void setTxt_titel(EditText txt_titel) {
		this.txt_titel = txt_titel;
	}

	public EditText getTxt_adresse() {
		return txt_adresse;
	}

	public void setTxt_adresse(EditText txt_adresse) {
		this.txt_adresse = txt_adresse;
	}

	public EditText getTxt_plz() {
		return txt_plz;
	}

	public void setTxt_plz(EditText txt_plz) {
		this.txt_plz = txt_plz;
	}

	public EditText getTxt_ort() {
		return txt_ort;
	}

	public void setTxt_ort(EditText txt_ort) {
		this.txt_ort = txt_ort;
	}

	public EditText getTxt_taetigkeit() {
		return txt_taetigkeit;
	}

	public void setTxt_taetigkeit(EditText txt_taetigkeit) {
		this.txt_taetigkeit = txt_taetigkeit;
	}

	public Button getBtnSelectDateVon() {
		return btnSelectDateVon;
	}

	public void setBtnSelectDateVon(Button btnSelectDateVon) {
		this.btnSelectDateVon = btnSelectDateVon;
	}

	public Button getBtnSelectDateBis() {
		return btnSelectDateBis;
	}

	public void setBtnSelectDateBis(Button btnSelectDateBis) {
		this.btnSelectDateBis = btnSelectDateBis;
	}

	/**
	 * 
	 * @param text
	 */
	private void shortToast(String text) {
		Context context = berufserfahrungenActivity;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	public ArrayList<BerufserfahrungData> getBerufserfahrungen() {
		return berufserfahrungen;
	}

}
