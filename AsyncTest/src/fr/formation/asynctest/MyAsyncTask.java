package fr.formation.asynctest;

import java.util.Date;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void, Integer, Long> {

	Integer compteur;
	MyActivity activity;

	public MyAsyncTask(MyActivity myActivity) {
		compteur = 6;
		activity = myActivity;
	}

	@Override
	protected Long doInBackground(Void... arg0) {
		long temps = new Date().getTime();
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			publishProgress(i);
		}
		long tempsFin = new Date().getTime();
		return tempsFin - temps;
	}

	@Override
	// executé en dehors du thread pour mettre à jour l'UI
	// lancé par publishProgress
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		activity.progress.setProgress(values[0]);
	}

	@Override
	protected void onPostExecute(Long result) {

		super.onPostExecute(result);
		activity.demarrerActivity();

	}
}
