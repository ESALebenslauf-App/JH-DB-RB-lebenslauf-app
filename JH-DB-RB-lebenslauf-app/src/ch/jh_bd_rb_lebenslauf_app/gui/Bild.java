package ch.jh_bd_rb_lebenslauf_app.gui;

import ch.jh_bd_rb_lebenslauf_app.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Bild extends Activity {

	static final String NAME = "name";
	static final String ADRESSE = "adresse";
	
	ImageView iv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bild);
		
		/*
		iv = (ImageView) findViewById(R.id.kamAuswahl);
		Button btn = (Button) findViewById(R.id.kamAuswahl);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, 0);
			}
		});*/

	}


	public void onClickBerufserfahrung(View Button) {
		final Intent intent = new Intent(this, Berufserfahrung.class);
		
		
		intent.putExtra(NAME, "Name");
		intent.putExtra(ADRESSE, "Adresse");
		startActivity(intent);

	}

	public void onClickStart(View Button) {
		final Intent intent = new Intent(this, StartActivity.class);
		startActivity(intent);
	}
	
	public void onClickCamera(View Button) {
		shortToast("onClickCamera(View Button)");

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == 0){
			Bitmap theImage = (Bitmap) data.getExtras().get("data");
			
		}
	}	

	public void onClickHochladen(View Button) {
		shortToast("onClickHochladen(View Button)");
	}	
	
	private void shortToast(String text) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
