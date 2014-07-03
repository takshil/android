package fr.formation.musicfan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class InfosActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_infos);
		MusicFanApp myApp = (MusicFanApp) InfosActivity.this.getApplication();
		
		InfosActivity.this.getActionBar().setDisplayHomeAsUpEnabled(true);
		TextView textName = (TextView) findViewById(R.id.textName);
		textName.setText(myApp.getName());
	}
}
