package com.example.nikola.soccerjar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.example.nikola.soccerjar.retrofit.models.LeagueResponse;
import com.example.nikola.soccerjar.retrofit.models.Team;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.my_recyclerDetail_view)
    RecyclerView recyclerViewDetail;
    @BindView(R.id.my_detailToolbar)
    Toolbar myToolbar;
    public int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitvity_detail);
        ButterKnife.bind(this);

        recyclerViewDetail.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewDetail.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        String pageName = intent.getStringExtra("caption");
        myToolbar.setTitle(pageName);





            Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                ApiManager.getClient().create(ApiService.class).getLeague(id).enqueue(new Callback<LeagueResponse>() {
                    @Override
                    public void onResponse(Call<LeagueResponse> call, Response<LeagueResponse> response) {
                        if (response.isSuccessful()) {
                            LeagueResponse leagueResponse = response.body();
                            List<Team> standing = leagueResponse.getStanding();
                            final LeagueTablesAdapter tablesAdapter = new LeagueTablesAdapter(standing);
                            recyclerViewDetail.setAdapter(tablesAdapter);
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    tablesAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<LeagueResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
        t.start();

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
