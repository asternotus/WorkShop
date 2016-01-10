package mindmap.selestar.com.mindmap.controllers;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ASTER-NOTUS on 07.12.2015.
 */
public class HTTPRequestAsyncTask extends AsyncTask<String, String, JSONArray> {

    @Override
    protected JSONArray doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;
        JSONArray response = new JSONArray();

        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();

            if(responseCode == 200){
                String responseString = readStream(urlConnection.getInputStream());
                Log.v("REQUEST", responseString);
                response = new JSONArray(responseString);
            }else{
                Log.v("REQUEST", "Response code:"+ responseCode);
            }

        } catch (Exception e) {
            Log.v("REQUEST", e.toString());
            e.printStackTrace();
        } finally {
            if(urlConnection != null)
                urlConnection.disconnect();
        }

        return response;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
