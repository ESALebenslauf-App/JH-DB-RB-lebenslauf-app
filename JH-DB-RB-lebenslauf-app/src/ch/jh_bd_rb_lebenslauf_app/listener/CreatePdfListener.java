package ch.jh_bd_rb_lebenslauf_app.listener;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.SendItem;
import ch.jh_bd_rb_lebenslauf_app.resource.FileConst;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
	static String personalien = "Hier kommen die Personalien hin";
	static String bildung = "Hier kommt die Bildung hin";
	static String berufserfahrung = "Hier kommt die Berufserfahrung hin";
	static String skills = "Hier kommen die Skills hin";

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
	public CreatePdfListener(Activity finishActivity, String dir,SendItem sendItem) {
		this.dir = dir;
		this.finishActivity = finishActivity;
		this.sendItem = sendItem;
		btnSenden = (Button) finishActivity.findViewById(R.id.btnSenden);
	}



	/*
	 * //Konstruktor mit Lebenslaufdaten public CreatePdfListener(String
	 * personalien, String bildung, String berufserfahrung, String skills) {
	 * 
	 * this.personalien = personalien; this.bildung = bildung;
	 * this.berufserfahrung = berufserfahrung; this.skills = skills; }
	 */
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
			// Erstellung eines Zeitstempels f�r die PDF-Bezeichnung
			DateFormat formatDate = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
			Date date = new Date();
			String datum = formatDate.format(date).toString();
			// Pfad und Bezeichnung des PDFs(MUSS NOCH ANGEPASST WERDEN)
			String pfad = FileConst.getPdfPath() + "/Lebenslauf" + datum
					+ ".pdf";
			sendItem.setPath(pfad);
			FileOutputStream fos = new FileOutputStream(pfad);
			// �bergabe des Dokument und Pfad an den PdfWriter
			PdfWriter.getInstance(document, fos);
			document.open();
			// Methode zum hinzuf�gen des Textes wird aufgerufen
			addTitlePage(document);
			document.close();
			// 
			btnSenden.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Methode zum hinuf�gen des Textes
	private static void addTitlePage(Document document)

	throws DocumentException {
		Paragraph preface = new Paragraph();
		// Titel
		preface.add(new Paragraph(titelU, titelF));
		addEmptyLine(preface, 3);
		// Personalien
		preface.add(new Paragraph(personalienU, ueberschriftF));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph(personalien, standardF));
		addEmptyLine(preface, 2);
		// Bildung
		preface.add(new Paragraph(bildungU, ueberschriftF));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph(bildung, standardF));
		addEmptyLine(preface, 2);
		// Berufserfahrung
		preface.add(new Paragraph(berufserfahrungU, ueberschriftF));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph(berufserfahrung, standardF));
		addEmptyLine(preface, 2);
		// Skills
		preface.add(new Paragraph(skillsU, ueberschriftF));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph(skills, standardF));
		addEmptyLine(preface, 2);
		document.add(preface);

	}

	// Methode f�r eine leere Zeile(Absatz)
	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public void onClick(View arg0) {
		// Zugriff auf die Preferenzen via der FinishActivity
		prefs = PreferenceManager.getDefaultSharedPreferences(finishActivity);
		// Methode zum auslesen der Preferenzen wird ausgef�hrt
		checkPreferences();
		// Methode zum erstellen eines PDFs wird ausgef�hrt
		createPdf();
		// Toast dass ein PDF erstellt wurde und mit Angaben �ber Sprache,
		// Schriftgr�sse und Schriftart
		Toast toast = Toast.makeText(finishActivity, toastText + toastEnglish
				+ ", " + toastSize + ", " + toastArt + ")", Toast.LENGTH_LONG);
		toast.show();

	}

	// Methode die die Preferenzen ausliest
	public void checkPreferences() {
		// Die drei CheckBoxen werden gepr�ft(true or false)
		boolean englisch = prefs.getBoolean("sprache", true);
		boolean gross = prefs.getBoolean("schriftGr", true);
		boolean art = prefs.getBoolean("schriftArt", true);

		// Pr�fen CheckBox Englisch oder Deutsch
		if (englisch) {
			toastEnglish = "Englisch";
			titelU = "Curriculum Vitae";
			personalienU = "Personal Data";
			bildungU = "Education";
			berufserfahrungU = "professional experience";
		} else {
			toastEnglish = "Deutsch";
			titelU = "Lebenslauf";
			personalienU = "Personalien";
			bildungU = "Bildung";
			berufserfahrungU = "Berufserfahrung";
			skillsU = "Skills";
		}
		// Pr�fen CheckBox grosse oder kleine Schrift
		if (gross) {
			toastSize = "gross";
			groesse = 16;
		} else {
			toastSize = "klein";
			groesse = 11;
		}

		// Pr�fen CheckBox Helvetica oder Times_Roman Schrift
		if (art) {
			toastArt = "Helvetica";
			schrift = Font.FontFamily.HELVETICA;

		} else {
			toastArt = "TimesRoman";
			schrift = Font.FontFamily.TIMES_ROMAN;

		}
	}

}
