package fr.formation.ballesensor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements SensorEventListener {

	MyView view;
	int x = 200, y = 200;
	private SensorManager sm;
	private Sensor snAccel;
	private Sensor snGyro;
	private Sensor snRotation;
	private Sensor snGravity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new MyView(MainActivity.this);
		setContentView(view);

		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		snAccel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		snGyro = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		snRotation = sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
		snGravity = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);

		view.invalidate();

	}

	class MyView extends View {
		Paint paint = new Paint();

		public MyView(Context context) {
			super(context);
			paint.setColor(Color.CYAN);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			paint.setColor(Color.RED);
			canvas.drawCircle(x, y, 150, paint);
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		if (event.sensor.getType() == Sensor.TYPE_GRAVITY){
			float[] accValues = event.values;
			x += accValues[0];
			y += accValues[1];
			view.invalidate();
		}
	}

	protected void onResume() {
		super.onResume();
		sm.registerListener(this, snAccel, SensorManager.SENSOR_DELAY_NORMAL);
		sm.registerListener(this, snGyro, SensorManager.SENSOR_DELAY_NORMAL);
		sm.registerListener(this, snRotation, SensorManager.SENSOR_DELAY_NORMAL);
		sm.registerListener(this, snGravity, SensorManager.SENSOR_DELAY_NORMAL);
	}

	protected void onStop() {
		sm.unregisterListener(this);
		super.onStop();
	}
}
