package com.example.httptest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String url = "http://nicolasjanel.fr/drive/service.php";
		new MyAsyncTask(MainActivity.this, url).execute();
	}
	
	public static JSONArray doGet(String url){
		JSONArray json = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("accept", "application/json");
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null){
				InputStream stream = entity.getContent();
				String result = convertStreamToString(stream);
				json = new JSONArray(result);
				stream.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;
	}

	public static String convertStreamToString(InputStream is) {

    	// On utilise un bufferedReader pour lire un flux en string
    	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
 
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

	public void chargerPersonne(List<Personne> listPersonne) {
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.personne);
		for (Personne personne : listPersonne){
			listAdapter.add(personne.toString());
		}
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(listAdapter);
	}
}
