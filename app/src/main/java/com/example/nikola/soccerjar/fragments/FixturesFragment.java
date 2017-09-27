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

/**
 * Created by Nikola on 9/18/2017.
 */

public class FixturesFragment extends Fragment {

    public int id;

    @BindView(R.id.showResultsBTN)
    Button btnMoreResults;

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

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerFixturesView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
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
                            recyclerView.setAdapter(fixturesAdapter);

                        }

                    }
                    btnMoreResults.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FixturesAdapter fixturesAdapter = new FixturesAdapter(teamList);
                            recyclerView.setAdapter(fixturesAdapter);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<FixturesResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return view;
    }
}
