package ch.jh_bd_rb_lebenslauf_app.daten;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;

/**
 * 
 * @author rbuess
 * 
 */

public class Zertifikat {
	private String path;
	private String name;
	private Bitmap image;
	private byte[] byteArrayImage;

	public void setByteArrayImage(byte[] byteArrayImage) {
		this.byteArrayImage = byteArrayImage;
	}

	public byte[] getByteArrayImage() {
		return byteArrayImage;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		getImage().compress(Bitmap.CompressFormat.JPEG, 100, stream);
		setByteArrayImage(stream.toByteArray());
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	//TODO Name hinzufègen
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
