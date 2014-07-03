package fr.formation.musicfan;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewBalle extends SurfaceView implements
		SurfaceHolder.Callback {

	BalleThread balleThread;

	public SurfaceViewBalle(Context context) {
		super(context);
		SurfaceHolder holder = getHolder();
		balleThread = new BalleThread(holder);
		holder.addCallback(this);
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// on lance le thread qui s'occupe de l'affichage
		balleThread.demarre();
		balleThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		balleThread.arrete();
		while (retry)
			try {
				balleThread.join();
				retry = false;
			} catch (InterruptedException e) {
			}

	}

}
