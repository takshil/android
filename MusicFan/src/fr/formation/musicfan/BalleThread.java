package fr.formation.musicfan;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class BalleThread extends Thread {
	SurfaceHolder surfaceHolder;
	boolean cont = false;
	int posX;
	int posY;
	int sensX = 1;
	int sensY = 1;
	int colorBalle;
	Random rnd;
	
	public BalleThread(SurfaceHolder sh){
		surfaceHolder = sh;
		posX = 300;
		posY = 300;
		rnd = new Random();
	}
	
	public void demarre(){
		cont = true;
	}
	
	public void arrete(){
		cont = false;
	}

	@Override
	public void run() {
		Paint p = new Paint();
		p.setTextSize(20);
		p.setStyle(Paint.Style.FILL);
		while(cont){
			Canvas c = null;
			try{
				c = surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					p.setColor(Color.BLACK);
					c.drawCircle(posX, posY, 200, p);
					if (sensX == 1){
						posX += 10;
						if (posX + 50> c.getWidth()){
							sensX = 2;
							colorBalle = changeColor();
						}
					}else{
						posX -= 10;
						if (posX - 50 <= 0){
							sensX = 1;
							colorBalle = changeColor();
						}
					}
					if (sensY == 1){
						posY += 10;
						if (posY + 50> c.getHeight()){
							sensY = 2;
							colorBalle = changeColor();
						}
					}else{
						posY -= 10;
						if (posY - 50<= 0){
							sensY = 1;
							colorBalle = changeColor();
						}
					}
					p.setColor(colorBalle );
					c.drawCircle(posX, posY, 50, p);
				}
			}
			finally{
				if (c !=null)
					surfaceHolder.unlockCanvasAndPost(c);
			}
			
			try {
				sleep(50);
			} catch (InterruptedException e) {
			}
			
		}
	}

	private int changeColor() {
		return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
	}
}
