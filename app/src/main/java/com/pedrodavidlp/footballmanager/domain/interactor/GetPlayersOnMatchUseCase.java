package com.pedrodavidlp.footballmanager.domain.interactor;

import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.MatchRepo;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.Interactor;
import com.tonilopezmr.interactorexecutor.MainThread;

import java.util.List;

public class GetPlayersOnMatchUseCase extends UseCase<GetPlayersOnMatchUseCase.Callback>{
    public interface Callback {
        void onListMatchLoaded(List<Player> list);
        void onError(Exception e);
    }
    private MatchRepo repository;

    public GetPlayersOnMatchUseCase(MainThread mainThread, Executor executor, MatchRepo repository) {
        super(mainThread, executor);
        this.repository = repository;
    }

    @Override
    public void run() {
       try{
           repository.loadListMatch(callback);
       } catch (Exception e){
           callback.onError(e);
       }
    }
}
