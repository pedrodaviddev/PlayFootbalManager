package com.pedrodavidlp.footballmanager.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.MatchRepo;

public class MatchRepository implements MatchRepo {

    @Override
    public void join() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference();
        reference = reference.child("message").child("match");
        reference.setValue(getActualUser());

    }

    private Object getActualUser() {
        return null;
    }
}
