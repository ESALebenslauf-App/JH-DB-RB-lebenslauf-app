package ch.jh_bd_rb_lebenslauf_app.daten;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	
	public DBHelper(Context context) {
		super(context, LebenslaufDB.DB_NAME, null, LebenslaufDB.DB_VERSION);
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// erzeugt das DB-Schema
		// hier koennten bereits initiale daten abgefuellt werden
		db.execSQL(LebenslaufDB.SQL_CREATE_TABLE_PERS);
		db.execSQL(LebenslaufDB.SQL_CREATE_TABLE_BERUF);
		db.execSQL(LebenslaufDB.SQL_CREATE_TABLE_BILDUNG);
		db.execSQL(LebenslaufDB.SQL_CREATE_TABLE_SKILLS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// transform db from old to new version
		Log.w(DBHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS SCANITEM");
	}
}