package ch.jh_bd_rb_lebenslauf_app.daten;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
	
	public DbHelper(Context context) {
		super(context, PersonalienTable.DB_NAME, null, PersonalienTable.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// erzeugt das DB-Schema
		// hier koennten bereits initiale daten abgefuellt werden
		db.execSQL(PersonalienTable.SQL_DB_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// transform db from old to new version
		// ...
	}
}