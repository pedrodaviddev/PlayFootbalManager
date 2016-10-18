package com.pedrodavidlp.footballmanager.presenter;

import com.pedrodavidlp.footballmanager.domain.interactor.GetListUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.presenter.common.ListPresenter;
import com.pedrodavidlp.footballmanager.view.ViewList;

import java.util.List;

/**
 * Created by PedroDavidLP on 15/9/16.
 */
public class ListPlayersPresenter implements ListPresenter<Player> {
    private final String TAG = getClass().getSimpleName();
    private GetListUseCase getListUseCase;
    private ViewList<Player> viewList;

    public ListPlayersPresenter(GetListUseCase getListUseCase) {
        this.getListUseCase = getListUseCase;
    }


    @Override
    public void init() {
        viewList.initUi();

        loadList();
    }

    @Override
    public void setView(ViewList<Player> view) {
        if (view == null){
            throw new IllegalArgumentException("Error setting view");
        }
        viewList = view;
    }


    @Override
    public void loadList() {
        getListUseCase.execute(new GetListUseCase.Callback() {
            @Override
            public void onListLoaded(List<Player> list) {
                viewList.loadList(list);
            }

            @Override
            public void onError(Exception e) {
                viewList.error(e);
            }
        });
    }
}
