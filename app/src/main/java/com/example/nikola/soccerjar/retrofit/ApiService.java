package com.example.nikola.soccerjar.retrofit;

import com.example.nikola.soccerjar.retrofit.models.Competition;
import com.example.nikola.soccerjar.retrofit.models.Fixtures;
import com.example.nikola.soccerjar.retrofit.models.League;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Nikola on 7/3/2017.
 */

public interface ApiService {

    @Headers("X-Auth-Token: 29c98aeca58b4f52a9b3ffb122ca9c31")
    @GET("/v1/competitions/")
    Call<List<Competition>> getCompetitions();

    @Headers("X-Auth-Token: 29c98aeca58b4f52a9b3ffb122ca9c31")
    @GET("/v1/competitions/{id}/leagueTable")
    Call<League> getLeague(@Path("id") int id);

    @Headers("X-Auth-Token: 29c98aeca58b4f52a9b3ffb122ca9c31")
    @GET("/v1/competitions/{id}/fixtures")
    Call<Fixtures> getFixtures(@Path("id") int id);

}
