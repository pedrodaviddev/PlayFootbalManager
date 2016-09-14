package com.pedrodavidlp.footballmanager.domain.repository.common;

import com.pedrodavidlp.footballmanager.domain.model.Player;

import java.util.List;

/**
 * Created by PedroDavidLP on 14/9/16.
 */
public interface Repository<T> {
    void add(T t);
    List<T> getAll();
}
