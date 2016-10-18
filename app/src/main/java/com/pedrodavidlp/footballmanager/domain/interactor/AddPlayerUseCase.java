package com.pedrodavidlp.footballmanager.domain.interactor;

import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.PlayersRepo;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.Interactor;
import com.tonilopezmr.interactorexecutor.MainThread;

/**
 * Created by PedroDavidLP on 20/9/16.
 */

public class AddPlayerUseCase implements Interactor {
    public interface Callback{
        void onSuccesfulAdded();
        void onError(Exception e);
    }

    private Callback callback;
    private MainThread mainThread;
    private Executor executor;
    private Player player;
    private PlayersRepo repository;

    public AddPlayerUseCase(MainThread mainThread, Executor executor, PlayersRepo repository) {
        this.mainThread = mainThread;
        this.executor = executor;
        this.repository = repository;
    }

    @Override
    public void run() {
        try {
            repository.add(player);
            callback.onSuccesfulAdded();
        } catch (Exception e) {
            callback.onError(e);
            e.printStackTrace();
        }

    }

    public void execute(final Callback callback, Player player){
        if(callback == null){
            throw new IllegalArgumentException("CALLBACK CANT BE NULL");
        }
        this.callback = callback;
        this.player = player;
        this.executor.run(this);
    }
}
