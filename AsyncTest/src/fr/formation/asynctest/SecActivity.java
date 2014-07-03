package fr.formation.asynctest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class SecActivity extends MyActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		progress = (ProgressBar) findViewById(R.id.progressBar1);
		
		new MyAsyncTask(SecActivity.this).execute();

	}

	@Override
	public void demarrerActivity() {
		Intent intent = new Intent(SecActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
