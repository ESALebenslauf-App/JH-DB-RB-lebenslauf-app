package ch.jh_bd_rb_lebenslauf_app.gui;

import java.sql.Date;
import java.text.SimpleDateFormat;

import ch.jh_bd_rb_lebenslauf_app.daten.LebenslaufDB;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ZusammenfassungSQL extends Activity {
	private LebenslaufDB db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = new LebenslaufDB(this);
		db.open();

		final Cursor cursor = db.getAllCursor();
		int[] viewIds = new int[] { R.id.bar, R.id.foo, R.id.date };

		// FIXME: anstatt SimpleCursorAdapter verwende besser einen CursorLoader (bessere Performance)
		setListAdapter(new SimpleCursorAdapter(this,
				R.layout.note_listentry_layout, cursor, Notes.PROJECTION_SHORT, viewIds, 0) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				cursor.moveToPosition(position);
				long date = cursor.getLong(cursor.getColumnIndex(Notes.FIELD_DATE));
				String dateStr = SimpleDateFormat.getDateTimeInstance().format(new Date(date));
				
				View myView = super.getView(position, convertView, parent);
				TextView tv = (TextView) myView.findViewById(R.id.date);
				tv.setText(dateStr);
				return myView;
			}
		});
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent(this, ShowNoteActivity.class);
		i.putExtra("noteId", id);
		startActivity(i);
	}

	protected void onResume() {
		super.onResume();
		db.open();
	};

	@Override
	protected void onPause() {
		super.onPause();
		db.close();
	}
}
