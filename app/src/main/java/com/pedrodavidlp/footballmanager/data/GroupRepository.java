package com.pedrodavidlp.footballmanager.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.GroupRepo;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository implements GroupRepo {
    public static Group currentGroup;
    @Override
    public void join() {

    }

    @Override
    public void add(Group group) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference();
        reference.child("group").child(group.getId()).child("password").setValue(group.getPassword());
        for (Player player : group.getPlayers()) {
            reference.child("group").child(group.getId()).child("players").setValue(player.getNickname());
        }

    }
}
