package fr.formation.calculatrice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final int DIV = 4;
	private static final int MUL = 3;
	private static final int MOINS = 2;
	private static final int PLUS = 1;
	int operation = 0;
	int resultat = 0;
	String nombre1 = "";
	String nombre2 = "";
	
	TextView tvResultat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvResultat = (TextView) findViewById(R.id.resultat);
		LinearLayout numberView = (LinearLayout) findViewById(R.id.numberLayout);
		int chiffre = 1;
		for (int i = 0; i< 3; i++){
			LinearLayout ligneLayout = new LinearLayout(this);
			ligneLayout.setOrientation(LinearLayout.HORIZONTAL);
			for (int j = 0; j< 3; j++){
				Button button = new Button(this);
				button.setText(String.valueOf(chiffre));
				button.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Button button = (Button)v;
						if (operation == 0){ 
							nombre1 = nombre1 + button.getText().toString();
						}else{
							nombre2 = nombre2 + button.getText().toString();
						}
						
					}
				});
				ligneLayout.addView(button);
				chiffre++;
			}
			numberView.addView(ligneLayout);
		}
		Button button = new Button(this);
		button.setText("0");
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Button button = (Button)v;
				if (operation == 0){ 
					nombre1 = nombre1 + button.getText().toString();
				}else{
					nombre2 = nombre2 + button.getText().toString();
				}
				
			}
		});
		numberView.addView(button);
		
		Button buttonPlus = (Button) findViewById(R.id.buttonPlus);
		buttonPlus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (nombre1.equals("")){
					nombre1 = String.valueOf(resultat);
				}
				operation = PLUS;
			}
		});
		
		Button buttonMoins = (Button) findViewById(R.id.buttonMoins);
		buttonMoins.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (nombre1.equals("")){
					nombre1 = String.valueOf(resultat);
				}
				operation = MOINS;
			}
		});
		
		Button buttonMul = (Button) findViewById(R.id.buttonMul);
		buttonMul.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (nombre1.equals("")){
					nombre1 = String.valueOf(resultat);
				}
				operation = MUL;
			}
		});
		
		Button buttonDiv = (Button) findViewById(R.id.buttonDiv);
		buttonDiv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (nombre1.equals("")){
					nombre1 = String.valueOf(resultat);
				}
				operation = DIV;
			}
		});
		
		Button buttonEgal = (Button) findViewById(R.id.buttonEgal);
		buttonEgal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (operation == PLUS){
					resultat = Integer.parseInt(nombre1) + Integer.parseInt(nombre2);
				}
				if (operation == MOINS){
					resultat = Integer.parseInt(nombre1) - Integer.parseInt(nombre2);
				}
				if (operation == MUL){
					resultat = Integer.parseInt(nombre1) * Integer.parseInt(nombre2);
				}
				if (operation == DIV){
					resultat = Integer.parseInt(nombre1) / Integer.parseInt(nombre2);
				}
				tvResultat.setText(String.valueOf(resultat));
				nombre1 = "";
				nombre2 = "";
				operation = 0;
			}
		});
	}
}
