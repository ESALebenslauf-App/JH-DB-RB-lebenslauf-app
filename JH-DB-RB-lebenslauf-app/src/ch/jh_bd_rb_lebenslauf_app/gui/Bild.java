package ch.jh_bd_rb_lebenslauf_app.gui;


import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.listener.BildListener;
import ch.jh_bd_rb_lebenslauf_app.listener.HochladenListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 *
 */
public class Bild extends Activity {


	private String anrede;
	private String name;
	private String vorname;
	private String strasse;
	private int plz;
	private String ort;
	private String geb;
	private String bild;
	private Button btnBerufserfahrung;
	private ImageButton btnCamera;
	private ImageButton btnHochladen;
	
	ImageView iv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bild);
		initActivityElemente();
		initActivityListener();
	}
	
	@Override
    protected void onResume() {
    	super.onResume();
    }


	private void initActivityElemente() {
		btnBerufserfahrung = (Button) findViewById(R.id.buttonBerufserfahrung);
		btnCamera = (ImageButton) findViewById(R.id.kamAuswahl);
		btnHochladen = (ImageButton) findViewById(R.id.picAuswahl);
		
	}

	private void initActivityListener() {
		btnBerufserfahrung.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View button) {
				clickBildBerufserfahrung(button);				
			}
		});
		
		btnCamera.setOnClickListener(new BildListener(this));
		btnHochladen.setOnClickListener(new HochladenListener(this));
		
	}



	/**
	 * 
	 * @param Button
	 */
	public void clickBildBerufserfahrung(View Button) {
		
		
		final Intent intent = new Intent(this, Berufserfahrung.class);
		
		
		intent.putExtra(name, "Name");
		intent.putExtra(strasse, "Strasse");
		startActivity(intent);

	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == 0){
			Bitmap theImage = (Bitmap) data.getExtras().get("data");
			
		}
	}	


}
