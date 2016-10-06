package com.pedrodavidlp.footballmanager.di.components;

import com.pedrodavidlp.footballmanager.di.Activity;
import com.pedrodavidlp.footballmanager.di.AppComponent;
import com.pedrodavidlp.footballmanager.di.modules.ActivityModule;
import com.pedrodavidlp.footballmanager.di.modules.MainModule;
import com.pedrodavidlp.footballmanager.domain.interactor.ChangeMatchUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.GetListUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.GetPlayersOnMatchUseCase;
import com.pedrodavidlp.footballmanager.presenter.ListPlayersPresenter;
import com.pedrodavidlp.footballmanager.presenter.MatchPresenter;
import com.pedrodavidlp.footballmanager.view.fragment.ListPlayersFragment;
import com.pedrodavidlp.footballmanager.view.fragment.MatchFragment;
import com.pedrodavidlp.footballmanager.view.fragment.PayFragment;
import com.pedrodavidlp.footballmanager.view.fragment.TeamFragment;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = {MainModule.class, ActivityModule.class})
public interface MainComponent extends ActivityComponent{

    void inject(MatchFragment matchFragment);
    void inject(TeamFragment teamFragment);
    void inject(ListPlayersFragment listPlayersFragment);
    void inject(PayFragment payFragment);

    ChangeMatchUseCase changeMatchUseCase();
    GetListUseCase getListUseCase();
    GetPlayersOnMatchUseCase getPlayersOnMatchUseCase();
    ListPlayersPresenter listPlayersPresenter();
    MatchPresenter matchPresenter();
}
