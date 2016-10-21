package com.pedrodavidlp.footballmanager.di.launcher;

import android.content.Context;

import com.pedrodavidlp.footballmanager.domain.interactor.SelectStateUseCase;
import com.pedrodavidlp.footballmanager.presenter.LauncherPresenter;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;

import dagger.Module;
import dagger.Provides;

@Module
public class LauncherActivityModule {

    @Provides
    @LauncherScope
    LauncherPresenter provideLauncherPresenter(SelectStateUseCase selectStateUseCase){
        return new LauncherPresenter(selectStateUseCase);
    }

    @Provides
    @LauncherScope
    SelectStateUseCase provideSelectStateUseCase(Context context,
                                                 MainThread mainThread,
                                                 Executor executor){
        return new SelectStateUseCase(context,mainThread,executor);
    }


}
