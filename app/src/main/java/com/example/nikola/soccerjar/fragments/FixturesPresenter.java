package com.example.nikola.soccerjar.fragments;

import com.example.nikola.soccerjar.retrofit.ApiManager;
import com.example.nikola.soccerjar.retrofit.ApiService;
import com.example.nikola.soccerjar.retrofit.models.FixturesResponse;
import com.example.nikola.soccerjar.retrofit.models.Team;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class FixturesPresenter {

    private FixturesView view;
    private List<Team> teamList = new ArrayList<>();

    void getTeamNames(int id) {

        if (view != null) {
            view.showProgressDialog();
        }


        ApiManager.getClient().create(ApiService.class).getFixtures(id).enqueue(new Callback<FixturesResponse>() {
            @Override
            public void onResponse(Call<FixturesResponse> call, Response<FixturesResponse> response) {
                if (response.isSuccessful()) {
                    FixturesResponse fixturesResponse = response.body();
                    teamList = fixturesResponse.getFixtures();
                    List<Team> gameStatusList = new ArrayList<>();
                    for (Team team : teamList) {
                        if (team.getStatus().equals("SCHEDULED")) {
                            gameStatusList.add(team);
                        }
                    }
                    if (view != null) {
                        view.showTeamsWithScheduledStatus(gameStatusList);
                        view.dismissProgressDialog();
                    }

                } else {
                    if (view != null) {
                        view.unsucessfulResponse();
                        view.dismissProgressDialog();
                    }
                }
            }

            @Override
            public void onFailure(Call<FixturesResponse> call, Throwable t) {
                if (view != null) {
                    view.unsucessfulResponse();
                    view.dismissProgressDialog();
                }
            }
        });
    }


    //    we want to connect with the passive view with this 2 methods
    void registerView(FixturesView fixturesView) {
        this.view = fixturesView;
    }

    void unRegisterView() {
        this.view = null;
    }

    void getPreviousGames() {
        if (view != null) {
            view.showAllTeams(teamList);
        }
    }

}
