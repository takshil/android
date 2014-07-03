package fr.formation.musicfan.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ConcertSQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_CONCERT = "concert";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_VILLE = "ville";
	public static final String COLUMN_LIEU = "lieu";
	public static final String COLUMN_DATE = "mydate";
	  
	private static final String DATABASE_NAME = "baseconcert.db";
	private static final int DATABASE_VERSION = 3;
	
	// Le script SQL pour créer les tables de la base de données
		  private static final String DATABASE_CREATE = "create table "
		      + TABLE_CONCERT + "(" + COLUMN_ID
		      + " integer primary key autoincrement, " + COLUMN_VILLE
		      + " text not null, " + COLUMN_LIEU + " text not null, "
		      + COLUMN_DATE+" integer);";
	
	public ConcertSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(ConcertSQLiteHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONCERT);
		    onCreate(db);
	}

}
