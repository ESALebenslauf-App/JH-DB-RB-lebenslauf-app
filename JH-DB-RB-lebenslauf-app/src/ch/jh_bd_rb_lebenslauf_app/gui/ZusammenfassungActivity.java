package ch.jh_bd_rb_lebenslauf_app.gui;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienDB;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsDB;
import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienData;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsData;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungData;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungData;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */
public class ZusammenfassungActivity extends Activity {

	private Button btnSkills;
	private Button btnFinish;

	private TextView edtPersonalien;
	private TextView edtBerufserfahrung;
	private TextView edtBildung;
	private TextView edtSkills;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zusammenfassung);

		final Bundle extras = getIntent().getExtras();
		if (extras != null) {

		}

		initActivityElemente();
		initActivityListener();
		getShowData();
	}

	private void initActivityElemente() {
		btnSkills = (Button) findViewById(R.id.buttonSkills);
		btnFinish = (Button) findViewById(R.id.buttonFinish);
		// Textfelder für die Lebenslaufdaten
		edtPersonalien = (TextView) findViewById(R.id.edt_zusammenfassung_personalien);
		edtBerufserfahrung = (TextView) findViewById(R.id.edt_zusammenfassung_berufserfahrung);
		edtBildung = (TextView) findViewById(R.id.edt_zusammenfassung_bildung);
		edtSkills = (TextView) findViewById(R.id.edt_zusammenfassung_skills);

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

		startActivity(intent);

	}

	/**
	 * 
	 * @param Button
	 */
	public void clickFinish(View Button) {
		final Intent intent = new Intent(this, FinishActivity.class);

		startActivity(intent);
	}

	// Lädt die Daten aus der DB und stellt sie als Text dar.
	public void getShowData() {

		Spanned txtPersonalien;

		PersonalienDB personalienDB = new PersonalienDB(this);
		BerufserfahrungDB berufserfahrungDB = new BerufserfahrungDB(this);
		BildungDB bildungDB = new BildungDB(this);
		SkillsDB skillsDB = new SkillsDB(this);

		// TODO Muss noch angepasst werden! Laden der Personalien und
		// dazugehörigen Daten.
		personalienDB.open();
		ArrayList<PersonalienData> personalienArray = personalienDB
				.getAllPersonalien();
		personalienDB.close();

		berufserfahrungDB.open();
		// TESTDATEN
		BerufserfahrungData BE = new BerufserfahrungData("Luftwaffe",
				"Appliaktionsmanager", "Schaffä", "So lala",
				"Flugsicherungsstrasse 1-5", "8602", "Wangen", "Heute",
				"Morgen");
		BE.setPersID(new Long(1000));
		berufserfahrungDB.insertBerufserfahrung(BE);
		berufserfahrungDB.insertBerufserfahrung(BE);
		berufserfahrungDB.insertBerufserfahrung(BE);
		// TEST DATEN BIS HIER

		ArrayList<BerufserfahrungData> berufserfahrungArray = berufserfahrungDB
				.getAllBerufserfahrung();

		berufserfahrungDB.close();

		bildungDB.open();

		// TESTDATEN
		BildungData BI = new BildungData("Weiterbildung", "FFHS", "8000",
				"Schulstrasse", "heute", "morgen");
		BI.setPersID(new Long(1000));
		bildungDB.insertBildung(BI);
		bildungDB.insertBildung(BI);
		bildungDB.insertBildung(BI);
		// TEST DATEN BIS HIER

		ArrayList<BildungData> bildungArray = bildungDB.getAllBildungen();
		bildungDB.close();

		skillsDB.open();
		
		//TESTDATEN
		SkillsData SK = new SkillsData("Englisch", "fliessend", "First");
		SK.setPers_id(new Long(1000));
		skillsDB.insertSkills(SK);
		skillsDB.insertSkills(SK);
		skillsDB.insertSkills(SK);
		//TEST DATEN BIS HIER
		
		ArrayList<SkillsData> skillsArray = skillsDB.getAllSkills();
		skillsDB.close();

		// ////////////////////// PERSONALIEN
		// Die Personalien laden und als Text an das htmlPersonalien übergeben.
		// TODO Test Daten können gelöscht werden:
		String anrede = "Herr";
		String name = "Buess";
		String vorname = "Reto";
		String strasse = "Gumpisbüelstrasse 19";
		String plz = "8600";
		String ort = "Dübendorf";
		String date = "05.09.1985";

		if (personalienArray.size() > 0) {

			PersonalienData personalien = personalienArray.get(0);
			anrede = personalien.getAnrede();
			name = personalien.getName();
			vorname = personalien.getVorname();
			strasse = personalien.getStrasse();
			plz = personalien.getPlz();
			ort = personalien.getOrt();
			date = personalien.getDate();
			// String bild = personalien.getBild();
		}
		// Übergibt die Daten als Text an ein Spanned.
		txtPersonalien = Html.fromHtml(anrede + "<br />" + vorname + " " + name
				+ "<br />" + strasse + "<br />" + plz + " " + ort + "<br />"
				+ date);
		// Spanned www = Html.fromHtml("TEXT");
		// CharSequence xxx = TextUtils.concat(txtPersonalien, www);
		// Übergibt den Spanned an den TextView.
		edtPersonalien.setText(txtPersonalien);

		// ///////////////////////// BERUFSERFAHRUNG
		// Die Berufserfahrungen laden und als Text an die TextView übergeben.
		Spanned textBerufserfahrung = Html.fromHtml("");

		for (BerufserfahrungData current : berufserfahrungArray) {
			BerufserfahrungData berufserfahrung = (BerufserfahrungData) current;

			// TODO Test Daten können gelöscht werden.

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
			Spanned addBerufserfahrungText = Html.fromHtml("<b>Firma: </b>"
					+ firma + "<br />" + "<b>" + "Titel: " + "</b>" + titel
					+ "<br />" + "<b>Adresse: </b><br />" + adresse + "<br />"
					+ plzFirma + ortFirma + "<br />" + "<b>Tätigkeit: </b>"
					+ taetigkeit + "<br />" + "<b>Dauer: </b>" + vonFirma
					+ " bis " + bisFirma + "<br />" + "<b>Beschreibung: </b>"
					+ beschreibung + "<br />----------------<br />");

			// Fügt den bisherigen Text mit dem neuen Spanned zusammen.
			textBerufserfahrung = (Spanned) TextUtils.concat(
					textBerufserfahrung, addBerufserfahrungText);

		}

		edtBerufserfahrung.setText(textBerufserfahrung);

		// //////////////////////////////// BILDUNG
		// Die Berufserfahrungen laden und als Text an das htmlBerufserfahrung
		// übergeben.
		Spanned textBildung = Html.fromHtml("");
		for (BildungData current : bildungArray) {
			BildungData bildung = (BildungData) current;

			// TODO Test Daten können gelöscht werden.

			String ausbildungsart = bildung.getAusbildungsart();
			String nameSchule = bildung.getNameschule();
			String plzSchule = bildung.getPlz();
			String adresse = bildung.getAdresseSchule();
			String vonSchule = bildung.getDatumVon();
			String bisSchule = bildung.getDatumBis();

			// Schreibt die Berufserfahrung Daten mit HTML in ein Spanned.
			Spanned addBildungText = Html.fromHtml("<b>Ausbildungsart: </b>"
					+ ausbildungsart + "<br />" + "<b>" + "Name der Schule: "
					+ "</b>" + nameSchule + "<br />" + "<b>Adresse: </b><br />"
					+ plzSchule + " " + adresse + "<br />" + "<b>Dauer: </b>"
					+ vonSchule + " bis " + bisSchule
					+ "<br />----------------<br />");

			// Fügt den bisherigen Text mit dem neuen Spanned zusammen.
			textBildung = (Spanned) TextUtils.concat(textBildung,
					addBildungText);

		}

		edtBildung.setText(textBildung);

		// SKILLS
		// Die Berufserfahrungen laden und als Text an das htmlBerufserfahrung
		// übergeben.
		Spanned textSkills = Html.fromHtml("");
		for (SkillsData current : skillsArray) {
			SkillsData skills = (SkillsData) current;

			String was = skills.getWas();
			String ausmass = skills.getAusmass();
			String zertifikat = skills.getZertifikat();

			// Schreibt die Skills Daten mit HTML in ein Spanned.
			Spanned addSkillsText = Html.fromHtml("<b>Was: </b>" + was
					+ "<br />" + "<b>" + "Ausmass: " + "</b>" + ausmass
					+ "<br />" + "<b>" + "Zertifikat: " + "</b>" + zertifikat
					+ "<br />----------------<br />");

			// Fügt den bisherigen Text mit dem neuen Spanned zusammen.
			textSkills = (Spanned) TextUtils.concat(textSkills, addSkillsText);

		}

		edtSkills.setText(textSkills);

	}

}
