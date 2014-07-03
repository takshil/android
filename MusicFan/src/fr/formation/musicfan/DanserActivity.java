package fr.formation.musicfan;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class DanserActivity extends Activity {

	ImageView danseur;
	Animation animation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DanserActivity.this.getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_danser);
		
		danseur = (ImageView) findViewById(R.id.imageView1);
		
		animation = AnimationUtils.loadAnimation(DanserActivity.this, R.anim.animation);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				danseur.startAnimation(animation);
			}
		});
		danseur.startAnimation(animation);
	}
}
