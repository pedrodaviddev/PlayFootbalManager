package com.pedrodavidlp.footballmanager.domain.repository;

import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.common.Repository;

public interface MatchRepo {
    void join();

    void change();
}
