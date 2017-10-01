package com.example.nikola.soccerjar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

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
    @BindView(R.id.progressbar)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        recyclerViewMain.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewMain.setLayoutManager(layoutManager);

        CompetitionTask task = new CompetitionTask();
        task.setProgressBar(progressBar);
        task.execute();
    }

    public class CompetitionTask extends AsyncTask<String, Integer, String> {

        ProgressBar bar;

        public void setProgressBar(ProgressBar bar){
            this.bar = bar;
        }

        @Override
        protected String doInBackground(String... params) {

            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1);
                    publishProgress(i);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }

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

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            bar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            bar.setVisibility(View.GONE);
        }
    }
}






