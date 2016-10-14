package com.pedrodavidlp.footballmanager.domain.interactor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.data.GroupRepository;
import com.pedrodavidlp.footballmanager.data.UserRepository;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.PlayersRepo;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.Interactor;
import com.tonilopezmr.interactorexecutor.MainThread;

import java.util.ArrayList;
import java.util.List;

public class SelectStateUseCase implements Interactor{
    private final String TAG = getClass().getSimpleName();
    public static final int NO_CONNECTION = 0;
    public static final int NOT_LOGGED = 1;
    public static final int NO_NICKNAME = 2;
    public static final int NO_GROUP = 3;
    public static final int NORMAL_USER = 4;


    public interface Callback{
        void goToState(int state);
        void error(Exception e);
    }


    private Context context;
    private Callback callback;
    private MainThread mainThread;
    private Executor executor;

    public SelectStateUseCase(Context context,MainThread mainThread, Executor executor) {
        this.context = context;
        this.mainThread = mainThread;
        this.executor = executor;

    }

    @Override
    public void run() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info == null || !info.isConnected()){
            callback.goToState(NO_CONNECTION);
        } else {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser user = auth.getCurrentUser();
            if(user == null){
                callback.goToState(NOT_LOGGED);
            } else {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference();
                reference.child(context.getString(R.string.branch_user))
                        .child(user.getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    List<String> groups = new ArrayList<>();
                                    if (dataSnapshot.child(context.getString(R.string.nickname)).getValue() == null){
                                        callback.goToState(NO_NICKNAME);
                                    } else {
                                        for (DataSnapshot data : dataSnapshot.child(context.getString(R.string.groups)).getChildren()) {
                                            groups.add(data.getValue(String.class));
                                        }
                                        if (groups.size()>0){
                                            GroupRepository.currentGroup=new Group(groups.get(0),"");
                                            UserRepository.currentNickname=dataSnapshot.child(context.getString(R.string.nickname)).getValue(String.class);
                                            callback.goToState(NORMAL_USER);
                                        } else {
                                            UserRepository.currentNickname=dataSnapshot.child(context.getString(R.string.nickname)).getValue(String.class);
                                            callback.goToState(NO_GROUP);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

            }

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
