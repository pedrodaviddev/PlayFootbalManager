package com.pedrodavidlp.footballmanager.domain.interactor.common;

import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.Interactor;
import com.tonilopezmr.interactorexecutor.MainThread;

public abstract class UseCaseE<C,E> implements Interactor {
    private MainThread mainThread;
    private Executor executor;
    protected C callback;
    protected E entity;

    public UseCaseE(MainThread mainThread, Executor executor) {
        this.mainThread = mainThread;
        this.executor = executor;
    }

    public void execute(final C callback,  E entity){
        if(callback == null){
            throw new IllegalArgumentException("CALLBACK CANT BE NULL");
        }
        this.callback = callback;
        this.entity = entity;
        this.executor.run(this);
    }
}
