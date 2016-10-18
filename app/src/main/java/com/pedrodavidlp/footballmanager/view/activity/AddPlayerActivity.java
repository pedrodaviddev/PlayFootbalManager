package com.pedrodavidlp.footballmanager.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.data.PlayersRepository;
import com.pedrodavidlp.footballmanager.domain.interactor.AddPlayerUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.PlayersRepo;
import com.pedrodavidlp.footballmanager.presenter.AddPlayerPresenter;
import com.pedrodavidlp.footballmanager.view.View;
import com.pedrodavidlp.footballmanager.view.executor.MainThreadImp;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;
import com.tonilopezmr.interactorexecutor.ThreadExecutor;

public class AddPlayerActivity extends AppCompatActivity implements View<Player> {
    TextInputEditText namePlayerText;
    FloatingActionButton addPlayerFab;
    AddPlayerPresenter presenter;
    AddPlayerUseCase addPlayerUseCase;
    PlayersRepo repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        addPlayerFab = (FloatingActionButton) findViewById(R.id.addPlayerFab);
        Executor executor = new ThreadExecutor();
        MainThread mainThread = new MainThreadImp();
        repository = new PlayersRepository();
        addPlayerUseCase = new AddPlayerUseCase(mainThread,executor,repository);
        presenter = new AddPlayerPresenter(addPlayerUseCase);

        presenter.setView(this);
        presenter.init();

    }

    @Override
    public void initUi() {

        addPlayerFab.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
              //  presenter.addPlayer(new Player(namePlayerText.getText().toString(),1,false,false));
                finish();
            }
        });
    }

    @Override
    public void error(Exception e) {

    }


}
