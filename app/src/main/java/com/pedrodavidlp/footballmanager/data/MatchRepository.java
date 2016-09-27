package com.pedrodavidlp.footballmanager.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.MatchRepo;

public class MatchRepository implements MatchRepo {
    private Context context;

    public MatchRepository(Context context) {
        this.context = context;
    }

    @Override
    public void join() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference reference = firebaseDatabase.getReference();
        final SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.preferences_group),Context.MODE_PRIVATE);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        reference.child("player").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reference.child(context.getString(R.string.branch_groups))
                        .child(preferences.getString(context.getString(R.string.current_group),null))
                        .child("match").child(user.getUid()).setValue((dataSnapshot.getValue(Player.class)).getNickname());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private Object getActualUser() {
        return null;
    }
}
