package com.pedrodavidlp.footballmanager.presenter;

import com.pedrodavidlp.footballmanager.domain.interactor.CreateUserUseCase;
import com.pedrodavidlp.footballmanager.presenter.common.Presenter;
import com.pedrodavidlp.footballmanager.view.ViewTry;

public class UserPresenter implements Presenter<ViewTry> {
    private CreateUserUseCase createUserUseCase;
    private ViewTry view;

    public UserPresenter(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @Override
    public void init() {
        view.initUi();
    }

    @Override
    public void setView(ViewTry view) {
        if (view == null) {
            throw new IllegalArgumentException("Error setting view");
        }
        this.view = view;
    }

    public void insertNickname(String nickname) {
        createUserUseCase.execute(new CreateUserUseCase.Callback() {
            @Override
            public void onSuccesfulCreated() {
                view.succeed();
            }

            @Override
            public void nickTaken() {
                view.failed();
            }

            @Override
            public void onError(Exception e) {
                view.error(e);
            }
        },nickname);
    }
}
