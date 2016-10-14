package com.pedrodavidlp.footballmanager.domain.interactor;

import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.GroupRepo;
import com.pedrodavidlp.footballmanager.domain.repository.MatchRepo;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.Interactor;
import com.tonilopezmr.interactorexecutor.MainThread;

public class JoinGroupUseCase implements Interactor {
    public interface Callback{
        void onSuccesfulJoin();
        void groupNotExist();
        void wrongPassword();
        void onError(Exception e);
    }

    private Callback callback;
    private MainThread mainThread;
    private Executor executor;
    private GroupRepo repository;
    private Group group;
    private Player toJoin;

    public JoinGroupUseCase(MainThread mainThread, Executor executor, GroupRepo repository) {
        this.mainThread = mainThread;
        this.executor = executor;
        this.repository = repository;
    }

    @Override
    public void run() {
        try {
            repository.join(group,toJoin,callback);
        } catch (Exception e) {
            callback.onError(e);
            e.printStackTrace();
        }

    }

    public void execute(final Callback callback,Group group, Player toJoin){
        if(callback == null){
            throw new IllegalArgumentException("CALLBACK CANT BE NULL");
        }
        this.group = group;
        this.toJoin = toJoin;
        this.callback = callback;
        this.executor.run(this);
    }
}
