package com.example.nikola.soccerjar;

import com.example.nikola.soccerjar.retrofit.ApiManager;
import com.example.nikola.soccerjar.retrofit.ApiService;
import com.example.nikola.soccerjar.retrofit.models.CompetitionResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


class MainPresenter {

    private MainView view;

    void getCompetitionList() {

        if (view != null) {
            view.showProgressDialog();
        }

        ApiManager.getClient().create(ApiService.class).getCompetitions().enqueue(new Callback<List<CompetitionResponse>>() {
            @Override
            public void onResponse(Call<List<CompetitionResponse>> call, Response<List<CompetitionResponse>> response) {
                if (response.isSuccessful()) {
                    List<CompetitionResponse> competitionListResponse = response.body();
                    final List<CompetitionResponse> responseList = new ArrayList<>();
                    for (CompetitionResponse competitionResponse : competitionListResponse) {
                        String[] strList = new String[]{"445", "446", "447", "449", "452", "455", "456", "459"};
                        int[] intList = new int[strList.length];
                        for (int i = 0; i < strList.length; i++) {
                            try {
                                intList[i] = Integer.parseInt(strList[i]);
                            } catch (NumberFormatException nfe) {
                                nfe.printStackTrace();
                            }
                        }
                        for (int anIntList : intList) {
                            if (competitionResponse.get_id() == anIntList) {
                                responseList.add(competitionResponse);
                            }
                            if (view != null) {
                                view.showCompetitionsList(responseList);
                                view.dismissProgressDialog();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CompetitionResponse>> call, Throwable t) {
                if (view != null) {
                    view.unsuccesfulResponse();
                    view.dismissProgressDialog();
                }
            }
        });


    }

    void registerView(MainView mainView) {
        this.view = mainView;
    }

    void unRegisterView() {
        this.view = null;
    }

}
