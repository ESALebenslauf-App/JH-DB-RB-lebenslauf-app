package ch.jh_bd_rb_lebenslauf_app.gui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienDB;
import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienData;
import ch.jh_bd_rb_lebenslauf_app.listener.BildListener;
import ch.jh_bd_rb_lebenslauf_app.listener.HochladenListener;
import ch.jh_bd_rb_lebenslauf_app.resource.FileConst;
import ch.jh_bd_rb_lebenslauf_app.resource.StringConst;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */
public class BildActivity extends FragmentActivity {

	private Bitmap image = null;
	private String imageString = "";
	private ImageButton btnCamera;
	private ImageButton btnHochladen;
	private Spinner spinnerAnrede;
	private EditText txt_vorname;
	private EditText txt_name;
	private EditText txt_adresse;
	private EditText text_edit_plz;
	private EditText txt_Edit_ort;
	private Button btnGeburtsdatum;
	private Button btnBerufserfahrung;
	private Long persID;
	private boolean save = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bild);
		this.persID = getIntent().getLongExtra(StringConst.getPesrid(), 0);

		initActivityElemente();
		initActivityListener();
		loadData();
	}

	private void initActivityElemente() {
		btnBerufserfahrung = (Button) findViewById(R.id.buttonBerufserfahrung);
		btnCamera = (ImageButton) findViewById(R.id.kamAuswahl);
		btnHochladen = (ImageButton) findViewById(R.id.picAuswahl);
		setText_edit_plz((EditText) findViewById(R.id.text_edit_plz));
		setTxt_adresse((EditText) findViewById(R.id.txt_adresse));
		setBtnGeburtsdatum((Button) findViewById(R.id.btn_Bild_Geburtsdatum));
		getBtnGeburtsdatum().setText(StringConst.GEBURTSDATUM);
		setTxt_Edit_ort((EditText) findViewById(R.id.txt_Edit_ort));
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

		btnGeburtsdatum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new DatePickerFragment();
				newFragment.show(getSupportFragmentManager(),
						StringConst.DATEPICKERGEBURTSDATUM);
			}
		});

		btnCamera.setOnClickListener(new BildListener(this));
		btnHochladen.setOnClickListener(new HochladenListener(this));

	}

	private void loadData() {
		if (persID > 0) {
			PersonalienData pers = new PersonalienData(persID);
			PersonalienDB persDB = new PersonalienDB(this);
			persDB.open();
			pers = persDB.getPersonalien(pers);

			getTxt_name().setText(pers.getName());
			getTxt_vorname().setText(pers.getVorname());
			getTxt_adresse().setText(pers.getStrasse());
			getText_edit_plz().setText(pers.getPlz());
			getTxt_Edit_ort().setText(pers.getOrt());
			getBtnGeburtsdatum().setText(pers.getDate());

			Resources res = getResources();
			String[] anrede = res.getStringArray(R.array.anrede_array);

			if (anrede[0].equals(pers.getAnrede())) {
				getSpinnerAnrede().setSelection(0);
			}
			if (anrede[1].equals(pers.getAnrede())) {
				getSpinnerAnrede().setSelection(1);
			}
			if (anrede[2].equals(pers.getAnrede())) {
				getSpinnerAnrede().setSelection(2);
			}
		}
	}

	/**
	 * 
	 * @param Button
	 */
	public void clickBildBerufserfahrung(View Button) {

		final Intent intent = new Intent(this, BerufserfahrungActivity.class);

		intent.putExtra(StringConst.getPesrid(), datenSpeichern());
		startActivity(intent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_pers_list:
			final Intent intent = new Intent(this, ListPersonenActivity.class);
			this.startActivity(intent);
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private Long datenSpeichern() {
		PersonalienData pers;

		if (persID > 0) {

			pers = saveData(StringConst.UPDATE);
		} else {
			pers = saveData(StringConst.INSERT);

			this.persID = pers.getID();
		}

		return persID;
	}

	@SuppressLint("UseValueOf")
	private PersonalienData saveData(String saveDB) {

		PersonalienData pers = new PersonalienData(getSpinnerAnrede()
				.getSelectedItem().toString(), getTxt_name().getText()
				.toString(), getTxt_vorname().getText().toString(),
				getTxt_adresse().getText().toString(), getText_edit_plz()
						.getText().toString(), getTxt_Edit_ort().getText()
						.toString(), getBtnGeburtsdatum().getText().toString(),
				"bild");

		// Datenbank
		if (!pers.getName().equals("") || !pers.getVorname().equals("")) {
			PersonalienDB personalienDB = new PersonalienDB(this);
			personalienDB.open();
			switch (saveDB) {
			case StringConst.UPDATE:
				pers.setID(persID);
				pers = personalienDB.updatePersonalien(pers);
				break;
			case StringConst.INSERT:
				pers = personalienDB.insertPersonalieng(pers);
				break;
			default:
				break;
			}
			// Hier wird die Speicherung des Fotos ausgelöst und die Personen ID
			// als eindeutige Bezeichnung mitgegeben.
			if (image != null) {
				pers.setBild(pers.getID() + saveImage(pers.getID()));
			}

			personalienDB.close();
			if (pers.getID() > 0) {
				save = true;
			}

			if (save) {
				Toast toast = Toast.makeText(
						this,
						StringConst.getDatenWurdenGespeichert()
								+ StringConst.getIhrePresid() + pers.getID(),
						Toast.LENGTH_LONG);
				toast.show();
			}
		} else {
			pers.setID(new Long(0));
			Toast toast = Toast.makeText(this,
					StringConst.getDatenWurdenNichtGespeichert(),
					Toast.LENGTH_LONG);
			toast.show();
		}
		return pers;
	}

	/**
	 * 
	 * @author rbuess
	 * 
	 */
	// Diese Methode handelt das geschossene(oder aus der Galry gewählte) Foto
	// vom BildListener
	// Der BildListener sendet das Ergebnis zurück an die BildActivity.
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// Wenn ein Foto neu geschossen wurde
		if (requestCode == 1 && resultCode == RESULT_OK) {
			image = (Bitmap) data.getExtras().get("data");
			Toast toast = Toast.makeText(this, "Foto gespeichert",
					Toast.LENGTH_LONG);
			toast.show();
		}
		// Wenn ein Foto aus der Galery ausgewählt wurde
		if (requestCode == 2 && resultCode == RESULT_OK) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String filePath = cursor.getString(columnIndex);
			cursor.close();

			image = BitmapFactory.decodeFile(filePath);

			Toast toast = Toast.makeText(this, "Foto gespeichert",
					Toast.LENGTH_LONG);
			toast.show();
		}
	}

	/**
	 * 
	 * @author rbuess
	 * 
	 */
	// Diese Methode Speichert das Foto und gibt die Personen ID zu der
	// Bezeichnung hinzu
	public String saveImage(Long id) {

		String ID = id.toString();

		String fotoName = "Foto.jpg";

		String pfad = FileConst.getPdfPath();

		String fileName = pfad + "/" + ID + fotoName;

		File fotoFile = new File(fileName);

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		try {
			FileOutputStream fos = new FileOutputStream(fotoFile);
			fos.write(byteArray);
			fos.close();
		} catch (Exception error) {
		}

		imageString = fotoName;
		return imageString;

	}

	public Button getBtnBerufserfahrung() {
		return btnBerufserfahrung;
	}

	public void setBtnBerufserfahrung(Button btnBerufserfahrung) {
		this.btnBerufserfahrung = btnBerufserfahrung;
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

	public Spinner getSpinnerAnrede() {
		return spinnerAnrede;
	}

	public void setSpinnerAnrede(Spinner spinnerAnrede) {
		this.spinnerAnrede = spinnerAnrede;
	}

	public Button getBtnGeburtsdatum() {
		return btnGeburtsdatum;
	}

	public void setBtnGeburtsdatum(Button btnGeburtsdatum) {
		this.btnGeburtsdatum = btnGeburtsdatum;
	}
}
