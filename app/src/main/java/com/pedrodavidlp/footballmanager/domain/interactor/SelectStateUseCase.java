package com.pedrodavidlp.footballmanager.domain.interactor;

import com.pedrodavidlp.footballmanager.domain.interactor.common.UseCase;
import com.pedrodavidlp.footballmanager.domain.repository.UserRepo;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;

public class SelectStateUseCase extends UseCase<SelectStateUseCase.Callback> {
    public static final int NO_CONNECTION = 0;
    public static final int NOT_LOGGED = 1;
    public static final int NO_NICKNAME = 2;
    public static final int NO_GROUP = 3;
    public static final int ADMIN_USER = 4;
    public static final int NORMAL_USER = 5;

    public interface Callback{
        void goToState(int state);
        void error(Exception e);
    }

    private UserRepo repository;

    public SelectStateUseCase(MainThread mainThread, Executor executor, UserRepo repository) {
        super(mainThread,executor);
        this.repository = repository;
    }

    @Override
    public void run() {
        repository.selectState(callback);
    }

}
