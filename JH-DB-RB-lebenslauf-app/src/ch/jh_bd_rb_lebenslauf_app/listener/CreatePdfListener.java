package ch.jh_bd_rb_lebenslauf_app.listener;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class CreatePdfListener implements OnClickListener {

	static String personalien = "Hier kommen die Personalien hin";
	static String bildung = "Hier kommt die Bildung hin";
	static String berufserfahrung = "Hier kommt die Berufserfahrung hin";
	static String skills = "Hier kommen die Skills hin";
	String dir;

	public CreatePdfListener(String dir) {
		this.dir = dir;
	}

	public CreatePdfListener(String personalien, String bildung,
			String berufserfahrung, String skills) {

		this.personalien = personalien;
		this.bildung = bildung;
		this.berufserfahrung = berufserfahrung;
		this.skills = skills;

	}

	// private static String FILE = "sdcard/test.pdf";
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
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
		// We add one empty line
		addEmptyLine(preface, 1);
		// Lets write a big header
		preface.add(new Paragraph(personalien, catFont));

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

	}
}
