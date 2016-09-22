package com.pedrodavidlp.footballmanager.domain.repository;

import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.repository.common.Repository;

public interface GroupRepo extends Repository<Group> {
    void join();
}
