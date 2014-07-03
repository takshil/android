package fr.formation.touchscreen;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView nbDoigts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		nbDoigts = (TextView) findViewById(R.id.nbDoigts);
		View view = findViewById(R.id.layout1);
		view.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				int nb = arg1.getPointerCount();
				Log.d("####", nb + " doigts");
				switch (arg1.getActionMasked()) { 
					case MotionEvent.ACTION_DOWN: 
						nbDoigts.setText(nb + " doigts appuyés");
						arg0.setBackgroundColor(Color.argb(255, 25*nb, 25*nb, 25*nb)); 
					case MotionEvent.ACTION_POINTER_DOWN: 
						nbDoigts.setText(nb + " doigts appuyés");
						arg0.setBackgroundColor(Color.argb(255, 25*nb, 25*nb, 25*nb)); 
						break;
					case MotionEvent.ACTION_POINTER_UP:
						nbDoigts.setText(nb + " doigts appuyés");
						arg0.setBackgroundColor(Color.argb(255, 25*nb, 25*nb, 25*nb));
						break;
					case MotionEvent.ACTION_UP:
						nbDoigts.setText(" 0 doigts appuyés");
						arg0.setBackgroundColor(Color.argb(255, 0, 0, 0));
				}
				arg0.invalidate();
				return true;
			}
		});
	}
}
