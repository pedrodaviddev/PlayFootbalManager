package com.pedrodavidlp.footballmanager.domain.interactor;
import com.pedrodavidlp.footballmanager.domain.repository.UserRepo;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.Interactor;
import com.tonilopezmr.interactorexecutor.MainThread;

public class CreateUserUseCase implements Interactor {
    public interface Callback{
        void onSuccesfulCreated();
        void nickTaken();
        void onError(Exception e);
    }

    private Callback callback;
    private MainThread mainThread;
    private Executor executor;
    private UserRepo repository;
    private String nickname;

    public CreateUserUseCase(MainThread mainThread, Executor executor, UserRepo repository) {
        this.mainThread = mainThread;
        this.executor = executor;
        this.repository = repository;
    }

    @Override
    public void run() {
        try {
            repository.add(nickname,callback);
        } catch (Exception e) {
            callback.onError(e);
        }

    }

    public void execute(final Callback callback,String nickname){
        if(callback == null){
            throw new IllegalArgumentException("CALLBACK CANT BE NULL");
        }
        this.callback = callback;
        this.nickname = nickname;
        this.executor.run(this);
    }
}
