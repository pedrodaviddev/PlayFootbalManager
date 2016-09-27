package com.pedrodavidlp.footballmanager.domain.repository;

import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.common.Repository;

public interface GroupRepo extends Repository<Group> {
    void join();
    void create(Group group,Player creator);
}
