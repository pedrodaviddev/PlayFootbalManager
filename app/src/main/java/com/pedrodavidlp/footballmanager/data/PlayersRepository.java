package com.pedrodavidlp.footballmanager.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.PlayersRepo;

/**
 * Created by PedroDavidLP on 14/9/16.
 */
public class PlayersRepository implements PlayersRepo {
    FirebaseDatabase database;
    DatabaseReference reference;

    public PlayersRepository() {
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("message");
    }

    @Override
    public void add(Player player) {
    }

}
