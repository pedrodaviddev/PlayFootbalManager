package com.pedrodavidlp.footballmanager.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.domain.model.Player;

import java.util.ArrayList;
import java.util.List;

public class LauncherActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        View view = findViewById(R.id.trial);
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        if(info !=null && info.isConnected()){
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser user = auth.getCurrentUser();
            if (user == null){
                intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            } else {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference();
                reference.child("player").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> groups = new ArrayList<>();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            groups.add(data.getValue(String.class));
                        }

                        if (groups.size() != 0) {
                            intent = new Intent(getApplicationContext(), MainActivity.class);
                        } else {
                            intent = new Intent(getApplicationContext(),JoinGroupActivity.class);
                        }
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        } else {
            Snackbar.make(view.getRootView(),"No hay conexion a internet",Snackbar.LENGTH_INDEFINITE).show();
        }

    }
}
