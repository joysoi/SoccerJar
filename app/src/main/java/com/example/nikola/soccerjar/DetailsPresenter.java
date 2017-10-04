package com.example.nikola.soccerjar;

import com.example.nikola.soccerjar.retrofit.ApiManager;
import com.example.nikola.soccerjar.retrofit.ApiService;
import com.example.nikola.soccerjar.retrofit.models.LeagueResponse;
import com.example.nikola.soccerjar.retrofit.models.Team;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailsPresenter {

    private List<Team> teams = new ArrayList<>();
    DetailsView view;


    public void getLeagueTable(int id) {

        if (view != null) {
            view.showProgressDialog();
        }

        ApiManager.getClient().create(ApiService.class).getLeague(id).enqueue(new Callback<LeagueResponse>() {
            @Override
            public void onResponse(Call<LeagueResponse> call, Response<LeagueResponse> response) {
                if (response.isSuccessful()) {
                    LeagueResponse leagueResponse = response.body();
                    List<Team> standing = leagueResponse.getStanding();

                    if (view != null) {
                        view.showLeagueTable(standing);
                        view.dismissProgressDialog();
                    }
                }
            }

            @Override
            public void onFailure(Call<LeagueResponse> call, Throwable t) {
                if (view != null) {
                    view.unsuccesfulResponse();
                    view.dismissProgressDialog();
                }
            }
        });

    }

    void registerView(DetailsView detailsView){
        this.view = detailsView;
    }

    void unRegisterView(){
        this.view = null;
    }

}
