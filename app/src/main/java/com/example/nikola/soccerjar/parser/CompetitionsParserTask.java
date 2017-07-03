package com.example.nikola.soccerjar.parser;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.nikola.soccerjar.model.Results;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Nikola on 6/24/2017.
 */


public class CompetitionsParserTask extends AsyncTask<String, Void, ArrayList<Results>> {


    ArrayList<Results> resultsArrayList = new ArrayList<>();

    private ArrayList<Results> parseFeed(String content) throws JSONException {

        JSONArray jsonArray = new JSONArray(content);

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject obj = jsonArray.getJSONObject(i);
            Results model = new Results();
            model.setCaption(obj.getString("caption"));
            model.setId_(obj.getString("id"));

            resultsArrayList.add(model);
        }

        return resultsArrayList;
    }


    @Override
    protected ArrayList<Results> doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String content = null;


        try {
            final String URL = "http://api.football-data.org/v1/competitions/";


            Uri builtUri = Uri.parse(URL).buildUpon()
                    .build();

            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            content = buffer.toString();
        } catch (IOException e) {
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
        }

        try {
            return parseFeed(content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}






