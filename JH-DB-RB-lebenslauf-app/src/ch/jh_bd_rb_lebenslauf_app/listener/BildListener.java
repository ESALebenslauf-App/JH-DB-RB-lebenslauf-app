package ch.jh_bd_rb_lebenslauf_app.listener;

import ch.jh_bd_rb_lebenslauf_app.gui.Bild;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class BildListener implements OnClickListener {
	private Activity bildActivity;

	public BildListener(Bild bild) {
		this.bildActivity = bild;
	}

	@Override
	public void onClick(View arg0) {
		shortToast("Neues Bild erstellen");

	}
	
	/**
	 * 
	 * @param text
	 */
	private void shortToast(String text) {
		Context context = bildActivity;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	
	private long persid;
	private String anrede;
	private String name;
	private String vorname;
	private String strasse;
	private int plz;
	private String ort;
	private String geb;
	private String bild;
	
	
	public long getPersId(){return persid;}
	public void setPersId(long persid){
		this.persid = persid;
	}
	
	
	public String getAnrede() {return anrede;}
	public void setAnrede(String anrede){
		this.anrede = anrede;
	}
	
	
	public String getName() {return name;}
	public void setName(String anrede){
		this.anrede = anrede;
	}
	
	
	
	public String getVorname() {return vorname;}
	public void setVornme(String vorname){
		this.vorname = vorname;
	}



	public String getStrasse() {return strasse;}
	public void setStrasse(String strasse){
		this.strasse = strasse;
	}
	
	
	public int getPLZ() {return plz;}
	public void setPlz(int plz){
		this.plz = plz;
	}
	
	
	public String getort() {return ort;}
	public void setort(String ort){
		this.ort = ort;
	}
	
	
	public String getGeb() {return geb;}
	public void setGeb(String geb){
		this.geb = geb;
	}
	
	
	
	public String getBild() {return bild;}
	public void setBild(String bild){
		this.bild = bild;
	}
	
	
}
