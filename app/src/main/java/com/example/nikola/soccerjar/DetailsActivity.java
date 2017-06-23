package com.example.nikola.soccerjar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Nikola on 6/23/2017.
 */

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitvity_detail);

        Intent intent = getIntent();
        String legaue_name = intent.getStringExtra("leagueTable");
        String team_name = intent.getStringExtra("teamName");

        TextView view_league_name = (TextView) findViewById(R.id.txt_league_name);
        TextView view_team_name = (TextView) findViewById(R.id.txt_team_name);

        view_league_name.setText(legaue_name);
        view_team_name.setText(team_name);



    }
}
