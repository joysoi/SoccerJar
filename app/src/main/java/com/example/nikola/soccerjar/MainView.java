package com.example.nikola.soccerjar;


import com.example.nikola.soccerjar.retrofit.models.CompetitionResponse;

import java.util.List;

interface MainView {

    void showCompetitionsList(List<CompetitionResponse> competitionResponses);

    void unsuccesfulResponse();

    void showProgressDialog();

    void dismissProgressDialog();

}
