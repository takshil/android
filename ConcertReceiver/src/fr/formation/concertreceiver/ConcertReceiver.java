package fr.formation.concertreceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ConcertReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//if (intent.getAction().equals("fr.formation.concertreceiver.NEW")) {

			String infosConcert = intent.getExtras().getString("concert");
			
	
			// Service pour gérer les notifications
			NotificationManager notifManager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
			
			Notification notification = new Notification.Builder(context)
			 .setContentTitle ("Nouveau concert!") 
			 .setContentText (infosConcert) 
			 .setSmallIcon (R.drawable.ic_launcher) 
			 .build ();
			
			notifManager.notify(1, notification);
		//}
	}
}
