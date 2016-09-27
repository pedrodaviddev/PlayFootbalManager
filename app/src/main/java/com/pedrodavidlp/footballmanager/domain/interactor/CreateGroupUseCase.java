package com.pedrodavidlp.footballmanager.domain.interactor;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pedrodavidlp.footballmanager.data.PlayersRepository;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.GroupRepo;
import com.pedrodavidlp.footballmanager.domain.repository.MatchRepo;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.Interactor;
import com.tonilopezmr.interactorexecutor.MainThread;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupUseCase implements Interactor {
    public interface Callback{
        void onSuccesfulCreated();
        void onError(Exception e);
    }

    private Callback callback;
    private MainThread mainThread;
    private Executor executor;
    private GroupRepo repository;
    private Group group;
    private Player creator;

    public CreateGroupUseCase(MainThread mainThread, Executor executor, GroupRepo repository) {
        this.mainThread = mainThread;
        this.executor = executor;
        this.repository = repository;
    }

    @Override
    public void run() {
        try {
            repository.create(group,creator);
            callback.onSuccesfulCreated();
        } catch (Exception e) {
            callback.onError(e);
            e.printStackTrace();
        }

    }

    public void execute(final Callback callback, Group group, Player creator){
        if(callback == null){
            throw new IllegalArgumentException("CALLBACK CANT BE NULL");
        }
        this.callback = callback;
        this.group = group;
        this.creator = creator;
        this.executor.run(this);
    }
}
