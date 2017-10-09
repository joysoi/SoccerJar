package com.example.nikola.soccerjar.fragments;

import com.example.nikola.soccerjar.retrofit.models.Team;

import java.util.List;

interface FixturesView {

    void showTeamsWithScheduledStatus(List<Team> fixtureList);

    void showAllTeams(List<Team> fixtureList);

    void showFilteredNames(List<Team> fixtureList, String s);

    void unsucessfulResponse();

    void showProgressDialog();

    void dismissProgressDialog();

}
