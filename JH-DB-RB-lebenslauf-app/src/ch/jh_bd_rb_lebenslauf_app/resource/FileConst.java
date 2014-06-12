package ch.jh_bd_rb_lebenslauf_app.resource;

import android.annotation.SuppressLint;

@SuppressLint("SdCardPath")
public class FileConst {
	
	private static final String  PDF_PATH = "/sdcard/android/obb";

	public static String getPdfPath() {
		return PDF_PATH;
	}

}
