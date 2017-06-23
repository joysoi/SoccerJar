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

//{
//        "syncresponse": {
//           "synckey": "2011-09-30 14:52:00",
//          "createdtrs": [
//
//        ],
//        "modtrs": [
//
//        ],
//        "deletedtrs": [
//        {
//        "companyid": "UTB17",
//        "username": "DA",
//        "date": "2011-09-26",
//        "reportid": "31341"
//        }
//        ]
//        }
//        }

//    JSONObject object = new JSONObject(result);
//    String syncresponse = object.getString("syncresponse");
//    JSONObject object2 = new JSONObject(syncresponse);
//    String synckey = object2.getString("synckey");
//    JSONArray jArray1 = object2.getJSONArray("createdtrs");
//    JSONArray jArray2 = object2.getJSONArray("modtrs");
//    JSONArray jArray3 = object2.getJSONArray("deletedtrs");
//for(int i = 0; i < jArray3 .length(); i++)
//        {
//        JSONObject object3 = jArray3.getJSONObject(i);
//        String comp_id = object3.getString("companyid");
//        String username = object3.getString("username");
//        String date = object3.getString("date");
//        String report_id = object3.getString("reportid");
//        }