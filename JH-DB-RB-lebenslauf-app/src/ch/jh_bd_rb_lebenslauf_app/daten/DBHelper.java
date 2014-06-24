package ch.jh_bd_rb_lebenslauf_app.daten;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * @author bdervishi.jherzig.rbuess
 * 
 */

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, LebenslaufDB.DB_NAME, null, LebenslaufDB.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// erzeugt das DB-Schema
		db.execSQL(LebenslaufDB.SQL_CREATE_TABLE_PERS);
		db.execSQL(LebenslaufDB.SQL_CREATE_TABLE_BERUF);
		db.execSQL(LebenslaufDB.SQL_CREATE_TABLE_BILDUNG);
		db.execSQL(LebenslaufDB.SQL_CREATE_TABLE_SKILLS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Die Methode onUpgrade wurde in der ersten version noch nicht implemtiert.		
	}

}