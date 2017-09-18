package com.example.nikola.soccerjar.retrofit.models;

import java.util.List;

/**
 * Created by Nikola on 9/12/2017.
 */

public class Fixtures {

    private List<Team> fixtures;

    public Fixtures(List<Team> fixtures) {
        this.fixtures = fixtures;

    }

    public List<Team> getFixtures() {
        return fixtures;
    }


}

