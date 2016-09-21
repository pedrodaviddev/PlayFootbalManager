package com.pedrodavidlp.footballmanager.view.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.view.fragment.ListPlayersFragment;

import java.util.ArrayList;
import java.util.List;

public class ListPlayersAdapter extends RecyclerView.Adapter<ListPlayersAdapter.ListPlayersHolder>{
    private List<Player> players;

    public ListPlayersAdapter() {
        this.players = new ArrayList<>();
    }

    public void setData(List<Player> list) {
        players = list;
    }

    public static class ListPlayersHolder extends RecyclerView.ViewHolder{
        private AppCompatImageView image;
        private TextView name;
        public ListPlayersHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.namePlayer);
            image = (AppCompatImageView) itemView.findViewById(R.id.imagePlayer);
            setImage();
        }

        public void setName(String name) {
            this.name.setText(name);
        }
        public void setImage() {
            switch ((int) (Math.random()*4+1)){
                case 1:
                    this.image.setImageResource(R.drawable.icon_player1);
                    break;
                case 2:

                    this.image.setImageResource(R.drawable.icon_player2);
                    break;
                case 3:

                    this.image.setImageResource(R.drawable.icon_player3);
                    break;
                default:

                    this.image.setImageResource(R.drawable.icon_player4);
                    break;
            }
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
