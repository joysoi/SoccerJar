package com.example.nikola.soccerjar.retrofit.models;

import java.util.List;

/**
 * Created by Nikola on 7/3/2017.
 */

public class LeagueResponse {

    private final List<Team> standing;

    public LeagueResponse(List<Team> standing) {
        this.standing = standing;
    }

    public List<Team> getStanding() {
        return standing;
    }

}
