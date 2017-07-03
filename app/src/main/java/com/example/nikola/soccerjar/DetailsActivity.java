package com.example.nikola.soccerjar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nikola.soccerjar.adapter.LeagueTablesAdapter;
import com.example.nikola.soccerjar.model.Results;
import com.example.nikola.soccerjar.parser.LeagueTablesParserTask;

import java.util.ArrayList;

/**
 * Created by Nikola on 6/23/2017.
 */

public class DetailsActivity extends AppCompatActivity {

    private ArrayList<Results> resultsParcelableArrayList = new ArrayList<>();
    public RecyclerView detailView;
    private String id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitvity_detail);
        detailView = (RecyclerView) findViewById(R.id.my_recyclerDetail_view);
        detailView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        detailView.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        LeagueTablesClass leagueTablesClass = new LeagueTablesClass();
        leagueTablesClass.execute(id);

    }

    public class LeagueTablesClass extends LeagueTablesParserTask {
        @Override
        protected void onPostExecute(ArrayList<Results> parcelables) {
            if (parcelables != null) {
                resultsParcelableArrayList.clear();
                for (Results resultsParcelable : parcelables) {
                    resultsParcelableArrayList.add(resultsParcelable);
                }
                updateDetailsDisplay();
            }
        }
    }


    public void updateDetailsDisplay() {

        RecyclerView.Adapter adapter = new LeagueTablesAdapter(resultsParcelableArrayList);
        detailView.setAdapter(adapter);
    }
}

