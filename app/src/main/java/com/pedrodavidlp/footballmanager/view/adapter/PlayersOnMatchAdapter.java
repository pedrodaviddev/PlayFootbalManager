package com.pedrodavidlp.footballmanager.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.domain.model.Player;

import java.util.ArrayList;
import java.util.List;


public class PlayersOnMatchAdapter extends RecyclerView.Adapter<PlayersOnMatchAdapter.PlayersOnMatchHolder> {
    private List<Player> players;

    public PlayersOnMatchAdapter() {
        this.players = new ArrayList<>();
    }

    public void setData(List<Player> list) {
        players = list;
    }

    public static class PlayersOnMatchHolder extends RecyclerView.ViewHolder{
        private TextView name;
        public PlayersOnMatchHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.nameOfPlayerInMatch);
        }

        public void setName(String name) {
            this.name.setText(name);
        }
    }

    @Override
    public PlayersOnMatchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_players_on_match,parent,false);
        PlayersOnMatchHolder holder=new PlayersOnMatchHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PlayersOnMatchHolder holder, int position) {
        holder.setName(players.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return players == null ? 0 : players.size();
    }
}
