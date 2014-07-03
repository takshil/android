package com.example.httptest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<Void, Void, List<Personne>> {

	MainActivity activity;
	
	String uri;

	public MyAsyncTask(MainActivity myActivity, String myUri) {
		activity = myActivity;
		uri = myUri;
	}

	@Override
	protected List<Personne> doInBackground(Void... url) {
		List<Personne> listPersonne = new ArrayList<Personne>();
		JSONArray json = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(uri);
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
		
		for (int i = 0; i < json.length(); i++){
			JSONObject jsObject;
			try {
				jsObject = json.getJSONObject(i);
				Personne personne = new Personne();
				personne.setNom(jsObject.getString("nom"));
				personne.setPrenom(jsObject.getString("prenom"));
				listPersonne.add(personne);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		}
		return listPersonne;
	}

	@Override
	// executé en dehors du thread pour mettre à jour l'UI
	// lancé par publishProgress
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
		
	}

	@Override
	protected void onPostExecute(List<Personne> result) {

		super.onPostExecute(result);
		activity.chargerPersonne(result);

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
}
