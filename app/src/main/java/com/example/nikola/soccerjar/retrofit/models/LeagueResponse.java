package com.example.nikola.soccerjar.retrofit.models;

import java.util.List;


public class LeagueResponse {

    private final List<Team> standing;

    public LeagueResponse(List<Team> standing) {
        this.standing = standing;
    }
    public List<Team> getStanding() {
        return standing;
    }

}
