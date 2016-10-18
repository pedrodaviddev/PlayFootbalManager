package com.pedrodavidlp.footballmanager.domain.interactor;

import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.UserRepo;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.Interactor;
import com.tonilopezmr.interactorexecutor.MainThread;

public class JoinUserUseCase implements Interactor {
    public interface Callback{
        void onSuccesfulJoin();
        void onError(Exception e);
    }

    private AddPlayerUseCase.Callback callback;
    private MainThread mainThread;
    private Executor executor;
    private Player player;
    private UserRepo repository;

    public JoinUserUseCase(MainThread mainThread, Executor executor, UserRepo repository) {
        this.mainThread = mainThread;
        this.executor = executor;
        this.repository = repository;
    }

    @Override
    public void run() {
        try {
           // repository.add(player);
            callback.onSuccesfulAdded();
        } catch (Exception e) {
            callback.onError(e);
            e.printStackTrace();
        }

    }

    public void execute(final AddPlayerUseCase.Callback callback, Player player){
        if(callback == null){
            throw new IllegalArgumentException("CALLBACK CANT BE NULL");
        }
        this.callback = callback;
        this.player = player;
        this.executor.run(this);
    }

}
