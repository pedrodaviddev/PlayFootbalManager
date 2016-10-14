package com.pedrodavidlp.footballmanager.presenter;

import android.support.annotation.Nullable;

import com.pedrodavidlp.footballmanager.domain.interactor.CreateGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.JoinGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.presenter.common.Presenter;
import com.pedrodavidlp.footballmanager.view.ViewLogin;
import com.pedrodavidlp.footballmanager.view.ViewTry;

public class GroupPresenter implements Presenter<ViewLogin> {
    private CreateGroupUseCase createUseCase;
    private JoinGroupUseCase joinUseCase;
    private  ViewLogin view;

    public GroupPresenter(@Nullable CreateGroupUseCase createUseCase, @Nullable JoinGroupUseCase joinUseCase) {
        this.createUseCase = createUseCase;
        this.joinUseCase = joinUseCase;
    }

    @Override
    public void init() {
        view.initUi();
    }

    @Override
    public void setView(ViewLogin view) {
        if (view == null){
            throw new IllegalArgumentException("Error setting view");
        }
        this.view = view;
    }


    public void createGroup(final Group group,Player creator){
        createUseCase.execute(new CreateGroupUseCase.Callback() {
            @Override
            public void onSuccesfulCreated() {
                view.success();
            }

            @Override
            public void nameTaken() {
                view.wrongId();
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
                view.success();
            }

            @Override
            public void groupNotExist() {
                view.wrongId();
            }

            @Override
            public void wrongPassword() {
                view.wrongPass();
            }

            @Override
            public void onError(Exception e) {
                view.error(e);
            }
        },group,toJoin);
    }

}
