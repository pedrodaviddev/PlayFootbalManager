package com.pedrodavidlp.footballmanager.domain.repository;

import com.pedrodavidlp.footballmanager.domain.interactor.CreateUserUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.SelectStateUseCase;
import com.pedrodavidlp.footballmanager.domain.repository.common.Repository;

public interface UserRepo extends Repository<String> {
    void add(String s, CreateUserUseCase.Callback callback);
    void selectState(SelectStateUseCase.Callback callback);
}
