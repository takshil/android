package org.hackingweb.gw2compagnon.rest;

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
import org.hackingweb.gw2compagnon.activity.MainActivity;
import org.hackingweb.gw2compagnon.model.account.*;
import org.hackingweb.gw2compagnon.model.account.Character;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

/**
 * Created by cyril on 15/08/2015.
 */
public class AccountHttpRest extends AsyncTask<Void, Void, List<org.hackingweb.gw2compagnon.model.account.Character>> {

    MainActivity activity;

    String uri;

    public AccountHttpRest(MainActivity myActivity, String myUri) {
        activity = myActivity;
        uri = myUri;
    }

    @Override
    protected List<Character> doInBackground(Void... url) {
        List<Character> lstCharacters = new ArrayList<Character>();
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
                Character perso = new Character();
                perso.setName(jsObject.getString("name"));
                lstCharacters.add(perso);
            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
        return lstCharacters;
    }

    @Override
    // executé en dehors du thread pour mettre à jour l'UI
    // lancé par publishProgress
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(List<Character> result) {

        super.onPostExecute(result);
        activity.chargerCharacters(result);

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
