package com.pedrodavidlp.footballmanager.domain.repository;

import com.pedrodavidlp.footballmanager.domain.interactor.GetPlayersOnMatchUseCase;

public interface MatchRepo {
    void loadListMatch(GetPlayersOnMatchUseCase.Callback callback);

    void join();

    void change();

}
