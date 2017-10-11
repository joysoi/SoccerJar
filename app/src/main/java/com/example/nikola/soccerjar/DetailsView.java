package com.example.nikola.soccerjar;


import com.example.nikola.soccerjar.retrofit.models.Team;

import java.util.List;

interface DetailsView {

    void showLeagueTable(List<Team> teams);

    void unsuccesfulResponse();

    void showProgressDialog();

    void dismissProgressDialog();

}
