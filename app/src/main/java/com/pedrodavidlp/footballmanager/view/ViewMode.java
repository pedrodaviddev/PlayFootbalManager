package com.pedrodavidlp.footballmanager.view;

public interface ViewMode<T> extends View<T> {
    void initUi(int mode);
}
