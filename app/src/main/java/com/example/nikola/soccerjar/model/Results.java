package com.example.nikola.soccerjar.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nikola on 7/1/2017.
 */

public class Results implements Parcelable {

    private String caption;
    private String league;
    private String id_;
    private String leagueTable;
    private String teamName;
    private String year;
    private String numberOfMatchdays;
    private String numberOfTeams;
    private String numberOfGames;
    private String links;
    private String currentMatchday;
    private String lastUpdated;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.caption);
        dest.writeString(this.league);
        dest.writeString(this.id_);
        dest.writeString(this.leagueTable);
        dest.writeString(this.teamName);
        dest.writeString(this.year);
        dest.writeString(this.numberOfMatchdays);
        dest.writeString(this.numberOfTeams);
        dest.writeString(this.numberOfGames);
        dest.writeString(this.links);
        dest.writeString(this.currentMatchday);
        dest.writeString(this.lastUpdated);
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNumberOfMatchdays() {
        return numberOfMatchdays;
    }

    public void setNumberOfMatchdays(String numberOfMatchdays) {
        this.numberOfMatchdays = numberOfMatchdays;
    }

    public String getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(String numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public String getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(String numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getCurrentMatchday() {
        return currentMatchday;
    }

    public void setCurrentMatchday(String currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getCaption() {

        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Results() {
    }

    protected Results(Parcel in) {
        this.caption = in.readString();
        this.league = in.readString();
        this.id_ = in.readString();
        this.leagueTable = in.readString();
        this.teamName = in.readString();
        this.year = in.readString();
        this.numberOfMatchdays = in.readString();
        this.numberOfTeams = in.readString();
        this.numberOfGames = in.readString();
        this.links = in.readString();
        this.currentMatchday = in.readString();
        this.lastUpdated = in.readString();
    }

    public static final Creator<Results> CREATOR = new Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel source) {
            return new Results(source);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };
}
