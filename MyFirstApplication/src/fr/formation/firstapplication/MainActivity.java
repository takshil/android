package fr.formation.firstapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		final Button button1 = (Button) findViewById(R.id.button1);
		button1.setText("mon bouton!");
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				button1.setText("J'ai cliqué!!");
				Intent intent = new Intent(MainActivity.this, MainActivity.class);
				startActivity(intent);
				
			}
		});
	}
}
