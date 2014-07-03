package fr.formation.asynctest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends MyActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		progress = (ProgressBar) findViewById(R.id.progressBar1);
		
		new MyAsyncTask(MainActivity.this).execute();

	}

	@Override
	public void demarrerActivity() {
		Intent intent = new Intent(MainActivity.this, SecActivity.class);
		startActivity(intent);
		finish();
	}
}
