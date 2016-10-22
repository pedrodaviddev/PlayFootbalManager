package com.pedrodavidlp.footballmanager.domain.interactor;

import com.pedrodavidlp.footballmanager.domain.interactor.common.UseCase;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.PlayersRepo;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;

import java.util.List;

/**
 * Created by PedroDavidLP on 15/9/16.
 */
public class GetListUseCase extends UseCase<GetListUseCase.Callback> {
    public interface Callback{
        void onListLoaded(List<Player> list);
        void onError(Exception e);
    }

    private PlayersRepo repository;

    public GetListUseCase(MainThread mainThread, Executor executor, PlayersRepo repository) {
        super(mainThread,executor);
        this.repository = repository;
    }

    @Override
    public void run() {
      repository.loadList(callback);
    }


}
