package com.pedrodavidlp.footballmanager.presenter;

import com.pedrodavidlp.footballmanager.view.ViewList;

import java.util.List;

/**
 * Created by PedroDavidLP on 20/9/16.
 */

public interface ListPresenter<T> extends Presenter<ViewList<T>>{
    void loadList();
}
