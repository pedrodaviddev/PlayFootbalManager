package com.pedrodavidlp.footballmanager.di.player;

import android.content.Context;

import com.pedrodavidlp.footballmanager.di.*;
import com.pedrodavidlp.footballmanager.domain.interactor.GetListUseCase;
import com.pedrodavidlp.footballmanager.presenter.ListPlayersPresenter;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayerListFragmentModule extends ActivityModule{
    @Provides
    @ActivityScope
    @Player
    public GetListUseCase provideListPlayersUseCase( Executor executor,
                                                     MainThread mainThread,
                                                     Context context){
        return new GetListUseCase(mainThread,executor,context);
    }

    @Provides
    @ActivityScope
    public ListPlayersPresenter provideListPlayersPresenter(@Player GetListUseCase getListUseCase){
        return new ListPlayersPresenter(getListUseCase);
    }


}
