package com.example.nikola.soccerjar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.nikola.soccerjar.model.Results;

import java.util.List;

/**
 * Created by Nikola on 6/23/2017.
 */

public class DetailsActivity extends AppCompatActivity {

    private List<Results> modelList;
    public RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitvity_detail);
//        displayDetailContent();
//        recyclerView = (RecyclerView) findViewById(R.id.my_detail_view);
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        String year = intent.getStringExtra("year");
        String numberOfMatchdays = intent.getStringExtra("numberOfMatchdays");
        String numberOfTeams = intent.getStringExtra("numberOfTeams");
        String numberOfGames = intent.getStringExtra("numberOfGames");

        TextView textView_year = (TextView) findViewById(R.id.txt_year);
        TextView textView_matchDays = (TextView) findViewById(R.id.txt_matchdays);
        TextView textView_teams = (TextView) findViewById(R.id.txt_teams);
        TextView textView_games = (TextView) findViewById(R.id.txt_games);

        textView_year.setText(getString(R.string.year) + year);
        textView_matchDays.setText(getString(R.string.number_of_matches) + numberOfMatchdays);
        textView_teams.setText(getString(R.string.number_of_teams) + numberOfTeams);
        textView_games.setText(getString(R.string.number_of_games) + numberOfGames);

    }

//    private class MyTask extends AsyncTask<String, String, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            return HttpGetManager.getData(params[0]);
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            modelList = LeagueTableParser.parseFeed(result);
//            updateDetailDisplay();
//        }
//    }
//
//    private void updateDetailDisplay() {
//        RecyclerView.Adapter adapter = new LeagueTableAdapter(this, modelList);
//        recyclerView.setAdapter(adapter);
//    }
//
//    private void displayDetailContent() {
//        Intent intent = getIntent();
//        String id = intent.getStringExtra("id");
//        requestDetailData(URL);
//
//    }
//
//    private void requestDetailData(String uri) {
//        new MyTask().execute(uri);
//    }
}
