package com.pedrodavidlp.footballmanager.domain.interactor;

import com.pedrodavidlp.footballmanager.domain.interactor.common.UseCase;
import com.pedrodavidlp.footballmanager.domain.repository.MatchRepo;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;

public class ChangeMatchUseCase extends UseCase<ChangeMatchUseCase.Callback> {
    public interface Callback{
        void hasJoined();
        void hasLeft();
        void onError(Exception e);
    }

    private MatchRepo repository;

    public ChangeMatchUseCase(MainThread mainThread, Executor executor, MatchRepo repository) {
        super(mainThread, executor);
        this.repository = repository;
    }

    @Override
    public void run() {
        try {
            repository.change();
        } catch (Exception e) {
            callback.onError(e);
            e.printStackTrace();
        }

    }


}
