package ch.jh_bd_rb_lebenslauf_app.gui;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungData;
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

public class ListBerufserfahrungenActivity extends ListActivity {
	private int listID;
	private ArrayList<BerufserfahrungData> listData;
	private ArrayAdapter<String> mPdfAdapter;
	private Long persID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_berufserfhrungen);
		setPersID(getIntent().getLongExtra(StringConst.getPesrid(), 0));
		BerufserfahrungData berufserfahrungdata = new BerufserfahrungData();
		berufserfahrungdata.setPersID(getPersID());

		listData = new ArrayList<BerufserfahrungData>();

		BerufserfahrungDB db = new BerufserfahrungDB(this);
		db.open();
		listData = db.getBerufserfarungRows(berufserfahrungdata, LebenslaufDB.BERUF_PERS_ID);
		db.close();

		setListAdapter();
	}

	private void setListAdapter() {
		ArrayList<String> namen = new ArrayList<String>();

		for (int i = 0; i < listData.size(); i++) {
			namen.add(listData.get(i).getTxt_firma() + " " + listData.get(i).getTxt_ort());
		}
		mPdfAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, namen);
		setListAdapter(mPdfAdapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		listID = (int) id;

		super.onListItemClick(l, v, position, id);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListBerufserfahrungenActivity.this);

		alertDialogBuilder.setTitle(this.getTitle() + StringConst.BERUFSERFARUNGDATEN);
		alertDialogBuilder.setMessage(StringConst.DIALOGOPTIONWAELEN);

		alertDialogBuilder.setPositiveButton(StringConst.UEBRNEHMEN, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				final Intent intent = new Intent(ListBerufserfahrungenActivity.this, BerufserfahrungActivity.class);
				intent.putExtra(StringConst.getPesrid(), getPersID());
				intent.putExtra(StringConst.ID, listData.get(listID).getID());

				startActivity(intent);
			}
		});

		alertDialogBuilder.setNegativeButton(StringConst.LOESCHEN, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int idDialog) {

				BerufserfahrungData berfserfahrung = listData.get(listID);
				BerufserfahrungDB db = new BerufserfahrungDB(ListBerufserfahrungenActivity.this);
				db.open();
				db.deleteBerufserfahrung(berfserfahrung);

				String toast = listData.get(listID).getTxt_firma() + StringConst.DATEN_WURDEN_GELOESCHT;
				Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();

				final Intent intent = new Intent(ListBerufserfahrungenActivity.this, BerufserfahrungActivity.class);
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
