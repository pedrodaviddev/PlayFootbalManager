package com.pedrodavidlp.footballmanager.data;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.domain.interactor.GetPlayersOnMatchUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.MatchRepo;

import java.util.ArrayList;
import java.util.List;

import static com.pedrodavidlp.footballmanager.R.string.branch_groups;

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
        reference.child(context.getString(branch_groups)).child(GroupRepository.currentGroup.getId())
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
    public void loadListMatch(final GetPlayersOnMatchUseCase.Callback callback) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference();
        reference.child(context.getString(branch_groups)).child(GroupRepository.currentGroup.getId()).child("match")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Player> res = new ArrayList<>();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            res.add(data.getValue(Player.class));
                        }
                        callback.onListMatchLoaded(res);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        callback.onError(new Exception(databaseError.getMessage()));
                    }
                });
    }


    @Override
    public void join() {
        reference.child(context.getString(branch_groups)).child(GroupRepository.currentGroup.getId())
                .child(context.getString(R.string.players)).child(UserRepository.currentNickname)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        reference.child(context.getString(branch_groups)).child(GroupRepository.currentGroup.getId())
                                .child(context.getString(R.string.match)).child(UserRepository.currentNickname)
                                .setValue(dataSnapshot.getValue(Player.class));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void leave() {
        reference.child(context.getString(branch_groups)).child(GroupRepository.currentGroup.getId())
                .child(context.getString(R.string.match)).child(UserRepository.currentNickname).removeValue();
    }

}
