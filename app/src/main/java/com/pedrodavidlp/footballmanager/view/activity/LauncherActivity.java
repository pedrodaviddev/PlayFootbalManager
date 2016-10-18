package com.pedrodavidlp.footballmanager.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.animation.AnimationUtils;

import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.domain.interactor.SelectStateUseCase;
import com.pedrodavidlp.footballmanager.presenter.LauncherPresenter;
import com.pedrodavidlp.footballmanager.view.ViewMode;
import com.pedrodavidlp.footballmanager.view.executor.MainThreadImp;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;
import com.tonilopezmr.interactorexecutor.ThreadExecutor;

public class LauncherActivity extends AppCompatActivity implements ViewMode {
    private LauncherPresenter presenter;
    private AppCompatImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        initDagger();

        imageView = (AppCompatImageView) findViewById(R.id.image_loading);
        imageView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate));
        MainThread mainThread = new MainThreadImp();
        Executor executor = new ThreadExecutor();
        SelectStateUseCase stateUseCase = new SelectStateUseCase(getApplicationContext(),mainThread,executor);
        presenter = new LauncherPresenter(stateUseCase);
        presenter.setView(this);
        presenter.init();

    }

    private void initDagger() {

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
            case SelectStateUseCase.NO_NICKNAME:
                intent = new Intent(getApplicationContext(),JoinGroupActivity.class);
                intent.putExtra("fragment",0);
                break;
            case SelectStateUseCase.NO_GROUP:
                intent = new Intent(getApplicationContext(),JoinGroupActivity.class);
                intent.putExtra("fragment",1);
                break;
            case SelectStateUseCase.NORMAL_USER:
                intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("admin",false);
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