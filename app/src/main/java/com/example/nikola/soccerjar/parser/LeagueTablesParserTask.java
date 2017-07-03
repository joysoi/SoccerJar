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
 * Created by Nikola on 6/22/2017.
 */

public class LeagueTablesParserTask extends AsyncTask<String, Void, ArrayList<Results>> {


    public static String STANDING = "standing";
    private ArrayList<Results> resultsArrayList = new ArrayList<>();

    private ArrayList<Results> parseFeed(String content) throws JSONException {

        JSONObject jsonObject = new JSONObject(content);
        JSONArray jsonArray = jsonObject.getJSONArray(STANDING);

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject obj = jsonArray.getJSONObject(i);
            Results model = new Results();
            model.setTeamName(obj.getString("teamName"));
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
            final String leagueTable = "leagueTable";

            Uri builtUri = Uri.parse(URL).buildUpon()
                    .appendPath(params[0])
                    .appendPath(leagueTable)
                    .build();

            java.net.URL url = new URL(builtUri.toString());

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






