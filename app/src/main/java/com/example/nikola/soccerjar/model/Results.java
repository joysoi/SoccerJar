package com.example.nikola.soccerjar.model;

/**
 * Created by Nikola on 6/22/2017.
 */

public class Results {

    private String caption;
    private String league;
    private String id_;
    private String leagueTable;
    private String teamName;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getId_() {
        return id_;
    }

    public void setId_(String id_) {
        this.id_ = id_;
    }

    public String getLeagueTable() {
        return leagueTable;
    }

    public void setLeagueTable(String leagueTable) {
        this.leagueTable = leagueTable;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }
}
