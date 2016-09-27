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
    private DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;

    public MatchRepository(Context context) {
        this.context = context;
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
    }

    @Override
    public void join() {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.preferences_user),Context.MODE_PRIVATE);
        reference.child(context.getString(R.string.branch_player)).child(context.getString(R.string.groups))
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                join(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    private void join(final String nickname){
        final SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.preferences_group),Context.MODE_PRIVATE);
        reference.child(context.getString(R.string.branch_player)).child(nickname)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reference.child(context.getString(R.string.branch_groups))
                        .child(preferences.getString(context.getString(R.string.current_group),null))
                        .child(context.getString(R.string.match)).child(nickname).setValue(dataSnapshot.getValue(Player.class));
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
