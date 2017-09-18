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
import com.example.nikola.soccerjar.retrofit.models.Fixtures;
import com.example.nikola.soccerjar.retrofit.models.Team;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_fixturesToolbar);
        fixturesView = (RecyclerView) findViewById(R.id.recyclerFixturesView);
        fixturesView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        fixturesView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);


        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.fixturesActivity);


        ApiManager.getClient().create(ApiService.class).getFixtures(id).enqueue(new Callback<Fixtures>() {
            @Override
            public void onResponse(Call<Fixtures> call, Response<Fixtures> response) {
                if (response.isSuccessful()) {
                    Fixtures fixtures = response.body();
                    List<Team> list = fixtures.getFixtures();
                    FixturesAdapter fixturesAdapter = new FixturesAdapter(list);
                    fixturesView.setAdapter(fixturesAdapter);
                }
            }

            @Override
            public void onFailure(Call<Fixtures> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}



