package com.example.nikola.soccerjar.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.nikola.soccerjar.R;
import com.example.nikola.soccerjar.adapter.FixturesAdapter;
import com.example.nikola.soccerjar.retrofit.ApiManager;
import com.example.nikola.soccerjar.retrofit.ApiService;
import com.example.nikola.soccerjar.retrofit.models.FixturesResponse;
import com.example.nikola.soccerjar.retrofit.models.Team;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FixturesFragment extends Fragment {

    public int id;
    @BindView(R.id.showResultsBTN)
    Button btnMoreResults;
    @BindView(R.id.recyclerFixturesView)
    RecyclerView recyclerViewFixtures;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    public static FixturesFragment newInstance(int id) {
        FixturesFragment f = new FixturesFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (!getArguments().isEmpty()) {
            id = getArguments().getInt("id");
        }
        if (savedInstanceState != null) {
            id = savedInstanceState.getInt("id");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.my_fixtures_detail_view, container, false);
        ButterKnife.bind(this, view);

        recyclerViewFixtures.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewFixtures.setLayoutManager(layoutManager);

        AsyncTask task = new AsyncTask();
        task.setProgressBar(progressBar);
        task.execute();

        return view;
    }

    public class AsyncTask extends android.os.AsyncTask<String, Integer, String> {

        ProgressBar bar;

        public void setProgressBar(ProgressBar bar) {
            this.bar = bar;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(100);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected String doInBackground(String... params) {

            for (int i = 0; i < 100; i++) {
                publishProgress(i);
            }

            ApiManager.getClient().create(ApiService.class).getFixtures(id).enqueue(new Callback<FixturesResponse>() {
                @Override
                public void onResponse(Call<FixturesResponse> call, Response<FixturesResponse> response) {
                    if (response.isSuccessful()) {
                        FixturesResponse fixturesResponse = response.body();
                        final List<Team> teamList = fixturesResponse.getFixtures();
                        List<Team> gameStatusList = new ArrayList();
                        for (Team team : teamList) {
                            if (team.getStatus().equals("SCHEDULED")) {
                                gameStatusList.add(team);
                                FixturesAdapter fixturesAdapter = new FixturesAdapter(gameStatusList);
                                recyclerViewFixtures.setAdapter(fixturesAdapter);
                            }
                        }
                        btnMoreResults.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FixturesAdapter fixturesAdapter = new FixturesAdapter(teamList);
                                recyclerViewFixtures.setAdapter(fixturesAdapter);
                            }
                        });
                    }
                }
                @Override
                public void onFailure(Call<FixturesResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
            return null;
        }
    }

}

