package com.pedrodavidlp.footballmanager.domain.interactor;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.Interactor;
import com.tonilopezmr.interactorexecutor.MainThread;

public class GetCurrentPlayerUseCase implements Interactor {
    private final String TAG = getClass().getSimpleName();
    public interface Callback{
        void onPlayerFound(Player player);
        void onError(Exception e);
    }

    private Callback callback;
    private MainThread mainThread;
    private Executor executor;

    public GetCurrentPlayerUseCase(MainThread mainThread, Executor executor) {
        this.mainThread = mainThread;
        this.executor = executor;
    }

    @Override
    public void run() {
        try {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            final FirebaseUser user = auth.getCurrentUser();
            final FirebaseDatabase database =FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference();
            Log.d(TAG, "run: "+user);
            Log.d(TAG, "run: "+user.getUid());
            Log.d(TAG, "run: "+user.getDisplayName());
            reference.child("player").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Player player = dataSnapshot.getValue(Player.class);
                    Log.d(TAG, "onDataChange: "+player+" "+player.getName()+" "+user+ " "+ user.getDisplayName());
                    player.setName(user.getDisplayName());
                    callback.onPlayerFound(player);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    callback.onError(new Exception(databaseError.getMessage()));
                }
            });
        } catch (Exception e) {
            callback.onError(e);
            e.printStackTrace();
        }

    }

    public void execute(final Callback callback){
        if(callback == null){
            throw new IllegalArgumentException("CALLBACK CANT BE NULL");
        }
        this.callback = callback;
        this.executor.run(this);
    }
}
