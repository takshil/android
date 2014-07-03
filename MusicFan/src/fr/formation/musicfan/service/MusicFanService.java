package fr.formation.musicfan.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import fr.formation.musicfan.MainActivity;
import fr.formation.musicfan.R;

public class MusicFanService extends Service {

	protected static final int TAG = 2;
	private boolean stop = false;
	protected boolean enCours;
	private Thread thread;

	public IBinder myBinder = new MyBinder();

	@Override
	public IBinder onBind(Intent arg0) {
		return myBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		if (thread == null) {
			thread = new Thread(new Runnable() {

				@Override
				public void run() {
					String strNomService = Context.NOTIFICATION_SERVICE;
					// Service pour gérer les notifications
					NotificationManager notifManager = (NotificationManager) getSystemService(strNomService);

					while (!stop) {
						Intent notifIntent = new Intent(MusicFanService.this,
								MainActivity.class);
						notifIntent.putExtra("stopService", true);
						PendingIntent contInt = PendingIntent.getActivity(
								MusicFanService.this, 2, notifIntent,
								PendingIntent.FLAG_CANCEL_CURRENT);
						Notification notification = new Notification.Builder(
								MusicFanService.this)
								.setContentTitle("Notre super site!")
								.setContentText(
										"Cliquez vite pour visiter le site du groupe!")
								.setSmallIcon(R.drawable.ic_launcher)
								.setContentIntent(contInt).build();

						notifManager.notify(TAG, notification);
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			});
			thread.start();
		}
		return START_NOT_STICKY;
	}

	@Override
	public void onDestroy() {
		stop = true;
		super.onDestroy();
	}

	public class MyBinder extends Binder {
		public MusicFanService getInstance() {
			return MusicFanService.this;
		}
	}

}
