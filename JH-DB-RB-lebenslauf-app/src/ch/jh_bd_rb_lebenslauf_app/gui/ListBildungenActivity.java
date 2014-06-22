package ch.jh_bd_rb_lebenslauf_app.gui;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungData;
import ch.jh_bd_rb_lebenslauf_app.daten.LebenslaufDB;
import ch.jh_bd_rb_lebenslauf_app.resource.StringConst;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author j.herzig
 * 
 */

public class ListBildungenActivity extends ListActivity {
	private int listID;
	private ArrayList<BildungData> listData;
	private ArrayAdapter<String> mPdfAdapter;
	private Long persID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_bildung);
		setPersID(getIntent().getLongExtra(StringConst.getPesrid(), 0));
		BildungData bildung = new BildungData();
		bildung.setPersID(getPersID());

		listData = new ArrayList<BildungData>();
		
		BildungDB db = new BildungDB(this);
		db.open();
		listData = db.getBildungRows(bildung, LebenslaufDB.BILDUNG_PERS_ID);
		db.close();

		setListAdapter();
	}

	private void setListAdapter() {
		ArrayList<String> namen = new ArrayList<String>();

		for (int i = 0; i < listData.size(); i++) {
			namen.add(listData.get(i).getNameschule() + " " + listData.get(i).getAdresseSchule());
		}
		mPdfAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, namen);
		setListAdapter(mPdfAdapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		listID = (int) id;

		super.onListItemClick(l, v, position, id);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListBildungenActivity.this);

		alertDialogBuilder.setTitle(this.getTitle() + StringConst.BILDUNGDATEN);
		alertDialogBuilder.setMessage(StringConst.DIALOGOPTIONWAELEN);

		alertDialogBuilder.setPositiveButton(StringConst.UEBRNEHMEN, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				final Intent intent = new Intent(ListBildungenActivity.this, BildungActivity.class);
				intent.putExtra(StringConst.getPesrid(), getPersID());
				intent.putExtra(StringConst.ID, listData.get(listID).getID());

				startActivity(intent);
			}
		});

		alertDialogBuilder.setNegativeButton(StringConst.LOESCHEN, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int idDialog) {

				BildungData bildung = listData.get(listID);
				BildungDB db = new BildungDB(ListBildungenActivity.this);
				db.open();
				db.deleteBildung(bildung);
				db.close();

				String toast = listData.get(listID).getNameschule() + StringConst.DATEN_WURDEN_GELOESCHT;
				Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();

				final Intent intent = new Intent(ListBildungenActivity.this, BerufserfahrungActivity.class);
				intent.putExtra(StringConst.getPesrid(), getPersID());

				startActivity(intent);
			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	private Long getPersID() {
		return persID;
	}

	private void setPersID(Long persID) {
		this.persID = persID;
	}

}
