package com.pedrodavidlp.footballmanager.domain.interactor;

import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.Interactor;
import com.tonilopezmr.interactorexecutor.MainThread;

public class SearchGroupUseCase extends UseCase<SearchGroupUseCase.Callback>{
    public interface Callback{
        void onSuccessfulSearch(Group group);
        void onError(Exception e);
    }

    public SearchGroupUseCase(MainThread mainThread, Executor executor) {
        super(mainThread, executor);
    }

    @Override
    public void run() {
        try {
            callback.onSuccessfulSearch(null);
        } catch (Exception e) {
            callback.onError(e);
            e.printStackTrace();
        }

    }
}
