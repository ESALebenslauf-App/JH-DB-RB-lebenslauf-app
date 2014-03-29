package ch.jh_bd_rb_lebenslauf_app.gui;

import ch.jh_bd_rb_lebenslauf_app.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class Bild extends Activity {

	static final String NAME = "name";
	static final String ADRESSE = "adresse";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bild);
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
