package com.example.nikola.soccerjar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikola.soccerjar.R;
import com.example.nikola.soccerjar.retrofit.models.Team;

import java.util.List;

public class FixturesAdapter extends RecyclerView.Adapter<FixturesAdapter.ViewHolder> {


    private List<Team> fixtureList;


    public FixturesAdapter(List<Team> list) {
        fixtureList = list;

    }


    @Override
    public FixturesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View contactView = layoutInflater.inflate(R.layout.my_fixtures_view, parent, false);

        return new FixturesAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Team teamList = fixtureList.get(position);


        TextView viewHome = holder.txtHome;
        TextView viewAway = holder.txtAway;
        TextView viewHomeScore = holder.txtHomeScore;
        TextView viewAwayScore = holder.txtAwayScore;

        viewHome.setText(teamList.getHomeTeamName());
        viewAway.setText(teamList.getAwayTeamName());
        viewHomeScore.setText(teamList.getResult().getGoalsHomeTeam() + "");
        viewAwayScore.setText(teamList.getResult().getGoalsAwayTeam() + "");



    }

    @Override
    public int getItemCount() {
        return fixtureList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtHome;
        TextView txtAway;
        TextView txtHomeScore;
        TextView txtAwayScore;


        ViewHolder(View itemView) {
            super(itemView);

            txtHome = (TextView) itemView.findViewById(R.id.txt_home);
            txtAway = (TextView) itemView.findViewById(R.id.txt_away);
            txtHomeScore = (TextView) itemView.findViewById(R.id.text_homeScore);
            txtAwayScore = (TextView) itemView.findViewById(R.id.text_awayScore);
        }
    }
}


