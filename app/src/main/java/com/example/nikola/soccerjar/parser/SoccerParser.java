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
                model.setLeague(obj.getString("league"));
                modelList.add(model);
            }
            return modelList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

//    public static List<Results> parseTeam(String content) {
//        try {
//
//            JSONObject object = new JSONObject(content);
//            JSONArray jsonArray1 = object.getJSONArray(LINKS);
//            List<Results> modelList = new ArrayList<>();
//
//            for (int i = 0; i < jsonArray1.length(); i++) {
//
//                JSONObject obj = jsonArray1.getJSONObject(i);
//                Results model = new Results();
//                model.setLeagueTable(obj.getString("leagueTable"));
//                model.setTeamName(obj.getString("teamName"));
//                modelList.add(model);
//            }
//
//            return modelList;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

