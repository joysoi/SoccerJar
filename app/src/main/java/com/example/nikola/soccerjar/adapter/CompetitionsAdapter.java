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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.nikola.soccerjar.DetailsActivity.CAPTION_KEY;
import static com.example.nikola.soccerjar.DetailsActivity.ID_KEY;


public class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionsAdapter.ViewHolder> {
    private List<CompetitionResponse> competitionListResponse = new ArrayList<>();
    private Context context;

    public void getCompetitions(Context context, List<CompetitionResponse> competitionResponses) {
        this.context = context;
        this.competitionListResponse.clear();
        this.competitionListResponse.addAll(competitionResponses);
        notifyDataSetChanged();
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
                Intent i = new Intent(context, DetailsActivity.class);
                i.putExtra(ID_KEY, id);
                i.putExtra(CAPTION_KEY, cap);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return competitionListResponse.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_view)
        CardView crd_team_name;
        @BindView(R.id.txt_leagues)
        TextView txt_team_name;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

