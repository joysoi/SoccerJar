package com.example.nikola.soccerjar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikola.soccerjar.R;
import com.example.nikola.soccerjar.model.Results;

import java.util.ArrayList;

/**
 * Created by Nikola on 6/22/2017.
 */

public class LeagueTablesAdapter extends RecyclerView.Adapter<LeagueTablesAdapter.ViewHolder> {

    private ArrayList<Results> modelList;


    public LeagueTablesAdapter(ArrayList<Results> objects) {
        modelList = objects;
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

        final Results resultsParcelable = modelList.get(position);
        TextView teamName = holder.txtTeamName;
        teamName.setText(resultsParcelable.getTeamName());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTeamName;


        public ViewHolder(View itemView) {
            super(itemView);
            txtTeamName = (TextView) itemView.findViewById(R.id.txt_teamName);
        }
    }


}































