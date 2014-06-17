package ch.jh_bd_rb_lebenslauf_app.gui;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienDB;
import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienData;
import ch.jh_bd_rb_lebenslauf_app.listener.BildListener;
import ch.jh_bd_rb_lebenslauf_app.listener.HochladenListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */
public class BildActivity extends Activity {

	// TODO �berarbeiten und mit eigenem Objekt arbeiten
	// private String anrede;
	private String name;
	// private String vorname;
	private String strasse;
	// private int plz;
	// private String ort;
	// private String geb;
	// private String bild;
	private Button btnBerufserfahrung;
	private ImageButton btnCamera;
	private ImageButton btnHochladen;
	private Spinner spinnerAnrede;
	private EditText txt_vorname;

	private EditText txt_name;
	private EditText txt_adresse;
	private EditText text_edit_plz;
	private EditText txt_Edit_ort;
	private EditText txt_Edit_geb;
	ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bild);
		initActivityElemente();
		initActivityListener();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void initActivityElemente() {
		btnBerufserfahrung = (Button) findViewById(R.id.buttonBerufserfahrung);
		btnCamera = (ImageButton) findViewById(R.id.kamAuswahl);
		btnHochladen = (ImageButton) findViewById(R.id.picAuswahl);
		setText_edit_plz((EditText) findViewById(R.id.txt_Edit_ort));
		setTxt_adresse((EditText) findViewById(R.id.txt_adresse));
		setTxt_Edit_geb((EditText) findViewById(R.id.txt_Edit_geb));
		setTxt_Edit_ort((EditText) findViewById(R.id.txt_adresse));
		setTxt_name((EditText) findViewById(R.id.txt_name));
		setTxt_vorname((EditText) findViewById(R.id.txt_vorname));
		setSpinnerAnrede((Spinner) findViewById(R.id.spinnerAnrede));
	}

	private void initActivityListener() {
		btnBerufserfahrung.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View button) {
				clickBildBerufserfahrung(button);
			}
		});

		btnCamera.setOnClickListener(new BildListener(this));
		btnHochladen.setOnClickListener(new HochladenListener(this));

	}

	/**
	 * 
	 * @param Button
	 */
	public void clickBildBerufserfahrung(View Button) {

		final Intent intent = new Intent(this, BerufserfahrungActivity.class);

		intent.putExtra(name, "Name");
		intent.putExtra(strasse, "Strasse");
		startActivity(intent);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0) {
			// TODO wird dieser Code be�tigt wenn ja f�r was?
			// Bitmap theImage = (Bitmap) data.getExtras().get("data");

		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		datenSpeichern();
	}

	@Override
	protected void onStop() {
		super.onStop();
		datenSpeichern();
	}

	/**
	 * Daten k�nnen Persistent gespeichert werden
	 */
	private void datenSpeichern() {

		boolean save = false;
		PersonalienData pers = new PersonalienData(getSpinnerAnrede()
				.getSelectedItem().toString(), getTxt_name().getText().toString(), getTxt_vorname()
				.getText().toString(), getTxt_adresse().getText().toString(), getText_edit_plz()
				.getText().toString(), getTxt_Edit_ort().getText().toString(), getTxt_Edit_geb()
				.getText().toString(), "bild");

		// Datenbank
		PersonalienDB personalienDB = new PersonalienDB(this);
		personalienDB.open();
		pers = personalienDB.insertPersonalieng(pers);
		personalienDB.close();
		if (pers.getID() > 0) {
			save = true;
		}

		if (save) {
			Toast toast = Toast.makeText(this, "Daten wurden gespeichert.",
					Toast.LENGTH_LONG);
			toast.show();
		}

	}

	public EditText getTxt_vorname() {
		return txt_vorname;
	}

	public void setTxt_vorname(EditText txt_vorname) {
		this.txt_vorname = txt_vorname;
	}

	public EditText getTxt_name() {
		return txt_name;
	}

	public void setTxt_name(EditText txt_name) {
		this.txt_name = txt_name;
	}

	public EditText getTxt_adresse() {
		return txt_adresse;
	}

	public void setTxt_adresse(EditText txt_adresse) {
		this.txt_adresse = txt_adresse;
	}

	public EditText getText_edit_plz() {
		return text_edit_plz;
	}

	public void setText_edit_plz(EditText text_edit_plz) {
		this.text_edit_plz = text_edit_plz;
	}

	public EditText getTxt_Edit_ort() {
		return txt_Edit_ort;
	}

	public void setTxt_Edit_ort(EditText txt_Edit_ort) {
		this.txt_Edit_ort = txt_Edit_ort;
	}

	public EditText getTxt_Edit_geb() {
		return txt_Edit_geb;
	}

	public void setTxt_Edit_geb(EditText txt_Edit_geb) {
		this.txt_Edit_geb = txt_Edit_geb;
	}

	public Spinner getSpinnerAnrede() {
		return spinnerAnrede;
	}

	public void setSpinnerAnrede(Spinner spinnerAnrede) {
		this.spinnerAnrede = spinnerAnrede;
	}

}
