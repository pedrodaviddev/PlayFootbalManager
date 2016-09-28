package com.pedrodavidlp.footballmanager.presenter;

import com.pedrodavidlp.footballmanager.domain.interactor.GetPlayersOnMatchUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.ChangeMatchUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.presenter.common.ListPresenter;
import com.pedrodavidlp.footballmanager.view.ViewList;

import java.util.List;

public class MatchPresenter implements ListPresenter<Player> {
    private GetPlayersOnMatchUseCase playersOnMatchUseCase;
    private ChangeMatchUseCase changeMatchUseCase;
    private  ViewList<Player> view;

    public MatchPresenter(GetPlayersOnMatchUseCase playersOnMatchUseCase, ChangeMatchUseCase changeMatchUseCase) {
        this.playersOnMatchUseCase = playersOnMatchUseCase;
        this.changeMatchUseCase = changeMatchUseCase;
    }

    @Override
    public void init() {
        view.initUi();
        loadList();
    }

    @Override
    public void setView(ViewList<Player> view) {
        if (view == null) {
            throw new IllegalArgumentException("Error setting view");
        }
        this.view = view;
    }

    @Override
    public void loadList() {
        playersOnMatchUseCase.execute(new GetPlayersOnMatchUseCase.Callback() {

            @Override
            public void onListMatchLoaded(List<Player> list) {
                view.loadList(list);
            }

            @Override
            public void onError(Exception e) {
                view.error(e);
            }
        });
    }

    public void changeMatch(){
        changeMatchUseCase.execute(new ChangeMatchUseCase.Callback() {
            @Override
            public void hasJoined() {

            }
            public void hasLeft(){

            }

            @Override
            public void onError(Exception e) {
                view.error(e);
            }
        });
    }
}
