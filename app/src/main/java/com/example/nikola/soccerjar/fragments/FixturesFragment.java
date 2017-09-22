package com.example.nikola.soccerjar.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nikola.soccerjar.R;

/**
 * Created by Nikola on 9/18/2017.
 */

public class FixturesFragment extends Fragment {

    private Button btnMoreResults;
    private Button btnMoreScores;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.my_fixtures_detail_view,container,false);

        btnMoreResults = (Button) view.findViewById(R.id.showResultsBTN);
        btnMoreScores = (Button) view.findViewById(R.id.showMoreBTN);

        return view;
    }
}
