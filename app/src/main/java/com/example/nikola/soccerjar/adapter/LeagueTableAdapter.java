package com.example.nikola.soccerjar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nikola.soccerjar.R;
import com.example.nikola.soccerjar.model.Results;

import java.util.List;

/**
 * Created by Nikola on 6/26/2017.
 */

public class LeagueTableAdapter extends RecyclerView.Adapter<LeagueTableAdapter.ViewHolder> {

    private List<Results> modelList;
    public Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public Button btn_team_name;


        public ViewHolder(View itemView) {
            super(itemView);
//            btn_team_name = (Button) itemView.findViewById(R.id.btn_team);
        }
    }

    public LeagueTableAdapter(Context context, List<Results> objects) {
        mContext = context;
        modelList = objects;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.my_main_view, parent, false);

        return new LeagueTableAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Results results = modelList.get(position);
        Button caption = holder.btn_team_name;
        caption.setText(results.getCaption());

    }


    @Override
    public int getItemCount() {
        return modelList.size();
    }

}
