package com.example.nikola.soccerjar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.nikola.soccerjar.adapter.CompetitionsAdapter;
import com.example.nikola.soccerjar.model.Results;
import com.example.nikola.soccerjar.retrofit.ApiManager;
import com.example.nikola.soccerjar.retrofit.ApiService;
import com.example.nikola.soccerjar.retrofit.Competition;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    ArrayList<Results> resultsParcelableArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.name);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        ApiManager.getClient().create(ApiService.class).getCompetitions().enqueue(new Callback<List<Competition>>() {
            @Override
            public void onResponse(Call<List<Competition>> call, Response<List<Competition>> response) {
                if (response.isSuccessful()) {
                    List<Competition> competitionList = response.body();
                    CompetitionsAdapter adapter = new CompetitionsAdapter(competitionList, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Competition>> call, Throwable t) {
                Log.d("s", "s");
            }
        });


//        ApiManager.getClient().create(ApiService.class).getCompetitions().enqueue(new Callback<JsonArray>() {
//            @Override
//            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
//                JsonArray array = response.body();
//                Competition competition = new Competition();
//                array.
//            }
//
//            @Override
//            public void onFailure(Call<JsonArray> call, Throwable t) {
//
//            }
//        });


//    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//        Competition competition = new Competition();
//        JsonObject object = response.body();
//        object.getAsJsonObject(competition.getCaption());

//        ImgurApiManager.getClient().create(ApiInterface.class).getImages(Constants.ALBUM_NAME)
//                .enqueue(new Callback<ResponseData>() {
//                    @Override
//                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
//                        dataCallback.onLoadFinished(response.body().album.images);
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseData> call, Throwable t) {
//                        Timber.e(t.getMessage());
//                    }
//                });
    }

//    private class CompetitionsClass extends CompetitionsParserTask {
//        @Override
//        protected void onPostExecute(ArrayList<Results> parcelables) {
//            if (parcelables != null) {
//                resultsParcelableArrayList.clear();
//                for (Results resultsParcelable : parcelables) {
//                    resultsParcelableArrayList.add(resultsParcelable);
//                }
//                updateDisplay();
//            }
//        }
//    }

//    private void updateDisplay() {
//        RecyclerView.Adapter adapter = new CompetitionsAdapter(resultsParcelableArrayList, this);
//        recyclerView.setAdapter(adapter);
//    }

}
