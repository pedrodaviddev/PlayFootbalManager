package com.pedrodavidlp.footballmanager.data;

import android.content.Context;

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
    public void change() {
        reference.child(context.getString(R.string.branch_groups)).child(GroupRepository.currentGroup.getId())
                .child(context.getString(R.string.match)).child(UserRepository.currentNickname)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    leave();
                } else {
                    join();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void join() {
        reference.child(context.getString(R.string.branch_groups)).child(GroupRepository.currentGroup.getId())
                .child(context.getString(R.string.players)).child(UserRepository.currentNickname)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        reference.child(context.getString(R.string.branch_groups)).child(GroupRepository.currentGroup.getId())
                                .child(context.getString(R.string.match)).child(UserRepository.currentNickname)
                                .setValue(dataSnapshot.getValue(Player.class));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void leave() {
        reference.child(context.getString(R.string.branch_groups)).child(GroupRepository.currentGroup.getId())
                .child(context.getString(R.string.match)).child(UserRepository.currentNickname).removeValue();
    }

}
