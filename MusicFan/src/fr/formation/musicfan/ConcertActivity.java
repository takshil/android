package fr.formation.musicfan;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import fr.formation.musicfan.bo.Concert;
import fr.formation.musicfan.dao.ConcertDataSource;

public class ConcertActivity extends Activity {

	ArrayAdapter<Concert> listAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_concert);
		
		
		List<Concert> listConcerts = getConcerts();
		listAdapter = new ArrayAdapter<Concert>(this,
				R.layout.concert);
		listAdapter.addAll(listConcerts);

		Button boutonAdd = (Button)findViewById(R.id.boutonAdd);
		final TextView tvVille = (TextView)findViewById(R.id.editVille);
		final TextView tvLieu = (TextView)findViewById(R.id.editLieu);
		final TextView tvDate = (TextView)findViewById(R.id.editDate);
		ListView listViewConcert = (ListView)findViewById(R.id.listConcert);
		listViewConcert.setAdapter(listAdapter);
		boutonAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String ville = tvVille.getText().toString();
				String lieu = tvLieu.getText().toString();
				String date = tvDate.getText().toString();
				
				ConcertDataSource concertDataSource = new ConcertDataSource(ConcertActivity.this);
				concertDataSource.open();
				long id = concertDataSource.insert(ville, lieu, date);
				Concert concert = concertDataSource.get(id);
				concertDataSource.close();
				listAdapter.add(concert);
				
				
				Intent intent = new Intent();
				intent.setAction("fr.formation.concertreceiver.NEW");
				intent.addCategory("fr.formation.concertreceveir.CONCERT");
				intent.putExtra("Concert", "Nouveau concert à " + ville + "(" + lieu+") le " + date);
				sendBroadcast(intent);
			}
		});
	}

	private List<Concert> getConcerts() {
		ConcertDataSource concertDataSource = new ConcertDataSource(ConcertActivity.this);
		concertDataSource.open();
		List<Concert> listConcerts = concertDataSource.getAll();
		concertDataSource.close();
		return listConcerts;
	}
	
	


}
