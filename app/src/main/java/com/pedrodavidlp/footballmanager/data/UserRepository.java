package com.pedrodavidlp.footballmanager.data;


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
import com.pedrodavidlp.footballmanager.domain.interactor.CreateUserUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.SelectStateUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

import static com.pedrodavidlp.footballmanager.R.string.branch_groups;
import static com.pedrodavidlp.footballmanager.R.string.players;
import static com.pedrodavidlp.footballmanager.domain.interactor.SelectStateUseCase.NOT_LOGGED;
import static com.pedrodavidlp.footballmanager.domain.interactor.SelectStateUseCase.NO_CONNECTION;
import static com.pedrodavidlp.footballmanager.domain.interactor.SelectStateUseCase.NO_GROUP;
import static com.pedrodavidlp.footballmanager.domain.interactor.SelectStateUseCase.NO_NICKNAME;

public class UserRepository implements UserRepo {
    public static String currentNickname;
    private Context context;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private final String TAG = getClass().getSimpleName();

    public UserRepository(Context context) {
        this.context = context;
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    @Override
    public void add(String nickname) {
    }

    @Override
    public void add(final String s, final CreateUserUseCase.Callback callback) {
        currentNickname = s;
        reference.child(context.getString(R.string.branch_nickname)).child(s).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            callback.nickTaken();
                        } else {
                            reference.child(context.getString(R.string.branch_nickname)).child(s).setValue(user.getUid());
                            reference.child(context.getString(R.string.branch_user)).child(user.getUid())
                                    .child(context.getString(R.string.nickname)).setValue(s);
                            callback.onSuccesfulCreated();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void selectState(final SelectStateUseCase.Callback callback) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        Log.d(TAG, "selectState: "+user);
        if(!checkConnection(info,callback)){
            if(user == null){
                callback.goToState(NOT_LOGGED);
            } else {
                getUserState(callback);
            }

        } else {
            callback.error(new Exception("NO HAY CONEXION A INTERNET"));
        }
    }

    private void getUserState(final SelectStateUseCase.Callback callback) {
        Log.d(TAG, "getUserState: aqui no llegasoque");
        reference.child(context.getString(R.string.branch_user))
                .child(user.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> groups = new ArrayList<>();
                        Log.d(TAG, "onDataChange: primer if " +(dataSnapshot.child(context.getString(R.string.nickname)).getValue() == null) + "");
                        if (dataSnapshot.child(context.getString(R.string.nickname)).getValue() == null){
                            callback.goToState(NO_NICKNAME);
                        } else {
                            for (DataSnapshot data : dataSnapshot.child(context.getString(R.string.groups)).getChildren()) {
                                groups.add(data.getValue(String.class));
                                Log.d(TAG, "onDataChange: grupos : "+groups.add(data.getValue(String.class)));
                            }
                            if (groups.size()>0){
                                Log.d(TAG, "onDataChange: ramaño"+groups.size());
                                GroupRepository.currentGroup=new Group(groups.get(0),"",0,0);
                                UserRepository.currentNickname=dataSnapshot.child(context.getString(R.string.nickname)).getValue(String.class);
                                reference.child(context.getString(branch_groups)).child(GroupRepository.currentGroup.getId())
                                        .child(context.getString(players)).child(UserRepository.currentNickname)
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                Player player = dataSnapshot.getValue(Player.class);
                                                if(player.isAdmin()){
                                                    callback.goToState(SelectStateUseCase.ADMIN_USER);
                                                } else {
                                                    callback.goToState(SelectStateUseCase.NORMAL_USER);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                            } else {
                                UserRepository.currentNickname=dataSnapshot.child(context.getString(R.string.nickname)).getValue(String.class);
                                callback.goToState(NO_GROUP);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d(TAG, "onCancelled: error database "+databaseError.getDetails()+" "+databaseError.getMessage());
                    }
                });
    }

    private boolean checkConnection(NetworkInfo info, SelectStateUseCase.Callback callback) {
        if(info == null || !info.isConnected()){
            callback.goToState(NO_CONNECTION);
        }
        return info == null || !info.isConnected();
    }
}
