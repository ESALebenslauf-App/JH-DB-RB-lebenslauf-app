package ch.jh_bd_rb_lebenslauf_app.listener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungData;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungData;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.LebenslaufDB;
import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienData;
import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienDB;
import ch.jh_bd_rb_lebenslauf_app.daten.SendItem;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsDB;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsData;
import ch.jh_bd_rb_lebenslauf_app.resource.FileConst;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfDocument.Page;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CreatePdfListener implements OnClickListener {
	private SendItem sendItem;
	private Button btnSenden;

	private static Font titelF;
	private static Font ueberschriftF;
	private static Font standardF;

	static String titelU = "Lebenslauf";
	static String personalienU = "Personalien";
	static String bildungU = "Bildung";
	static String berufserfahrungU = "Berufserfahrung";
	static String skillsU = "Skills";
	static String personalienText = "Hier kommen die Personalien hin";
	static String bildung = "Hier kommt die Bildung hin";
	static String berufserfahrung = "Hier kommt die Berufserfahrung hin";
	static String skills = "Hier kommen die Skills hin";

	static Long persID;
	static String fotoName;
	static Bitmap bitmap;
	static Bitmap bitmapResized;
	static Image image = null;

	String dir;
	Activity finishActivity;
	SharedPreferences prefs;

	String toastText = "PDF erstellt: (";
	String toastSize;
	String toastArt;
	String toastEnglish;

	boolean pdfEnglisch;
	boolean pdfGross;
	boolean pdfArt;
	static int groesse = 11;
	static FontFamily schrift = Font.FontFamily.TIMES_ROMAN;

	// TEST-Konstruktor mit Aktivity und Directory
	public CreatePdfListener(Activity finishActivity, Long id, SendItem sendItem) {
		this.persID = id;
		this.finishActivity = finishActivity;
		this.sendItem = sendItem;
		btnSenden = (Button) finishActivity.findViewById(R.id.btnSenden);
		getShowData();
	}

	// Lädt die Daten aus der DB und stellt sie als Text dar.
	public void getShowData() {

		Spanned txtPersonalien;

		PersonalienDB personalienDB = new PersonalienDB(finishActivity);
		BerufserfahrungDB berufserfahrungDB = new BerufserfahrungDB(
				finishActivity);
		BildungDB bildungDB = new BildungDB(finishActivity);
		SkillsDB skillsDB = new SkillsDB(finishActivity);

		personalienDB.open();
		PersonalienData personalienData = new PersonalienData(getPersID());
		ArrayList<PersonalienData> personalienArray = personalienDB
				.getPersonalienRows(personalienData, LebenslaufDB.PERS_ID);
		personalienDB.close();

		berufserfahrungDB.open();
		BerufserfahrungData berufserfahrungData = new BerufserfahrungData();
		berufserfahrungData.setPersID(getPersID());
		ArrayList<BerufserfahrungData> berufserfahrungArray = berufserfahrungDB
				.getBerufserfarungRows(berufserfahrungData,
						LebenslaufDB.BERUF_PERS_ID);
		berufserfahrungDB.close();

		bildungDB.open();
		BildungData bildungData = new BildungData();
		bildungData.setPersID(getPersID());
		ArrayList<BildungData> bildungArray = bildungDB.getBildungRows(
				bildungData, LebenslaufDB.BILDUNG_PERS_ID);
		bildungDB.close();

		skillsDB.open();
		SkillsData skillsData = new SkillsData();
		skillsData.setPers_id(getPersID());
		ArrayList<SkillsData> skillsArray = skillsDB.getSkillsRows(skillsData,
				LebenslaufDB.SKILLS_PERS_ID);
		skillsDB.close();

		// ////////////////////// PERSONALIEN
		// Die Personalien laden und als Text an das htmlPersonalien übergeben.

		if (personalienArray.size() > 0) {

			PersonalienData personalien = personalienArray.get(0);
			String anrede = personalien.getAnrede();
			String name = personalien.getName();
			String vorname = personalien.getVorname();
			String strasse = personalien.getStrasse();
			String plz = personalien.getPlz();
			String ort = personalien.getOrt();
			String date = personalien.getDate();
			// fotoName = personalien.getBild();

			// Übergibt die Daten als Text an ein Spanned.
			txtPersonalien = Html.fromHtml(anrede + "<br />" + vorname + " "
					+ name + "<br />" + strasse + "<br />" + plz + " " + ort
					+ "<br />" + date);
			// Spanned www = Html.fromHtml("TEXT");
			// CharSequence xxx = TextUtils.concat(txtPersonalien, www);
			// Übergibt den Spanned an den TextView.
			personalienText = txtPersonalien.toString();
		}
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

		berufserfahrung = textBerufserfahrung.toString();

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

		bildung = textBildung.toString();

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

		skills = textSkills.toString();

	}

	// Methode zum erstellen eines PDFs
	@SuppressLint("SimpleDateFormat")
	public void createPdf() {

		// Definition der verschiedenen Schriftarten
		titelF = new Font(schrift, 24, Font.BOLD);
		ueberschriftF = new Font(schrift, 20, Font.BOLD);
		standardF = new Font(schrift, groesse, Font.NORMAL);

		try {
			Document document = new Document();
			// FileOutputStream fos = new FileOutputStream(dir+"/test.pdf");
			// Erstellung eines Zeitstempels für die PDF-Bezeichnung
			DateFormat formatDate = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
			Date date = new Date();
			String datum = formatDate.format(date).toString();
			// Pfad und Bezeichnung des PDFs(MUSS NOCH ANGEPASST WERDEN)
			String pfad = FileConst.getPdfPath() + "/Lebenslauf" + datum
					+ ".pdf";
			sendItem.setPath(pfad);
			FileOutputStream fos = new FileOutputStream(pfad);
			// Übergabe des Dokument und Pfad an den PdfWriter
			PdfWriter.getInstance(document, fos);
			document.open();
			// Methode zum aufrufen des Titels wird aufgerufen
			addTitel(document);

			// Bild hinzufügen
			
			document.add(addImage());
			

			// Methode zum hinzufügen des Textes wird aufgerufen
			addText(document);
			document.close();
			//
			btnSenden.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Methode zum hinzufügen des Titels
	private static void addTitel(Document document) {
		Paragraph preface = new Paragraph();
		// Titel
		preface.add(new Paragraph(titelU, titelF));
		addEmptyLine(preface, 3);
		try {
			document.add(preface);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Methode zum hinufügen des Textes
	private static void addText(Document document)

	throws DocumentException {
		Paragraph preface = new Paragraph();
		// Personalien
		preface.add(new Paragraph(personalienU, ueberschriftF));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph(personalienText, standardF));
		addEmptyLine(preface, 2);
		// Berufserfahrung
		preface.add(new Paragraph(berufserfahrungU, ueberschriftF));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph(berufserfahrung, standardF));
		addEmptyLine(preface, 2);
		// Bildung
		preface.add(new Paragraph(bildungU, ueberschriftF));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph(bildung, standardF));
		addEmptyLine(preface, 2);
		// Skills
		preface.add(new Paragraph(skillsU, ueberschriftF));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph(skills, standardF));
		addEmptyLine(preface, 2);
		document.add(preface);

	}

	// Methode für eine leere Zeile(Absatz)
	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public void onClick(View arg0) {
		// Zugriff auf die Preferenzen via der FinishActivity
		prefs = PreferenceManager.getDefaultSharedPreferences(finishActivity);
		// Methode zum auslesen der Preferenzen wird ausgeführt
		checkPreferences();
		// Methode zum erstellen eines PDFs wird ausgeführt
		createPdf();
		// Toast dass ein PDF erstellt wurde und mit Angaben über Sprache,
		// Schriftgrösse und Schriftart
		Toast toast = Toast.makeText(finishActivity, toastText + toastEnglish
				+ ", " + toastSize + ", " + toastArt + ")", Toast.LENGTH_LONG);
		toast.show();

	}

	// Methode die die Preferenzen ausliest
	public void checkPreferences() {
		// Die drei CheckBoxen werden geprüft(true or false)
		boolean englisch = prefs.getBoolean("sprache", true);
		boolean gross = prefs.getBoolean("schriftGr", true);
		boolean art = prefs.getBoolean("schriftArt", true);

		// Prüfen CheckBox Englisch oder Deutsch
		if (englisch) {
			toastEnglish = "Englisch";
			titelU = "Curriculum Vitae";
			personalienU = "Personal Data";
			bildungU = "Education";
			berufserfahrungU = "Professional Experience";
		} else {
			toastEnglish = "Deutsch";
			titelU = "Lebenslauf";
			personalienU = "Personalien";
			bildungU = "Bildung";
			berufserfahrungU = "Berufserfahrung";
			skillsU = "Skills";
		}
		// Prüfen CheckBox grosse oder kleine Schrift
		if (gross) {
			toastSize = "gross";
			groesse = 16;
		} else {
			toastSize = "klein";
			groesse = 11;
		}

		// Prüfen CheckBox Helvetica oder Times_Roman Schrift
		if (art) {
			toastArt = "Helvetica";
			schrift = Font.FontFamily.HELVETICA;

		} else {
			toastArt = "TimesRoman";
			schrift = Font.FontFamily.TIMES_ROMAN;

		}
	}

	public Long getPersID() {
		return persID;
	}

	private static Image addImage() throws BadElementException,
			MalformedURLException, IOException {

		String filePath = FileConst.getPdfPath() + "/" + persID.toString()
				+ "Foto.jpg";

		bitmap = BitmapFactory.decodeFile(filePath);
		bitmapResized = Bitmap.createScaledBitmap(bitmap, 190, 250, false);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmapResized.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		image = Image.getInstance(byteArray);
		return image;

	}

}
