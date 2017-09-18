package com.example.nikola.soccerjar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nikola.soccerjar.adapter.LeagueTablesAdapter;
import com.example.nikola.soccerjar.retrofit.ApiManager;
import com.example.nikola.soccerjar.retrofit.ApiService;
import com.example.nikola.soccerjar.retrofit.models.League;
import com.example.nikola.soccerjar.retrofit.models.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nikola on 6/23/2017.
 */

public class DetailsActivity extends AppCompatActivity {

    public RecyclerView detailView;
    public int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitvity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_detailToolbar);
        detailView = (RecyclerView) findViewById(R.id.my_recyclerDetail_view);
        detailView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        detailView.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        String pageName = intent.getStringExtra("caption");
        toolbar.setTitle(pageName);
        setSupportActionBar(toolbar);

        ApiManager.getClient().create(ApiService.class).getLeague(id).enqueue(new Callback<League>() {
            @Override
            public void onResponse(Call<League> call, Response<League> response) {
                if (response.isSuccessful()) {
                    League league = response.body();
                    List<Team> standing = league.getStanding();
                    LeagueTablesAdapter tablesAdapter = new LeagueTablesAdapter(standing);
                    detailView.setAdapter(tablesAdapter);
                }
            }

            @Override
            public void onFailure(Call<League> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        MenuItem frwdButton = menu.findItem(R.id.action_forward);
        MenuItemCompat.getActionView(frwdButton);
        return super.onCreateOptionsMenu(menu);
    }

    public void showFixtures(MenuItem item) {

        Intent intent1 = new Intent(DetailsActivity.this, FixturesActivity.class);
        intent1.putExtra("id", id);
        startActivity(intent1);
    }
}

