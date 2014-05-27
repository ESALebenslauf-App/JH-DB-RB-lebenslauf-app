package ch.jh_bd_rb_lebenslauf_app.listener;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import android.app.Activity;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class CreatePdfListener implements OnClickListener {

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
	String toastText = "PDF erstellt";

	public CreatePdfListener(Activity finishActivity, String dir) {
		this.dir = dir;
		this.finishActivity = finishActivity;
	}

	public CreatePdfListener(String personalien, String bildung,
			String berufserfahrung, String skills) {

		this.personalien = personalien;
		this.bildung = bildung;
		this.berufserfahrung = berufserfahrung;
		this.skills = skills;

	}

	// private static String FILE = "sdcard/test.pdf";
	private static Font titelF = new Font(Font.FontFamily.HELVETICA, 22,
			Font.BOLD);
	private static Font ueberschriftF = new Font(Font.FontFamily.COURIER, 18,
			Font.BOLD);
	private static Font standardF = new Font(Font.FontFamily.TIMES_ROMAN, 14,
			Font.BOLD);

	public void createPdf() {

		try {
			Document document = new Document();
			// FileOutputStream fos = new FileOutputStream(dir+"/test.pdf");
			Log.e("MESAGE", dir);
			FileOutputStream fos = new FileOutputStream(
					"/sdcard/android/obb/Lebenslauf.pdf");
			PdfWriter.getInstance(document, fos);
			document.open();
			// addMetaData(document);
			addTitlePage(document);
			// addContent(document);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.e("MESSAGE", "PDF erzeugt");
	}

	private static void addTitlePage(Document document)

	throws DocumentException {
		Paragraph preface = new Paragraph();
		//Titel
		preface.add(new Paragraph(titelU, titelF));
		addEmptyLine(preface, 3);
		//Personalien
		preface.add(new Paragraph(personalienU, ueberschriftF));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph(personalien, standardF));
		addEmptyLine(preface, 2);
		//Bildung
		preface.add(new Paragraph(bildungU, ueberschriftF));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph(bildung, standardF));
		addEmptyLine(preface, 2);
		//Berufserfahrung
		preface.add(new Paragraph(berufserfahrungU, ueberschriftF));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph(berufserfahrung, standardF));
		addEmptyLine(preface, 2);
		//Skills
		preface.add(new Paragraph(skillsU, ueberschriftF));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph(skills, standardF));
		addEmptyLine(preface, 2);
		document.add(preface);

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		createPdf();
		Toast toast = Toast.makeText(finishActivity, toastText, Toast.LENGTH_LONG);
		toast.show();

	}
}
