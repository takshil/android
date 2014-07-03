package fr.formation.musicfan;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fr.formation.musicfan.service.MusicFanService;

public class MainActivity extends Activity {

	TextView textBienvenue;
	EditText saisieName;
	Button boutonVal;
	MusicFanApp myApp;
	int TAG = 1;
	Intent intent;
	
	private MusicFanService service;
	
	private ServiceConnection connection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			service = null;
			Toast.makeText(MainActivity.this, "Service déconnecté", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			service = ((MusicFanService.MyBinder)binder).getInstance();
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myApp = (MusicFanApp) MainActivity.this.getApplication();
		setContentView(R.layout.activity_main);
		
		
		//Démarrage du service
		intent = new Intent(this, MusicFanService.class) ; 
		startService(intent);
		bindService(intent, connection, BIND_AUTO_CREATE);

		
		Button bService = (Button)findViewById(R.id.service);
		bService.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				unbindService(connection);
				stopService(intent);				
			}
		});

		
		textBienvenue = (TextView) findViewById(R.id.bienvenue);
		saisieName = (EditText) findViewById(R.id.editName);
		boutonVal = (Button) findViewById(R.id.buttonVal);
		if (!myApp.getName().equals("")){
			textBienvenue.setText("Bienvenue " + myApp.getName() + " !");
			saisieName.setVisibility(View.GONE);
			boutonVal.setVisibility(View.GONE);
		}
		boutonVal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {  
				myApp.setName(saisieName.getText().toString());
				if (!myApp.getName().equals("")){
					textBienvenue.setText("Bienvenue " + myApp.getName() + " !");
					saisieName.setVisibility(View.GONE);
					boutonVal.setVisibility(View.GONE);
				}
			}
		});
		
		Button boutonSMS = (Button) findViewById(R.id.buttonSMS);
		boutonSMS.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri telephone = Uri.parse("sms:0606060606");
				Intent intentSMS = new Intent(Intent.ACTION_SENDTO, telephone);
				intentSMS.putExtra("sms_body", "Bonjour, je suis " + myApp.getName());
				startActivity(intentSMS);
			}
		});
		
		TextView textSite = (TextView) findViewById(R.id.textSite);
		textSite.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri uriSite = Uri.parse("http://www.merzhin.net");
				Intent intentSite = new Intent(Intent.ACTION_VIEW, uriSite);
				startActivity(intentSite);
				
				String strNomService = Context.NOTIFICATION_SERVICE; 
				// Service pour gérer les notifications
				NotificationManager notifManager = (NotificationManager) getSystemService(strNomService);
				
				Uri telephone = Uri.parse("sms:0606060606");
				Intent notifIntent = new Intent(Intent.ACTION_SENDTO, telephone); 
				//Intent notifIntent = new Intent(MainActivity.this, InfosActivity.class);
				PendingIntent contInt = PendingIntent.getActivity(MainActivity.this, 2, notifIntent, PendingIntent.FLAG_CANCEL_CURRENT);
				Notification notification = new Notification.Builder(MainActivity.this)
				 .setContentTitle ("SMS au chanteur") 
				 .setContentText ("Cliquez vite pour envoyer un SMS au chanteur du groupe!") 
				 .setSmallIcon (R.drawable.ic_launcher) 
				 .setContentIntent(contInt)
				 .build ();
				
				notifManager.notify(TAG, notification);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.item1:
				Intent intent = new Intent(this, InfosActivity.class);
				startActivity(intent);
				break;
			case R.id.item2:
				//Toast.makeText(this, "Bientôt disponible", Toast.LENGTH_LONG).show();
				Intent intentDanser = new Intent(this, DanserActivity.class);
				startActivity(intentDanser);
				break;
		}
		return true;
	}	
	
	@Override
	protected void onDestroy() {
		stopService(intent);
		unbindService(connection);
		super.onDestroy();
	}
	
}
