package com.example.nikola.soccerjar.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.nikola.soccerjar.R;
import com.example.nikola.soccerjar.adapter.FixturesAdapter;
import com.example.nikola.soccerjar.retrofit.models.Team;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//Passive View:
public class FixturesFragment extends Fragment implements FixturesView {

    @BindView(R.id.showResultsBTN)
    Button btnMoreResults;
    @BindView(R.id.recyclerFixturesView)
    RecyclerView recyclerViewFixtures;
    ProgressDialog progressDialog;
    FixturesAdapter fixturesAdapter;
    FixturesPresenter presenter;

    public static FixturesFragment newInstance(int id) {
        FixturesFragment f = new FixturesFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.registerView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unRegisterView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.my_fixtures_detail_view, container, false);
        ButterKnife.bind(this, view);
        recyclerViewFixtures.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewFixtures.setLayoutManager(layoutManager);
        fixturesAdapter = new FixturesAdapter();
        recyclerViewFixtures.setAdapter(fixturesAdapter);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        presenter = new FixturesPresenter();
        if (!getArguments().isEmpty()) {
            int id = getArguments().getInt("id");
            presenter.getTeamNames(id);
        }
        return view;
    }

    @Override
    public void showTeamsWithScheduledStatus(List<Team> fixtureList) {
        fixturesAdapter.updateList(fixtureList);
    }

    @Override
    public void showAllTeams(List<Team> fixtureList) {
        fixturesAdapter.updateList(fixtureList);
    }

    @Override
    public void unsucessfulResponse() {
        Toast.makeText(getActivity(), "No list presented...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @OnClick(R.id.showResultsBTN)
    public void onShowResultsClick(){
        presenter.getPreviousGames();
    }
}

