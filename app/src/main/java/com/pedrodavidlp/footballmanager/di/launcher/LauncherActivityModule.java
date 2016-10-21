package com.pedrodavidlp.footballmanager.di.launcher;

import android.content.Context;

import com.pedrodavidlp.footballmanager.domain.interactor.SelectStateUseCase;
import com.pedrodavidlp.footballmanager.domain.repository.UserRepo;
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
    SelectStateUseCase provideSelectStateUseCase(MainThread mainThread,
                                                 Executor executor,
                                                 UserRepo repository){
        return new SelectStateUseCase(mainThread,executor,repository);
    }


}
