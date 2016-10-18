package com.pedrodavidlp.footballmanager.data;


import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.domain.interactor.CreateUserUseCase;
import com.pedrodavidlp.footballmanager.domain.repository.UserRepo;

public class UserRepository implements UserRepo {
    public static String currentNickname;
    private Context context;

    public UserRepository(Context context) {
        this.context = context;
    }

    @Override
    public void add(String nickname) {
    }

    @Override
    public void add(final String s, final CreateUserUseCase.Callback callback) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference reference = firebaseDatabase.getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
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
}
