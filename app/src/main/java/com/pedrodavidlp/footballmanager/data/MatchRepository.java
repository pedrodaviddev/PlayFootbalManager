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
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        reference.child(context.getString(R.string.branch_user)).child(user.getUid()).child("nickname")
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                findPlayer(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void findPlayer(final String nick) {
        reference.child(context.getString(R.string.branch_groups)).child(GroupRepository.currentGroup.getId())
                .child(context.getString(R.string.players)).child(nick)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        reference.child(context.getString(R.string.branch_groups)).child(GroupRepository.currentGroup.getId())
                                .child(context.getString(R.string.match)).child(nick).setValue(dataSnapshot.getValue(Player.class));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

}
