package com.pedrodavidlp.footballmanager.data;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.domain.interactor.CreateGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.JoinGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.GroupRepo;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository implements GroupRepo {
    private final String TAG = getClass().getSimpleName();
    public static Group currentGroup;
    private Context context;

    public GroupRepository(Context context) {
        this.context = context;
    }

    @Override
    public void join(final Group group, final Player toJoin, final JoinGroupUseCase.Callback callback) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference reference = firebaseDatabase.getReference();
        reference.child(context.getString(R.string.branch_groups))
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(group.getId()).exists()){
                    Group onDatabase = dataSnapshot.child(group.getId()).getValue(Group.class);
                    if(onDatabase.getPassword().equals(group.getPassword())){
                        Log.d(TAG, "onDataChange: "+onDatabase.getPassword()+" : "+ group.getPassword());
                        joinGruop(group,toJoin,callback);
                    } else{
                        callback.wrongPassword();
                    }
                } else {
                    callback.groupNotExist();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        joinGruop(group, toJoin, callback);
    }

    private void joinGruop(Group group, Player toJoin, JoinGroupUseCase.Callback callback) {
        currentGroup = group;
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference();
        List<String> groupsID = new ArrayList<>();
        groupsID.add(group.getId());
        reference.child(context.getString(R.string.branch_user)).child(user.getUid()).child("nickname").setValue(toJoin.getNickname());
        reference.child(context.getString(R.string.branch_nickname)).child(toJoin.getNickname()).setValue(toJoin.getSkill());
        reference.child(context.getString(R.string.branch_user)).child(user.getUid())
                .child(context.getString(R.string.groups)).setValue(groupsID);
        reference.child(context.getString(R.string.branch_groups)).child(group.getId())
                .child(context.getString(R.string.players)).child(toJoin.getNickname()).setValue(toJoin);
        callback.onSuccesfulJoin();
    }

    @Override
    public void create(Group group, Player creator, CreateGroupUseCase.Callback callback) {
        checkIfExist(group,creator,callback);
    }

    private void checkIfExist(final Group group, final Player creator, final CreateGroupUseCase.Callback callback) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference();
        reference.child(context.getString(R.string.branch_groups)).child(group.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: "+dataSnapshot+" "+dataSnapshot.exists()+dataSnapshot.getChildren());
                if (dataSnapshot.exists()){
                   callback.nameTaken();
                } else {
                    createGroup(group,creator,callback);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void createGroup(Group group, Player creator, CreateGroupUseCase.Callback callback){
        currentGroup = group;
        UserRepository.currentNickname=creator.getNickname();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference();


        List<String> groupsID = new ArrayList<>();
        groupsID.add(group.getId());
        reference.child(context.getString(R.string.branch_user)).child(user.getUid())
                .child(context.getString(R.string.groups)).setValue(groupsID);
        reference.child(context.getString(R.string.branch_user)).child(user.getUid())
                .child(context.getString(R.string.current_group)).setValue(group.getId());
        Log.d(TAG, "createGroup: "+creator.getNickname()+" "+creator+context.getString(R.string.branch_nickname));
        reference.child(context.getString(R.string.branch_nickname)).child(creator.getNickname()).setValue(creator.getSkill());
        reference.child("group").child(group.getId()).child("password").setValue(group.getPassword());
        reference.child(context.getString(R.string.branch_groups)).child(group.getId()).setValue(group);
        reference.child(context.getString(R.string.branch_groups)).child(group.getId())
                .child(context.getString(R.string.players)).child(creator.getNickname()).setValue(creator);
        callback.onSuccesfulCreated();
    }

    @Override
    public void add(Group group) {

    }
}
