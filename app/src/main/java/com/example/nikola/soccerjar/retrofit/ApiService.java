package com.example.nikola.soccerjar.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Nikola on 7/3/2017.
 */

public interface ApiService {

    @GET("/v1/competitions/")
    Call<List<Competition>> getCompetitions();

    @GET("/v1/competitions/{id}/leagueTable")
    Call<League> getLeague(@Path("id")int id);
}
