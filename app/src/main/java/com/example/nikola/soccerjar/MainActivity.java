package com.example.nikola.soccerjar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nikola.soccerjar.adapter.SoccerAdapter;
import com.example.nikola.soccerjar.http.HttpGetManager;
import com.example.nikola.soccerjar.model.Results;
import com.example.nikola.soccerjar.parser.SoccerParser;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://api.football-data.org/v1/competitions/";


    public RecyclerView recyclerView;
    private List<Results> modelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayContent();
        getSupportActionBar().setTitle(R.string.name);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    protected void displayContent() {
        requestData(URL);
    }

    private class MyTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            return HttpGetManager.getData(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            modelList = SoccerParser.parseFeed(result);
//            modelList = LeagueTableParser.parseFeed(result);
            updateDisplay();
        }
    }


    private void updateDisplay() {
        RecyclerView.Adapter adapter = new SoccerAdapter(this, modelList);
        recyclerView.setAdapter(adapter);
    }

    private void requestData(String uri) {
        new MyTask().execute(uri);
    }

}
