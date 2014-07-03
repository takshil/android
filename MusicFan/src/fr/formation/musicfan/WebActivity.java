package fr.formation.musicfan;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		WebView webV = (WebView) findViewById(R.id.webView1);
		
		webV.loadUrl(getIntent().getDataString());
	}
}
