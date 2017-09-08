package com.example.nikola.soccerjar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nikola.soccerjar.adapter.LeagueTablesAdapter;
import com.example.nikola.soccerjar.model.Results;
import com.example.nikola.soccerjar.retrofit.ApiManager;
import com.example.nikola.soccerjar.retrofit.ApiService;
import com.example.nikola.soccerjar.retrofit.League;
import com.example.nikola.soccerjar.retrofit.Team;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nikola on 6/23/2017.
 */

public class DetailsActivity extends AppCompatActivity {

    private ArrayList<Results> resultsParcelableArrayList = new ArrayList<>();

    public RecyclerView detailView;
    private int id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitvity_detail);
        detailView = (RecyclerView) findViewById(R.id.my_recyclerDetail_view);
        detailView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        detailView.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        ApiManager.getClient().create(ApiService.class).getLeague(id).enqueue(new Callback<League>() {
            @Override
            public void onResponse(Call<League> call, Response<League> response) {
            if(response.isSuccessful()){
                League league = response.body();
                List<Team> standing = league.getStanding();
                LeagueTablesAdapter tablesAdapter = new LeagueTablesAdapter(standing);
                detailView.setAdapter(tablesAdapter);
            }


            }

            @Override
            public void onFailure(Call<League> call, Throwable t) {

            }
        });

    }

//        ApiManager.getClient().create(ApiService.class).getLeague(id).enqueue(new Callback<List<League>>() {
//            @Override
//            public void onResponse(Call<List<League>> call, Response<List<League>> response) {
//                if(response.isSuccessful()){
//                    League league = response.body();
//                    league.getStanding();
//                    new LeagueTablesAdapter(league.getStanding());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<League>> call, Throwable t) {
//
//            }
//        });
//
//    public class LeagueTablesClass extends LeagueTablesParserTask {
//        @Override
//        protected void onPostExecute(ArrayList<Results> parcelables) {
//            if (parcelables != null) {
//                resultsParcelableArrayList.clear();
//                for (Results resultsParcelable : parcelables) {
//                    resultsParcelableArrayList.add(resultsParcelable);
//                }
//                updateDetailsDisplay();
//            }
//        }
//    }


//    public void updateDetailsDisplay() {
//
//        RecyclerView.Adapter adapter = new LeagueTablesAdapter(resultsParcelableArrayList);
//        detailView.setAdapter(adapter);
//    }
}

