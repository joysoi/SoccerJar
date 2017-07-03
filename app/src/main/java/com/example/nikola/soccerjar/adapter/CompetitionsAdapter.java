package com.example.nikola.soccerjar.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nikola.soccerjar.DetailsActivity;
import com.example.nikola.soccerjar.R;
import com.example.nikola.soccerjar.model.Results;

import java.util.ArrayList;

/**
 * Created by Nikola on 6/26/2017.
 */

public class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionsAdapter.ViewHolder> {

    private ArrayList<Results> modelList;
    private Context mContext;

    public CompetitionsAdapter(ArrayList<Results> objects, Context context) {
        modelList = objects;
        mContext = context;
    }

    @Override
    public CompetitionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.my_main_view, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Results resultsParcelable = modelList.get(position);
        Button caption = holder.btn_team_name;
        caption.setText(resultsParcelable.getCaption());
        caption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 7/3/2017 callbacks implement
                String id = resultsParcelable.getId_();
                Intent i = new Intent(mContext, DetailsActivity.class);
                i.putExtra("id", id);
                mContext.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public Button btn_team_name;


        public ViewHolder(View itemView) {
            super(itemView);
            btn_team_name = (Button) itemView.findViewById(R.id.btn);
        }
    }


}

