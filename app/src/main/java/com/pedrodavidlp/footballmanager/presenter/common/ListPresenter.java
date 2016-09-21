package com.pedrodavidlp.footballmanager.presenter.common;

import com.pedrodavidlp.footballmanager.view.ViewList;

/**
 * Created by PedroDavidLP on 20/9/16.
 */

public interface ListPresenter<T> extends Presenter<ViewList<T>>{
    void loadList();
}
