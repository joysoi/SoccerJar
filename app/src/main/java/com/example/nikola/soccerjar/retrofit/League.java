package com.example.nikola.soccerjar.retrofit;

import java.util.List;

/**
 * Created by Nikola on 7/3/2017.
 */

public class League {

    private final List<Team> standing;

    public League(List<Team> standing) {
        this.standing = standing;
    }

    public List<Team> getStanding() {
        return standing;
    }

}
