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
import com.pedrodavidlp.footballmanager.domain.interactor.SelectStateUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.presenter.LauncherPresenter;
import com.pedrodavidlp.footballmanager.view.ViewMode;
import com.pedrodavidlp.footballmanager.view.executor.MainThreadImp;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;
import com.tonilopezmr.interactorexecutor.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;

public class LauncherActivity extends AppCompatActivity implements ViewMode {
    private LauncherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        MainThread mainThread = new MainThreadImp();
        Executor executor = new ThreadExecutor();
        SelectStateUseCase stateUseCase = new SelectStateUseCase(getApplicationContext(),mainThread,executor);
        presenter = new LauncherPresenter(stateUseCase);
        presenter.setView(this);
        presenter.init();

    }

    @Override
    public void initUi(int mode) {
        Intent intent=null;
        switch (mode){
            case SelectStateUseCase.NO_CONNECTION:
                Snackbar.make(getWindow().getCurrentFocus(),"No hay conexion a internet",Snackbar.LENGTH_INDEFINITE).show();
                return;
            case SelectStateUseCase.NOT_LOGGED:
                intent = new Intent(getApplicationContext(),LoginActivity.class);
                break;
            case SelectStateUseCase.NO_GROUP:
                intent = new Intent(getApplicationContext(),JoinGroupActivity.class);
                break;
            case SelectStateUseCase.NORMAL_USER:
                intent = new Intent(getApplicationContext(),MainActivity.class);
                break;
        }
        startActivity(intent);
        finish();

    }

    @Override
    public void initUi() {

    }

    @Override
    public void error(Exception e) {

    }
}