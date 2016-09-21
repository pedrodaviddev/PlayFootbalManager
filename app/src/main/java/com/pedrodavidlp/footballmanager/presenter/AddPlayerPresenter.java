package com.pedrodavidlp.footballmanager.presenter;

import com.pedrodavidlp.footballmanager.domain.interactor.AddPlayerUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.presenter.common.Presenter;
import com.pedrodavidlp.footballmanager.view.View;

/**
 * Created by PedroDavidLP on 20/9/16.
 */

public class AddPlayerPresenter implements Presenter<View<Player>> {
    private AddPlayerUseCase addPlayerUseCase;
    private View<Player> view;

    public AddPlayerPresenter(AddPlayerUseCase addPlayerUseCase) {
        this.addPlayerUseCase = addPlayerUseCase;
    }

    @Override
    public void init() {
        view.initUi();
    }

    @Override
    public void setView(View<Player> view) {
        if (view == null){
            throw new IllegalArgumentException("Error setting view");
        }
        this.view = view;
    }
    public void addPlayer(Player player){
        addPlayerUseCase.execute(new AddPlayerUseCase.Callback() {
            @Override
            public void onSuccesfulAdded() {
            }

            @Override
            public void onError(Exception e) {
                view.error(e);
            }
        },player);
    }
}
