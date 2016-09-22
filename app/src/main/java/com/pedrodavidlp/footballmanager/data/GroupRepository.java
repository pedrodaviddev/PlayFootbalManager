package com.pedrodavidlp.footballmanager.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.GroupRepo;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository implements GroupRepo {
    @Override
    public void join() {

    }

    @Override
    public void add(Group group) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference();
        List<Player> players = new ArrayList<>();
        players.add(new Player("hola",0,false,false));
        group.setPlayers(players);
        reference.child(group.getId()).child("password").setValue(group.getPassword());
        reference.child(group.getId()).child("players").setValue(group.getPlayers());
    }
}
