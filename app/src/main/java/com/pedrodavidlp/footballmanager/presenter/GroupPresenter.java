package com.pedrodavidlp.footballmanager.presenter;

import android.util.Log;

import com.pedrodavidlp.footballmanager.domain.interactor.CreateGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.GetCurrentPlayerUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.JoinGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.SearchGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.presenter.common.Presenter;
import com.pedrodavidlp.footballmanager.view.ViewQuery;

import java.util.ArrayList;
import java.util.List;

public class GroupPresenter implements Presenter<ViewQuery<Group>> {
    private final String TAG = getClass().getSimpleName();
    private JoinGroupUseCase joinUseCase;
    private SearchGroupUseCase searchUseCase;
    private CreateGroupUseCase createUseCase;
    private GetCurrentPlayerUseCase currentPlayerUseCase;
    private ViewQuery<Group> view;

    public GroupPresenter(JoinGroupUseCase joinUseCase, SearchGroupUseCase searchUseCase,
                          CreateGroupUseCase createUseCase, GetCurrentPlayerUseCase currentPlayerUseCase) {
        this.joinUseCase = joinUseCase;
        this.searchUseCase = searchUseCase;
        this.createUseCase = createUseCase;
        this.currentPlayerUseCase = currentPlayerUseCase;
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

    public void searchGroup(Group group){
        searchUseCase.execute(new SearchGroupUseCase.Callback() {
            @Override
            public void onSuccessfulSearch(Group group) {
                view.successfulSearch(group);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    public void createGroup(final Group group){

        currentPlayerUseCase.execute(new GetCurrentPlayerUseCase.Callback() {
            @Override
            public void onPlayerFound(Player player) {
                List<Player> list = new ArrayList<>();
                list.add(player);
                group.setPlayers(list);
                uploadGroup(group);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    private void uploadGroup(Group group){
        createUseCase.execute(new CreateGroupUseCase.Callback() {
            @Override
            public void onSuccesfulCreated() {
                view.successfulAccess();
            }

            @Override
            public void onError(Exception e) {
                view.error(e);
            }
        },group);
    }

}
