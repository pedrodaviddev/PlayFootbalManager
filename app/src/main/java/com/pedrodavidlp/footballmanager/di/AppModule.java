package com.pedrodavidlp.footballmanager.di;

import com.pedrodavidlp.footballmanager.view.executor.MainThreadImp;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;
import com.tonilopezmr.interactorexecutor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    public MainThread provideMainThread(){
        return new MainThreadImp();
    }

    @Provides
    @Singleton
    public Executor provideExecutor(){
        return new ThreadExecutor();
    }

}
