package com.pedrodavidlp.footballmanager.presenter;

import com.pedrodavidlp.footballmanager.domain.interactor.CreateGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.JoinGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.presenter.common.Presenter;
import com.pedrodavidlp.footballmanager.view.ViewQuery;

public class GroupPresenter implements Presenter<ViewQuery<Group>> {
    private CreateGroupUseCase createUseCase;
    private JoinGroupUseCase joinUseCase;
    private ViewQuery<Group> view;

    public GroupPresenter(CreateGroupUseCase createUseCase, JoinGroupUseCase joinUseCase) {
        this.createUseCase = createUseCase;
        this.joinUseCase = joinUseCase;
    }

    @Override
    public void init() {
        view.initUi();
    }

    @Override
    public void setView(ViewQuery<Group> view) {
        if (view == null){
            throw new IllegalArgumentException("Error setting view");
        }
        this.view = view;
    }


    public void createGroup(final Group group,Player creator){
        createUseCase.execute(new CreateGroupUseCase.Callback() {
            @Override
            public void onSuccesfulCreated() {
                view.successfulAccess();
            }

            @Override
            public void onError(Exception e) {
                view.error(e);
            }
        },group,creator);

    }

    public void joinGroup(final Group group,Player toJoin){
        joinUseCase.execute(new JoinGroupUseCase.Callback() {
            @Override
            public void onSuccesfulJoin() {
                view.successfulAccess();
            }

            @Override
            public void onError(Exception e) {

            }
        },group,toJoin);
    }

}
