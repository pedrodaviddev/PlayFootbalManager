package com.pedrodavidlp.footballmanager.domain.interactor;

import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.MatchRepo;
import com.pedrodavidlp.footballmanager.domain.repository.PlayersRepo;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.Interactor;
import com.tonilopezmr.interactorexecutor.MainThread;

public class JoinMatchUseCase implements Interactor {
    public interface Callback{
        void onSuccesfulJoin();
        void onError(Exception e);
    }

    private Callback callback;
    private MainThread mainThread;
    private Executor executor;
    private MatchRepo repository;

    public JoinMatchUseCase(MainThread mainThread, Executor executor, MatchRepo repository) {
        this.mainThread = mainThread;
        this.executor = executor;
        this.repository = repository;
    }

    @Override
    public void run() {
        try {
            repository.join();
            callback.onSuccesfulJoin();
        } catch (Exception e) {
            callback.onError(e);
            e.printStackTrace();
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
