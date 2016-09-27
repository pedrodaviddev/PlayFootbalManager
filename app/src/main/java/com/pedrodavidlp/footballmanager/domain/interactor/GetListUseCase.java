package com.pedrodavidlp.footballmanager.domain.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.auth.api.model.StringList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.data.GroupRepository;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.Interactor;
import com.tonilopezmr.interactorexecutor.MainThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PedroDavidLP on 15/9/16.
 */
public class GetListUseCase implements Interactor{
    private Context context;

    private final String TAG = getClass().getSimpleName();

    public interface Callback{
        void onListLoaded(List<Player> list);
        void onError(Exception e);
    }

    private Callback callback;
    private MainThread mainThread;
    private Executor executor;

    public GetListUseCase(MainThread mainThread, Executor executor, Context context) {
        this.mainThread = mainThread;
        this.executor = executor;
        this.context = context;
    }

    @Override
    public void run() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference reference = firebaseDatabase.getReference();

        reference.child(context.getString(R.string.branch_groups)).child(GroupRepository.currentGroup.getId())
                .child("players").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Player> res = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    res.add(data.getValue(Player.class));
                }

                callback.onListLoaded(res);
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
