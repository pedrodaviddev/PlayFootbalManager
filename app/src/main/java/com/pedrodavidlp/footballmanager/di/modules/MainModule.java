package com.pedrodavidlp.footballmanager.di.modules;

import com.pedrodavidlp.footballmanager.di.Activity;
import com.pedrodavidlp.footballmanager.domain.interactor.ChangeMatchUseCase;
import com.pedrodavidlp.footballmanager.domain.repository.MatchRepo;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    @Activity
    public ChangeMatchUseCase provideChangeMatchUseCase(MainThread mainThread, Executor executor, MatchRepo repository){
        return new ChangeMatchUseCase(mainThread,executor,repository);
    }
}
