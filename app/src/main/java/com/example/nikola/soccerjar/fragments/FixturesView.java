package com.example.nikola.soccerjar.fragments;

import com.example.nikola.soccerjar.retrofit.models.Team;

import java.util.List;

public interface FixturesView {

    void showTeamsWithScheduledStatus(List<Team> fixtureList);

    void showAllTeams(List<Team> fixtureList);

    void unsucessfulResponse();

    void showProgressDialog();

    void dismissProgressDialog();

}
