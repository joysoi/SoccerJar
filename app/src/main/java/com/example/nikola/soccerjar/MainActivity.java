package com.example.nikola.soccerjar;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nikola.soccerjar.adapter.CompetitionsAdapter;
import com.example.nikola.soccerjar.retrofit.ApiManager;
import com.example.nikola.soccerjar.retrofit.ApiService;
import com.example.nikola.soccerjar.retrofit.models.CompetitionResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerViewMain;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        recyclerViewMain.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewMain.setLayoutManager(layoutManager);

        CompetitionTask task = new CompetitionTask();
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        task.execute();
    }

    public class CompetitionTask extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {

            ApiManager.getClient().create(ApiService.class).getCompetitions().enqueue(new Callback<List<CompetitionResponse>>() {
                @Override
                public void onResponse(Call<List<CompetitionResponse>> call, Response<List<CompetitionResponse>> response) {
                    if (response.isSuccessful()) {
                        List<CompetitionResponse> competitionListResponse = response.body();
                        final List<CompetitionResponse> responseList = new ArrayList();
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
                                    final CompetitionsAdapter competitionsAdapter = new CompetitionsAdapter(responseList, MainActivity.this);
                                    recyclerViewMain.setAdapter(competitionsAdapter);
                                }
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<CompetitionResponse>> call, Throwable t) {
                    t.printStackTrace();
                }
            });

            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
        }
    }
}






