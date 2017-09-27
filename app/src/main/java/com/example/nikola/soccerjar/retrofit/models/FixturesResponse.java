package com.example.nikola.soccerjar.retrofit.models;

import java.util.List;

/**
 * Created by Nikola on 9/12/2017.
 */

public class FixturesResponse {

    private List<Team> fixtures;
    private String status;

    public FixturesResponse(List<Team> fixtures, String status) {
        this.fixtures = fixtures;
        this.status = status;
    }

    public List<Team> getFixtures() {
        return fixtures;
    }

}

