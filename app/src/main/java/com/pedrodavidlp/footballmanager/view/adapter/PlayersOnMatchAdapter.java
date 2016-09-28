package com.pedrodavidlp.footballmanager.view.adapter;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private Context context;

    public PlayersOnMatchAdapter(Context context) {
        this.players = new ArrayList<>();
        this.context = context;
    }

    public void setData(List<Player> list) {
        buildNotification(list,players);
        players = list;
    }

    private void buildNotification(List<Player> list, List<Player> players) {
        List<Player> aux=players;
        players.removeAll(list);
        NotificationCompat.Builder builder=null;
        builder = new NotificationCompat.Builder(context);
        if(players.size()>0){
            builder.setContentTitle("Se ha ido del partido :(");
            builder.setContentText(players.get(0).getNickname() + "se ha ido.");
        } else {
            list.removeAll(aux);
            if (list.size() > 0) {

                builder.setContentTitle("Se han unido al partidoooo");
                builder.setContentText(list.get(0).getNickname() + "se ha unido.");
            }
        }
        builder.setSmallIcon(R.drawable.icon_play);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(123123,builder.build());
    }

    public static class PlayersOnMatchHolder extends RecyclerView.ViewHolder{
        private TextView name;
        public PlayersOnMatchHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.namePlayerOnMatch);
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
        holder.setName(players.get(position).getNickname());
    }

    @Override
    public int getItemCount() {
        return players == null ? 0 : players.size();
    }
}
