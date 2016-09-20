package com.pedrodavidlp.footballmanager.view;

/**
 * Created by PedroDavidLP on 19/9/16.
 */
public interface View<T> {
    void initUi();
    void error(Exception e);
}
