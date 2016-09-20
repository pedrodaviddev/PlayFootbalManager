package com.pedrodavidlp.footballmanager.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.view.fragment.ListPlayersFragment;

import java.util.List;

/**
 * Created by PedroDavidLP on 15/9/16.
 */
public class ListPlayersAdapter extends RecyclerView.Adapter<ListPlayersAdapter.ListPlayersHolder>{
    List<Player> players;

    public void setData(List<Player> list) {
        players = list;
    }

    public static class ListPlayersHolder extends RecyclerView.ViewHolder{
        private TextView name;
        public ListPlayersHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.namePlayer);
        }

        public void setName(String name) {
            this.name.setText(name);
        }
    }

    @Override
    public ListPlayersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_list_players,parent,false);
        ListPlayersHolder holder=new ListPlayersHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListPlayersHolder holder, int position) {
        holder.setName(players.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return players == null ? 0 : players.size();
    }
}
