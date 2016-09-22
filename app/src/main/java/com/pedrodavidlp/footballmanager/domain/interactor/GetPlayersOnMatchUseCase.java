package com.pedrodavidlp.footballmanager.domain.interactor;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.Interactor;
import com.tonilopezmr.interactorexecutor.MainThread;

import java.util.ArrayList;
import java.util.List;

public class GetPlayersOnMatchUseCase implements Interactor {
    public interface Callback{
        void onPlayersLoaded(List<Player> list);
        void onError(Exception e);
    }

    private Callback callback;
    private MainThread mainThread;
    private Executor executor;

    public GetPlayersOnMatchUseCase(MainThread mainThread, Executor executor) {
        this.mainThread = mainThread;
        this.executor = executor;
    }

    @Override
    public void run() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference();
        reference.child("message").child("match").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Player> res = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    res.add(data.getValue(Player.class));
                }
                callback.onPlayersLoaded(res);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(new Exception(databaseError.getMessage()));
            }
        });
    }

    public void execute(final Callback callback){
        if(callback == null){
            throw new IllegalArgumentException("CALLBACK CANT BE NULL");
        }
        this.callback = callback;
        this.executor.run(this);
    }

}
