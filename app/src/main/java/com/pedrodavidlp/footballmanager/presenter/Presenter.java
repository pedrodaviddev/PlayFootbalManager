package com.pedrodavidlp.footballmanager.presenter;

import com.pedrodavidlp.footballmanager.view.View;

/**
 * Created by PedroDavidLP on 20/9/16.
 */

public interface Presenter<T extends View> {
    void init();
    void setView(T view);
}
