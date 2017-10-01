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


public class LeagueTablesAdapter extends RecyclerView.Adapter<LeagueTablesAdapter.ViewHolder> {

    private List<Team> teamList;

    public LeagueTablesAdapter(List<Team> objects) {
        teamList = objects;
    }

    @Override
    public LeagueTablesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.my_detail_view, parent, false);

        return new LeagueTablesAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(final LeagueTablesAdapter.ViewHolder holder, int position) {

        final Team resultsParcelable = teamList.get(position);
        TextView teamName = holder.txtTeamName;
        TextView pos = holder.txtPosition;
        TextView pnts = holder.txtPoints;
        TextView pld = holder.txtPlayed;
        TextView gd = holder.txtGDiff;
        teamName.setText(resultsParcelable.getTeamName());
        pos.setText(resultsParcelable.getPosition());
        pnts.setText(resultsParcelable.getPoints());
        pld.setText(resultsParcelable.getPlayed());
        gd.setText(resultsParcelable.getGdiff());


    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTeamName;
        public TextView txtPosition;
        public TextView txtPoints;
        public TextView txtPlayed;
        public TextView txtGDiff;
        public TextView itmSeparator;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTeamName = (TextView) itemView.findViewById(R.id.txt_teamName);
            txtPosition = (TextView) itemView.findViewById(R.id.txt_position);
            txtPoints = (TextView) itemView.findViewById(R.id.txt_points);
            txtPlayed = (TextView) itemView.findViewById(R.id.txt_played);
            txtGDiff = (TextView) itemView.findViewById(R.id.txt_gdiff);
            itmSeparator = (TextView) itemView.findViewById(R.id.separatorView);
        }
    }


}































