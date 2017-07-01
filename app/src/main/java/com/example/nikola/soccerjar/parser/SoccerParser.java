package com.example.nikola.soccerjar.parser;

import com.example.nikola.soccerjar.model.Results;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikola on 6/22/2017.
 */

public class SoccerParser {


    public static List<Results> parseFeed(String content) {

        try {

            JSONArray jsonArray = new JSONArray(content);

            List<Results> modelList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);
                Results model = new Results();
                model.setCaption(obj.getString("caption"));
                model.setYear(obj.getString("year"));
                model.setNumberOfMatchdays(obj.getString("numberOfMatchdays"));
                model.setNumberOfTeams(obj.getString("numberOfTeams"));
                model.setNumberOfGames(obj.getString("numberOfGames"));
                modelList.add(model);
            }

            return modelList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}



