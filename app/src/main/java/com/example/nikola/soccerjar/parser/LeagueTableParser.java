package com.example.nikola.soccerjar.parser;

import com.example.nikola.soccerjar.model.Results;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikola on 6/24/2017.
 */


public class LeagueTableParser {

    public static String LINKS = "_links";

    public static List<Results> parseFeed(String content){

        try {

            JSONArray jsonArray = new JSONArray(content);
           JSONObject jsonObject = jsonArray.getJSONObject(Integer.parseInt(LINKS));
            List<Results> modelList = new ArrayList<>();

            for (int i = 0; i < jsonObject.length(); i++) {

                JSONObject obj = jsonObject.getJSONObject(String.valueOf(i));
                Results model = new Results();
                model.setLeagueTable(obj.getString("leagueTable"));
                modelList.add(model);
            }
            return modelList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}





