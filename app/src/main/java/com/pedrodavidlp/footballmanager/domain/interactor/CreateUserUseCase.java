package com.pedrodavidlp.footballmanager.domain.interactor;
import com.pedrodavidlp.footballmanager.domain.interactor.common.UseCaseE;
import com.pedrodavidlp.footballmanager.domain.repository.UserRepo;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;

public class CreateUserUseCase extends UseCaseE<CreateUserUseCase.Callback,String> {
    public interface Callback{
        void onSuccesfulCreated();
        void nickTaken();
        void onError(Exception e);
    }


    private UserRepo repository;

    public CreateUserUseCase(MainThread mainThread, Executor executor, UserRepo repository) {
        super(mainThread, executor);
        this.repository = repository;
    }

    @Override
    public void run() {
        try {
            repository.add(entity,callback);
        } catch (Exception e) {
            callback.onError(e);
        }

    }
}
