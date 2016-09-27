package com.pedrodavidlp.footballmanager.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.GroupRepo;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository implements GroupRepo {
    public static Group currentGroup;
    private Context context;

    public GroupRepository(Context context) {
        this.context = context;
    }

    @Override
    public void join() {

    }

    @Override
    public void create(Group group, Player creator) {
        currentGroup = group;
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference();

        List<String> groupsID = new ArrayList<>();
        groupsID.add(group.getId());
        reference.child(context.getString(R.string.branch_user)).child(user.getUid()).child("nickname").setValue(creator.getNickname());
        reference.child(context.getString(R.string.branch_user)).child(user.getUid())
                .child(context.getString(R.string.groups)).setValue(groupsID);
        reference.child("group").child(group.getId()).child("password").setValue(group.getPassword());
        reference.child(context.getString(R.string.branch_groups)).child(group.getId()).setValue(group);
        reference.child(context.getString(R.string.branch_groups)).child(group.getId())
                .child(context.getString(R.string.players)).child(creator.getNickname()).setValue(creator);


    }

    @Override
    public void add(Group group) {

    }
}
