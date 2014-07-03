package fr.formation.asynctest;

import android.app.Activity;
import android.widget.ProgressBar;

public abstract class MyActivity extends Activity {

	public ProgressBar progress;
	
	public abstract void demarrerActivity();
}
