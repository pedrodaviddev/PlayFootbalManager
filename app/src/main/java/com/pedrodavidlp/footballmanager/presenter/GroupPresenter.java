package com.pedrodavidlp.footballmanager.presenter;

import com.pedrodavidlp.footballmanager.domain.interactor.CreateGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.presenter.common.Presenter;
import com.pedrodavidlp.footballmanager.view.ViewQuery;

public class GroupPresenter implements Presenter<ViewQuery<Group>> {
    private CreateGroupUseCase createUseCase;
    private ViewQuery<Group> view;

    public GroupPresenter(CreateGroupUseCase createUseCase) {
        this.createUseCase = createUseCase;
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

}
