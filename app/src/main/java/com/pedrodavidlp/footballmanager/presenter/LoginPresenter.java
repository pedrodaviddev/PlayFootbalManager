package com.pedrodavidlp.footballmanager.presenter;

import com.pedrodavidlp.footballmanager.domain.interactor.JoinUserUseCase;
import com.pedrodavidlp.footballmanager.presenter.common.Presenter;
import com.pedrodavidlp.footballmanager.view.View;

public class LoginPresenter implements Presenter<View> {
    private JoinUserUseCase joinUserUseCase;
    private View view;

    public LoginPresenter(JoinUserUseCase joinUserUseCase) {
        this.joinUserUseCase = joinUserUseCase;
    }

    @Override
    public void init() {
        view.initUi();
    }

    @Override
    public void setView(View view) {
        if (view == null) {
            throw new IllegalArgumentException("Error setting view");
        }
        this.view = view;
    }
}

