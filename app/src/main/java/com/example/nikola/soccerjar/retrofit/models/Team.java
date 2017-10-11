package com.example.nikola.soccerjar.retrofit.models;


public class Team {

    private final String teamName;
    private final String position;
    private final String points;
    private final String playedGames;
    private final String goalDifference;
    private final String homeTeamName;
    private final String awayTeamName;
    private final String status;
    private Result result;

    public Team(String teamName, String position, String points, String playedGames, String goalDifference, String homeTeamName, String awayTeamName, Result result, String status) {
        this.teamName = teamName;
        this.position = position;
        this.points = points;
        this.playedGames = playedGames;
        this.goalDifference = goalDifference;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.status = status;
        this.result = result;
    }

    public String getPlayed() {
        return playedGames;
    }

    public String getGdiff() {
        return goalDifference;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getPoints() {
        return points;
    }

    public String getPosition() {
        return position;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public String getStatus() {
        return status;
    }

    public Result getResult() {
        return result;
    }
}
