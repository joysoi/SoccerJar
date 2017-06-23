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

import java.util.List;

/**
 * Created by Nikola on 6/22/2017.
 */

public class SoccerAdapter extends RecyclerView.Adapter<SoccerAdapter.ViewHolder> {


    private List<Results> modelList;
    public Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView text_caption;
//        public TextView text_league;
       public Button button_view;

        public ViewHolder(View itemView) {
            super(itemView);
//            text_caption = (TextView) itemView.findViewById(R.id.caption_txt);
//            text_league = (TextView) itemView.findViewById(R.id.league_txt);
            button_view = (Button) itemView.findViewById(R.id.btn);
        }
    }

    public SoccerAdapter(Context context, List<Results> objects) {
        mContext = context;
        modelList = objects;
    }

    @Override
    public SoccerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.my_txt_views, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Results results = modelList.get(position);
//        TextView caption = holder.text_caption;
//        caption.setText(results.getCaption());
//        TextView league = holder.text_league;
//        league.setText(results.getLeague());
        Button caption = holder.button_view;
        caption.setText(results.getCaption());
        caption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("id", results.getId_());
                mContext.startActivity(intent);
            }
        });

//        Button league = holder.button_view;
//        league.setText(results.getLeague());
//        league.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, DetailsActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}































