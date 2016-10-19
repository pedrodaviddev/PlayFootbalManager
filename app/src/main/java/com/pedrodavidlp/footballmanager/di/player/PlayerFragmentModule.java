package com.pedrodavidlp.footballmanager.di.player;

import android.content.Context;

import com.pedrodavidlp.footballmanager.data.MatchRepository;
import com.pedrodavidlp.footballmanager.di.ActivityModule;
import com.pedrodavidlp.footballmanager.di.FragmentScope;
import com.pedrodavidlp.footballmanager.domain.interactor.ChangeMatchUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.GetListUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.GetPlayersOnMatchUseCase;
import com.pedrodavidlp.footballmanager.domain.repository.MatchRepo;
import com.pedrodavidlp.footballmanager.presenter.ListPlayersPresenter;
import com.pedrodavidlp.footballmanager.presenter.MatchPresenter;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayerFragmentModule extends ActivityModule{

    @Provides
    @FragmentScope
    public ListPlayersPresenter provideListPlayersPresenter(@Player GetListUseCase getListUseCase){
        return new ListPlayersPresenter(getListUseCase);
    }

    @Provides
    @FragmentScope
    public MatchPresenter provideMatchPresenter(@Player GetPlayersOnMatchUseCase getPlayersOnMatchUseCase,
                                                @Player ChangeMatchUseCase changeMatchUseCase){
        return new MatchPresenter(getPlayersOnMatchUseCase,changeMatchUseCase);
    }

    @Provides
    @FragmentScope
    @Player
    public GetListUseCase provideListPlayersUseCase( Executor executor,
                                                     MainThread mainThread,
                                                     Context context){
        return new GetListUseCase(mainThread,executor,context);
    }

    @Provides
    @FragmentScope
    @Player
    public GetPlayersOnMatchUseCase provideGetPlayersOnMatchUseCase( Executor executor,
                                                     MainThread mainThread,
                                                     Context context){
        return new GetPlayersOnMatchUseCase(mainThread,executor,context);
    }

    @Provides
    @FragmentScope
    @Player
    public ChangeMatchUseCase provideChangeMatchUseCase( Executor executor,
                                                     MainThread mainThread,
                                                         MatchRepo repository){
        return new ChangeMatchUseCase(mainThread,executor,repository);
    }


}
