package fr.formation.musicfan.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import fr.formation.musicfan.bo.Concert;

public class ConcertDataSource {

	private ConcertSQLiteHelper dbHelper;
	private SQLiteDatabase database;
	
	private String[] allColumns = { ConcertSQLiteHelper.COLUMN_ID,
			ConcertSQLiteHelper.COLUMN_VILLE, ConcertSQLiteHelper.COLUMN_LIEU, 
			ConcertSQLiteHelper.COLUMN_DATE };

	public ConcertDataSource(Context context) {
		// on associe un lien vers la base de données
		dbHelper = new ConcertSQLiteHelper(context);
	}

	public void open() throws SQLException {
		// ouverture d'une connexion avec la bd
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	/**
	 * Crée une nouvelle personne et la renvoi
	 */
	public Concert create(String ville, String lieu, String date) {
		long insertId = insert(ville, lieu, date);
		
		// on peut maintenant récupérer le nouvel objet inséré
		return get(insertId);
	}

	public Concert get(long insertId) {
		// Un Cursor est un objet contenant le resultat d'une requete
		Cursor cursor = getCursor(insertId);
		cursor.moveToFirst();
		Concert concert = cursorToConcert(cursor);
		cursor.close();
		return concert;
	}

	public List<Concert> getAll() {
		List<Concert> comments = new ArrayList<Concert>();
		Cursor cursor = getAllCursor();

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Concert comment = cursorToConcert(cursor);
			comments.add(comment);
			cursor.moveToNext();
		}
		// Ne pas oublier de fermer le curseur
		cursor.close();
		return comments;
	}

	public Cursor getAllCursor() {
		Cursor cursor = database.query(ConcertSQLiteHelper.TABLE_CONCERT,
				allColumns, null, null, null, null, null);
		return cursor;
	}
	
	public Concert cursorToConcert(Cursor cursor) {
		Concert concert = new Concert();
		concert.setId(cursor.getInt(0));
		concert.setVille(cursor.getString(1));
		concert.setLieu(cursor.getString(2));
		concert.setDate(cursor.getString(3));
		return concert;
	}

	public Cursor getCursor(long insertId) {
		Cursor cursor = database.query(ConcertSQLiteHelper.TABLE_CONCERT,
				allColumns, ConcertSQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		return cursor;
	}

	public long insert(String ville, String lieu, String date) {
		ContentValues values = new ContentValues();
		values.put(ConcertSQLiteHelper.COLUMN_VILLE, ville);
		values.put(ConcertSQLiteHelper.COLUMN_LIEU, lieu);
		values.put(ConcertSQLiteHelper.COLUMN_DATE, date);

		return database.insert(ConcertSQLiteHelper.TABLE_CONCERT, null, values);
	}
	
	/**
	 * Supprime une personne de la base
	 * @param personne
	 * @return le nombre de lignes supprimées
	 */
	public int delete(Concert concert) {
		long id = concert.getId();
		return delete(id);
	}
	public int delete(long id) {
		return database.delete(ConcertSQLiteHelper.TABLE_CONCERT, ConcertSQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}
	
	/**
	 * Met à jour la base avec la personne en paramêtres
	 * @param personne
	 */
	public void update(Concert concert) {
		// même principe de méthode que pour l'insertion et la récupération de valeur
		ContentValues values = new ContentValues();
		values.put(ConcertSQLiteHelper.COLUMN_VILLE, concert.getVille());
		values.put(ConcertSQLiteHelper.COLUMN_LIEU, concert.getLieu());
		database.update(ConcertSQLiteHelper.TABLE_CONCERT, values, 
				ConcertSQLiteHelper.COLUMN_ID + " = " + concert.getId(), null);
	}
}
