package fr.formation.musicfan.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import fr.formation.musicfan.dao.ConcertDataSource;

public class ConcertProvider extends ContentProvider{

	private ConcertDataSource dao;
	
	/*
	 * Utilisé par le uri matcher qui va analyser et decouper l'uri
	 */
	private static final int CONCERTS = 10;
	private static final int CONCERT = 20;
	
	// le uri matcher avec les uri supportées
	private static final UriMatcher sURIMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
	static {
		// adresse pour les opération sans besoin d'id
		sURIMatcher.addURI(ConcertContracts.URI, ConcertContracts.BASE_PATH, CONCERTS);
		// adresse pour les opération sur un seul élement
		sURIMatcher.addURI(ConcertContracts.URI, ConcertContracts.BASE_PATH + "/#", CONCERT);
	}

	@Override
	public int delete(Uri uri, String arg1, String[] arg2) {
		int uriType = sURIMatcher.match(uri);
	    int rowsDeleted = 0;
	    switch (uriType) {
	    case CONCERT:
	      String id = uri.getLastPathSegment();
	      rowsDeleted = dao.delete(Long.parseLong(id));
	      break;
	    default:
	      throw new IllegalArgumentException("Unknown URI: " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);

	    return rowsDeleted;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sURIMatcher.match(uri);
	    long id = 0;
	    switch (uriType) {
	    case CONCERTS:
	      id = dao.insert(values.getAsString("ville"), values.getAsString("lieu"), values.getAsString("date"));
	      break;
	    default:
	      throw new IllegalArgumentException("Unknown URI: " + uri);
	    }
		// ne pas oublier de notifier les listener potentiels
	    getContext().getContentResolver().notifyChange(uri, null);
		// pour l'insertion, on renvoi le nouvel id
	    return Uri.parse(ConcertContracts.BASE_PATH + "/" + id);
	}

	@Override
	public boolean onCreate() {
		dao = new ConcertDataSource(getContext());
		dao.open();
		return true;
	}

	
	@Override
	public Cursor query(Uri uri, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		// le resultat
				Cursor cursor = null;
				
				// verifie l'action demandée
				int type = sURIMatcher.match(uri);
				// on peut récupérer l'ensemble des noms ou un en particulier
				// le resultat est obtenu par le dao
				switch (type){
				case CONCERT :
					String concertId = uri.getLastPathSegment();
					cursor = dao.getCursor(Long.parseLong(concertId));
					break;
				case CONCERTS :
					cursor = dao.getAllCursor();
					break;
				default:
				      throw new IllegalArgumentException("Unknown URI: " + uri);
				}
				// ne pas oublier de notifier les listener potentiels
				cursor.setNotificationUri(getContext().getContentResolver(), uri);
				return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues arg1, String arg2, String[] arg3) {
		// pas de mise à jour possible dans notre systele
		throw new IllegalArgumentException("Unknown URI: " + uri);
	}

}
