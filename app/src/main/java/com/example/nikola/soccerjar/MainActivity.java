package com.example.nikola.soccerjar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nikola.soccerjar.adapter.CompetitionsAdapter;
import com.example.nikola.soccerjar.model.Results;
import com.example.nikola.soccerjar.parser.CompetitionsParserTask;

import java.util.ArrayList;

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
        new CompetitionsClass().execute();
    }

    private class CompetitionsClass extends CompetitionsParserTask {
        @Override
        protected void onPostExecute(ArrayList<Results> parcelables) {
            if (parcelables != null) {
                resultsParcelableArrayList.clear();
                for (Results resultsParcelable : parcelables) {
                    resultsParcelableArrayList.add(resultsParcelable);
                }
                updateDisplay();
            }
        }
    }

    private void updateDisplay() {
        RecyclerView.Adapter adapter = new CompetitionsAdapter(resultsParcelableArrayList, this);
        recyclerView.setAdapter(adapter);
    }

}
