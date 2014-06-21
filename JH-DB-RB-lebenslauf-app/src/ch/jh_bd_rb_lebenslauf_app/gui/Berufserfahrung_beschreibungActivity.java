package ch.jh_bd_rb_lebenslauf_app.gui;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungData;
import ch.jh_bd_rb_lebenslauf_app.resource.StringConst;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Berufserfahrung_beschreibungActivity extends Activity {

	private long persID;
	private Long ID;
	private Button btnSave;
	private TextView txtBeschreibung;
	private String text;



	@Override
	protected void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.activity_berufserfahrung_beschreibung);
		this.persID = getIntent().getLongExtra(StringConst.getPesrid(), 0);
		setText(getIntent().getStringExtra(StringConst.BESCHREIBUNG));
		setID(getIntent().getLongExtra(StringConst.ID, 0));

		initActivityElemente();
		initActivityListener();
		loadData();
	}
	
	private void loadData() {
		BerufserfahrungData beruferfahrung = new BerufserfahrungData(
				getID());
		if (getID() > 0) {
			BerufserfahrungDB db = new BerufserfahrungDB(this);
			db.open();
			beruferfahrung = db.getBerufserfahrung(beruferfahrung);

			getTxtBeschreibung().setText(beruferfahrung.getTxt_beschreibung());
		}
	}

	private void initActivityListener() {
		btnSave = (Button) findViewById(R.id.btn_berufserfahrung_beschreibung_save);
		
		
		btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setText(getTxtBeschreibung().getText().toString()); 
				
				final Intent intent = new Intent(Berufserfahrung_beschreibungActivity.this, BerufserfahrungActivity.class);

				intent.putExtra(StringConst.getPesrid(), persID);
				intent.putExtra(StringConst.BESCHREIBUNG, getText() );
				intent.putExtra(StringConst.ID, getID());
				startActivity(intent);
			}
		});
	}

	private void initActivityElemente() {
		txtBeschreibung = (TextView) findViewById(R.id.txt_Edit_berufserfahrung_beschreibung);
		getTxtBeschreibung().setText(getText());
	}
	public TextView getTxtBeschreibung() {
		return txtBeschreibung;
	}

	public void setTxtBeschreibung(TextView txtBeschreibung) {
		this.txtBeschreibung = txtBeschreibung;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	
	private Long getID() {
		return ID;
	}

	private void setID(Long iD) {
		ID = iD;
	}
	
}
