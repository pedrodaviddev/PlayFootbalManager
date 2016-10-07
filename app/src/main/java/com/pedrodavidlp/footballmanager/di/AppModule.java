package com.pedrodavidlp.footballmanager.di;

import android.content.Context;

import com.pedrodavidlp.footballmanager.view.executor.MainThreadImp;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;
import com.tonilopezmr.interactorexecutor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }


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

    @Provides
    @Singleton
    public Context provideAppContext() {
        return context;
    }
}
