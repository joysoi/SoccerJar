package com.example.nikola.soccerjar;


import com.example.nikola.soccerjar.retrofit.models.CompetitionResponse;

import java.util.List;

public interface MainView {

    void getCompetitionsList(List<CompetitionResponse> competitionResponses);

    void unsuccesfulResponse();

    void showProgressDialog();

    void dismissProgressDialog();

}
