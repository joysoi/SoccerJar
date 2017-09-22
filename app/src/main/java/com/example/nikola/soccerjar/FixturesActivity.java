package com.example.nikola.soccerjar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.nikola.soccerjar.adapter.FixturesAdapter;
import com.example.nikola.soccerjar.retrofit.ApiManager;
import com.example.nikola.soccerjar.retrofit.ApiService;
import com.example.nikola.soccerjar.retrofit.models.FixturesResponse;
import com.example.nikola.soccerjar.retrofit.models.Team;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nikola on 9/12/2017.
 */

public class FixturesActivity extends AppCompatActivity {

    RecyclerView fixturesView;
    public int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);


//        getFragmentManager().beginTransaction()
//                .replace(R.id.fragment_container, new FixturesDetailsFragment()).commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_fixturesToolbar);
        fixturesView = (RecyclerView) findViewById(R.id.recyclerFixturesView);
        fixturesView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        fixturesView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.fixturesActivity);


        ApiManager.getClient().create(ApiService.class).getFixtures(id).enqueue(new Callback<FixturesResponse>() {
            @Override
            public void onResponse(Call<FixturesResponse> call, Response<FixturesResponse> response) {
                if (response.isSuccessful()) {
                    FixturesResponse fixturesResponse = response.body();
                    List<Team> teamList = fixturesResponse.getFixtures();
                    List<Team> gameStatusList = new ArrayList();
                    for (Team team : teamList) {
                        if (team.getStatus().equals("SCHEDULED"))
                            gameStatusList.add(team);
                            FixturesAdapter adapter = new FixturesAdapter(gameStatusList);
                            fixturesView.setAdapter(adapter);
                    }

//                    FixturesAdapter fixturesAdapter = new FixturesAdapter(teamList);
//                    fixturesView.setAdapter(fixturesAdapter);

                }
            }

            @Override
            public void onFailure(Call<FixturesResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
