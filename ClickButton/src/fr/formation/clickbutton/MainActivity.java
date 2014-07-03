package fr.formation.clickbutton;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView text =null;
	int nbClick = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button1 = (Button) findViewById(R.id.button1);
        text = (TextView) findViewById(R.id.textView1);
        
        button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nbClick ++;
				text.setText("J'ai cliqué " + String.valueOf(nbClick) + " fois!");
				MainActivity.this.finish();
			}
		});
        Log.d("MainActivity", "Activity crée");
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("MainActivity", "Activity détruite");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d("MainActivity", "Activity en pause");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("MainActivity", "Activity redémarrée");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("MainActivity", "Activity résume");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("MainActivity", "Activity démarrée");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("MainActivity", "Activity arrêtée");
	}
    
    
}
