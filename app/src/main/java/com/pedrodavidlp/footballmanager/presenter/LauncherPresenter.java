package com.pedrodavidlp.footballmanager.presenter;

import com.pedrodavidlp.footballmanager.domain.interactor.SelectStateUseCase;
import com.pedrodavidlp.footballmanager.presenter.common.Presenter;
import com.pedrodavidlp.footballmanager.view.ViewMode;

public class LauncherPresenter implements Presenter<ViewMode> {
    private SelectStateUseCase stateUseCase;
    private ViewMode view;

    public LauncherPresenter(SelectStateUseCase connectionUseCase) {
        this.stateUseCase = connectionUseCase;
    }

    @Override
    public void init() {
        view.initUi();
        stateUseCase.execute(new SelectStateUseCase.Callback() {
            @Override
            public void goToState(int state) {
                view.initUi(state);
            }

            @Override
            public void error(Exception e) {
                view.error(e);
            }
        });
    }

    @Override
    public void setView(ViewMode view) {
        if (view == null) {
            throw new IllegalArgumentException("Error setting view");
        }
        this.view = view;
    }
}
