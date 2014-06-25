package ch.jh_bd_rb_lebenslauf_app.gui;

import java.io.File;
import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.LebenslaufDB;
import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienDB;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsDB;
import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienData;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsData;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungData;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungData;
import ch.jh_bd_rb_lebenslauf_app.resource.FileConst;
import ch.jh_bd_rb_lebenslauf_app.resource.StringConst;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author rbuess
 * 
 */
public class ZusammenfassungActivity extends Activity {

	private Button btnSkills;
	private Button btnFinish;

	private TextView edtPersonalien;
	private TextView edtBerufserfahrung;
	private TextView edtBildung;
	private TextView edtSkills;
	private Long persID;

	Bitmap image;
	ImageView bild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zusammenfassung);
		this.persID = getIntent().getLongExtra(StringConst.PERSID, 0);

		initActivityElemente();
		initActivityListener();
		getShowData();
	}

	private void initActivityElemente() {
		btnSkills = (Button) findViewById(R.id.buttonSkills);
		btnFinish = (Button) findViewById(R.id.buttonFinish);
		// Textfelder f�r die Lebenslaufdaten
		edtPersonalien = (TextView) findViewById(R.id.edt_zusammenfassung_personalien);
		edtBerufserfahrung = (TextView) findViewById(R.id.edt_zusammenfassung_berufserfahrung);
		edtBildung = (TextView) findViewById(R.id.edt_zusammenfassung_bildung);
		edtSkills = (TextView) findViewById(R.id.edt_zusammenfassung_skills);

		// Hier wird der Pfad auf das gespeicherte Foto zusammengestellt.
		// und das Foto geladen.
		String filePath = FileConst.getPdfPath() + "/" + persID.toString() + "Foto.jpg";
		//Pr�fen ob Foto existiert
		File f = new File(filePath);
		if(f.exists()){
		image = BitmapFactory.decodeFile(filePath);
		}
		else{
			image = BitmapFactory.decodeResource(getResources(), R.drawable.ico_pic);
		}
		
		bild = (ImageView) findViewById(R.id.imageView1);
		bild.setImageBitmap(image);

	}

	private void initActivityListener() {
		btnFinish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View button) {
				clickFinish(button);
			}
		});

		btnSkills.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View button) {
				clickSkills(button);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zusammenfassung, menu);

		return true;
	}

	/**
	 * 
	 * @param Button
	 */
	public void clickSkills(View Button) {
		final Intent intent = new Intent(this, SkillsActivity.class);
		intent.putExtra(StringConst.PERSID, getPersID());

		startActivity(intent);

	}

	/**
	 * 
	 * @param Button
	 */
	public void clickFinish(View Button) {
		final Intent intent = new Intent(this, FinishActivity.class);
		intent.putExtra(StringConst.PERSID, getPersID());
		startActivity(intent);
	}

	// L�dt die Daten aus der DB und stellt sie als Text dar.
	public void getShowData() {

		Spanned txtPersonalien;

		PersonalienDB personalienDB = new PersonalienDB(this);
		BerufserfahrungDB berufserfahrungDB = new BerufserfahrungDB(this);
		BildungDB bildungDB = new BildungDB(this);
		SkillsDB skillsDB = new SkillsDB(this);

		personalienDB.open();
		PersonalienData personalienData = new PersonalienData(getPersID());
		ArrayList<PersonalienData> personalienArray = personalienDB.getPersonalienRows(personalienData,
				LebenslaufDB.PERS_ID);
		personalienDB.close();

		berufserfahrungDB.open();
		BerufserfahrungData berufserfahrungData = new BerufserfahrungData();
		berufserfahrungData.setPersID(getPersID());
		ArrayList<BerufserfahrungData> berufserfahrungArray = berufserfahrungDB.getBerufserfarungRows(
				berufserfahrungData, LebenslaufDB.BERUF_PERS_ID);
		berufserfahrungDB.close();

		bildungDB.open();
		BildungData bildungData = new BildungData();
		bildungData.setPersID(getPersID());
		ArrayList<BildungData> bildungArray = bildungDB.getBildungRows(bildungData, LebenslaufDB.BILDUNG_PERS_ID);
		bildungDB.close();

		skillsDB.open();
		SkillsData skillsData = new SkillsData();
		skillsData.setPers_id(getPersID());
		ArrayList<SkillsData> skillsArray = skillsDB.getSkillsRows(skillsData, LebenslaufDB.SKILLS_PERS_ID);
		skillsDB.close();

		// ////////////////////// PERSONALIEN

		if (personalienArray.size() > 0) {
			PersonalienData personalien = personalienArray.get(0);
			String anrede = personalien.getAnrede();
			String name = personalien.getName();
			String vorname = personalien.getVorname();
			String strasse = personalien.getStrasse();
			String plz = personalien.getPlz();
			String ort = personalien.getOrt();
			String date = personalien.getDate();

			// �bergibt die Daten als Text an ein Spanned.
			txtPersonalien = Html.fromHtml(anrede + "<br />" + vorname + " " + name + "<br />" + strasse + "<br />"
					+ plz + " " + ort + "<br />" + date);

			// Spanned www = Html.fromHtml("TEXT");
			// CharSequence xxx = TextUtils.concat(txtPersonalien, www);
			// �bergibt den Spanned an den TextView.
			edtPersonalien.setText(txtPersonalien);
		}
		// ///////////////////////// BERUFSERFAHRUNG
		// Die Berufserfahrungen laden und als Text an die TextView �bergeben.
		Spanned textBerufserfahrung = Html.fromHtml("");

		for (BerufserfahrungData current : berufserfahrungArray) {
			BerufserfahrungData berufserfahrung = (BerufserfahrungData) current;

			String firma = berufserfahrung.getTxt_firma();
			String titel = berufserfahrung.getTxt_titel();
			String adresse = berufserfahrung.getTxt_adresse();
			String plzFirma = berufserfahrung.getTxt_plz();
			String ortFirma = berufserfahrung.getTxt_ort();
			String taetigkeit = berufserfahrung.getTxt_taetigkeit();
			String vonFirma = berufserfahrung.getBtnSelectDateVon();
			String bisFirma = berufserfahrung.getBtnSelectDateBis();
			String beschreibung = berufserfahrung.getTxt_beschreibung();

			// Schreibt die Berufserfahrung Daten mit HTML in ein Spanned.
			Spanned addBerufserfahrungText = Html.fromHtml("<b>Firma: </b>" + firma + "<br />" + "<b>" + "Titel: "
					+ "</b>" + titel + "<br />" + "<b>Adresse: </b><br />" + adresse + "<br />" + plzFirma + ortFirma
					+ "<br />" + "<b>T�tigkeit: </b>" + taetigkeit + "<br />" + "<b>Dauer: </b>" + vonFirma + " bis "
					+ bisFirma + "<br />" + "<b>Beschreibung: </b>" + beschreibung + "<br />----------------<br />");

			// F�gt den bisherigen Text mit dem neuen Spanned zusammen.
			textBerufserfahrung = (Spanned) TextUtils.concat(textBerufserfahrung, addBerufserfahrungText);

		}

		edtBerufserfahrung.setText(textBerufserfahrung);

		// //////////////////////////////// BILDUNG
		// Die Berufserfahrungen laden und als Text an das htmlBerufserfahrung
		// �bergeben.
		Spanned textBildung = Html.fromHtml("");
		for (BildungData current : bildungArray) {
			BildungData bildung = (BildungData) current;

			String ausbildungsart = bildung.getAusbildungsart();
			String nameSchule = bildung.getNameschule();
			String plzSchule = bildung.getPlz();
			String adresse = bildung.getAdresseSchule();
			String vonSchule = bildung.getDatumVon();
			String bisSchule = bildung.getDatumBis();

			// Schreibt die Berufserfahrung Daten mit HTML in ein Spanned.
			Spanned addBildungText = Html.fromHtml("<b>Ausbildungsart: </b>" + ausbildungsart + "<br />" + "<b>"
					+ "Name der Schule: " + "</b>" + nameSchule + "<br />" + "<b>Adresse: </b><br />" + plzSchule + " "
					+ adresse + "<br />" + "<b>Dauer: </b>" + vonSchule + " bis " + bisSchule
					+ "<br />----------------<br />");

			// F�gt den bisherigen Text mit dem neuen Spanned zusammen.
			textBildung = (Spanned) TextUtils.concat(textBildung, addBildungText);

		}

		edtBildung.setText(textBildung);

		// SKILLS
		// Die Berufserfahrungen laden und als Text an das htmlBerufserfahrung
		// �bergeben.
		Spanned textSkills = Html.fromHtml("");
		for (SkillsData current : skillsArray) {
			SkillsData skills = (SkillsData) current;

			String was = skills.getWas();
			String ausmass = skills.getAusmass();
			String zertifikat = skills.getZertifikat();

			// Schreibt die Skills Daten mit HTML in ein Spanned.
			Spanned addSkillsText = Html.fromHtml("<b>Was: </b>" + was + "<br />" + "<b>" + "Ausmass: " + "</b>"
					+ ausmass + "<br />" + "<b>" + "Zertifikat: " + "</b>" + zertifikat
					+ "<br />----------------<br />");

			// F�gt den bisherigen Text mit dem neuen Spanned zusammen.
			textSkills = (Spanned) TextUtils.concat(textSkills, addSkillsText);

		}

		edtSkills.setText(textSkills);

	}

	public Long getPersID() {
		return persID;
	}

}
