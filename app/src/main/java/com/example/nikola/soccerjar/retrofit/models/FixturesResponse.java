package com.example.nikola.soccerjar.retrofit.models;

import java.util.List;

public class FixturesResponse {

    private List<Team> fixtures;

    public FixturesResponse(List<Team> fixtures) {
        this.fixtures = fixtures;
    }

    public List<Team> getFixtures() {
        return fixtures;
    }

}

