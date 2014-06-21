package ch.jh_bd_rb_lebenslauf_app.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienDB;
import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienData;
import ch.jh_bd_rb_lebenslauf_app.resource.FileConst;
import ch.jh_bd_rb_lebenslauf_app.resource.StringConst;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListPersonenActivity extends ListActivity {
	private int listID;
	private ArrayList<PersonalienData> listData;
	private ArrayAdapter<String> mPdfAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_personen);

		listData = new ArrayList<PersonalienData>();

		PersonalienDB db = new PersonalienDB(this);
		db.open();
		listData = db.getAllPersonalien();
		db.close();

		setListAdapter();

	}



	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		listID = (int) id;
		
		super.onListItemClick(l, v, position, id);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				ListPersonenActivity.this);

		alertDialogBuilder.setTitle(this.getTitle() + "Personen Daten");
		alertDialogBuilder.setMessage("Bitte wählen Sie eine Option aus.");

		alertDialogBuilder.setPositiveButton("Übernhemen",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						
						final Intent intent = new Intent(ListPersonenActivity.this, BildActivity.class);
						intent.putExtra(StringConst.getPesrid(), listData.get(listID).getID());

						startActivity(intent);
						
						
						Toast.makeText(getApplicationContext(),
								"Übernhemen",
								Toast.LENGTH_LONG).show();
					}
				});

		alertDialogBuilder.setNegativeButton("Löschen",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int idDialog) {

						PersonalienData pers = listData.get(listID);
						PersonalienDB persDB = new PersonalienDB(ListPersonenActivity.this);
						persDB.open();
						persDB.deletePersonalien(pers);
						
						setListAdapter();
						Toast.makeText(getApplicationContext(),
								"Löschen ID: "+ listID,
								Toast.LENGTH_LONG).show();

						mPdfAdapter.notifyDataSetChanged();
					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	
	private void setListAdapter() {
		ArrayList<String> namen = new ArrayList<String>();

		for (int i = 0; i < listData.size(); i++) {
			namen.add(listData.get(i).getName() + " "
					+ listData.get(i).getVorname());
		}
		mPdfAdapter = new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1, namen);
		setListAdapter(mPdfAdapter);

	}

}
