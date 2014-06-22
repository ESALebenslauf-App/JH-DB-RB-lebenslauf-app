package ch.jh_bd_rb_lebenslauf_app.gui;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.LebenslaufDB;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsDB;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsData;
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

public class ListSkillsActivity extends ListActivity {
	private int listID;
	private ArrayList<SkillsData> listData;
	private ArrayAdapter<String> mPdfAdapter;
	private Long persID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_skills);
		setPersID(getIntent().getLongExtra(StringConst.PERSID, 0));
		SkillsData skills = new SkillsData();
		skills.setPers_id(getPersID());

		listData = new ArrayList<SkillsData>();

		SkillsDB db = new SkillsDB(this);
		db.open();
		listData = db.getSkillsRows(skills, LebenslaufDB.SKILLS_PERS_ID);
		db.close();

		setListAdapter();
	}

	private void setListAdapter() {
		ArrayList<String> namen = new ArrayList<String>();

		for (int i = 0; i < listData.size(); i++) {
			namen.add(listData.get(i).getWas() + " " + listData.get(i).getAusmass());
		}
		mPdfAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, namen);
		setListAdapter(mPdfAdapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		listID = (int) id;

		super.onListItemClick(l, v, position, id);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListSkillsActivity.this);

		alertDialogBuilder.setTitle(this.getTitle() + StringConst.SKILLSDATEN);
		alertDialogBuilder.setMessage(StringConst.DIALOGOPTIONWAELEN);

		alertDialogBuilder.setPositiveButton(StringConst.UEBRNEHMEN, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				final Intent intent = new Intent(ListSkillsActivity.this, SkillsActivity.class);
				intent.putExtra(StringConst.PERSID, getPersID());
				intent.putExtra(StringConst.ID, listData.get(listID).getID());

				startActivity(intent);
			}
		});

		alertDialogBuilder.setNegativeButton(StringConst.LOESCHEN, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int idDialog) {

				SkillsData skill = listData.get(listID);
				SkillsDB db = new SkillsDB(ListSkillsActivity.this);
				db.open();
				db.deleteSkills(skill);
				db.close();

				String toast = listData.get(listID).getWas() + StringConst.DATEN_WURDEN_GELOESCHT;
				Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();

				final Intent intent = new Intent(ListSkillsActivity.this, SkillsActivity.class);
				intent.putExtra(StringConst.PERSID, getPersID());

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
