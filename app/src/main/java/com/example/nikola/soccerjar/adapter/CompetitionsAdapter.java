package com.example.nikola.soccerjar.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikola.soccerjar.DetailsActivity;
import com.example.nikola.soccerjar.R;
import com.example.nikola.soccerjar.retrofit.models.CompetitionResponse;

import java.util.List;

public class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionsAdapter.ViewHolder> {

    private List<CompetitionResponse> competitionListResponse;
    private Context mContext;

    public CompetitionsAdapter(List<CompetitionResponse> list, Context context) {
        competitionListResponse = list;
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

        final CompetitionResponse resultsParcelable = competitionListResponse.get(position);
        TextView caption = holder.txt_team_name;
        caption.setText(resultsParcelable.getCaption());
        caption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = resultsParcelable.get_id();
                String cap = resultsParcelable.getCaption();
                Intent i = new Intent(mContext, DetailsActivity.class);
                i.putExtra("id", id);
                i.putExtra("caption", cap);
                mContext.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return competitionListResponse.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView crd_team_name;
        public TextView txt_team_name;


        public ViewHolder(View itemView) {
            super(itemView);
            crd_team_name = (CardView) itemView.findViewById(R.id.card_view);
            txt_team_name = (TextView) itemView.findViewById(R.id.txt_leagues);
        }
    }


}

