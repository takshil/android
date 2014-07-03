package fr.formation.musicfan2;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		List<Concert> listConcerts = getConcerts();
		ArrayAdapter<Concert> listAdapter = new ArrayAdapter<Concert>(this,
				R.layout.concert);
		listAdapter.addAll(listConcerts);
		ListView listViewConcert = (ListView)findViewById(R.id.listView1);
		listViewConcert.setAdapter(listAdapter);
	}

	private List<Concert> getConcerts() {
		List<Concert> listConcerts = new ArrayList<Concert>();
		ContentResolver c = getContentResolver();
		Cursor cursor = c.query(Uri.parse("content://" +ConcertContracts.URI + "/" + ConcertContracts.BASE_PATH), null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Concert concert = cursorToConcert(cursor);
			listConcerts.add(concert);
			cursor.moveToNext();
		}
		
		return listConcerts;
	}

	private Concert cursorToConcert(Cursor cursor) {
		Concert concert = new Concert();
		concert.setId(cursor.getInt(0));
		concert.setVille(cursor.getString(1));
		concert.setLieu(cursor.getString(2));
		concert.setDate(cursor.getString(3));
		return concert;
	}
}
