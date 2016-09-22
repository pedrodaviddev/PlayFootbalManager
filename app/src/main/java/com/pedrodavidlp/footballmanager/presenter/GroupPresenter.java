package com.pedrodavidlp.footballmanager.presenter;

import com.pedrodavidlp.footballmanager.domain.interactor.CreateGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.JoinGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.JoinMatchUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.SearchGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.presenter.common.Presenter;
import com.pedrodavidlp.footballmanager.view.View;

public class GroupPresenter implements Presenter<View<Group>> {
    private JoinMatchUseCase joinUseCase;
    private SearchGroupUseCase searchUseCase;
    private CreateGroupUseCase createUseCase;
    private View<Group> view;

    public GroupPresenter(JoinMatchUseCase joinUseCase, SearchGroupUseCase searchUseCase, CreateGroupUseCase createUseCase) {
        this.joinUseCase = joinUseCase;
        this.searchUseCase = searchUseCase;
        this.createUseCase = createUseCase;
    }

    @Override
    public void init() {

    }

    @Override
    public void setView(View<Group> view) {
        if (view == null){
            throw new IllegalArgumentException("Error setting view");
        }
        this.view = view;
    }

}
