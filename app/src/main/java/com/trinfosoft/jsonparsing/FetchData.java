package com.trinfosoft.jsonparsing;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.trinfosoft.jsonparsing.MainActivity.data;

public class FetchData extends AsyncTask <Void,Void,Void> {

    String jsondata="";
    String jsonsingleparse = "";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.myjson.com/bins/gtbnr");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while(line!=null){
                line = bufferedReader.readLine();
                jsondata = jsondata + line;
            }

            JSONArray ja = new JSONArray(jsondata);
            jsondata = "";
            for(int i=0;i<ja.length();i++){
                JSONObject jo = (JSONObject) ja.get(i);
                jsonsingleparse = "Name: " + jo.get("name") + "\n" +
                        "Class: " + jo.get("class");
                jsondata = jsondata + jsonsingleparse + "\n\n";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        data.setText(jsondata);
    }
}
