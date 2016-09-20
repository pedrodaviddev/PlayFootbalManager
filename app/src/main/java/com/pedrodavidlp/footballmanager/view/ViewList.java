package com.pedrodavidlp.footballmanager.view;

import java.util.List;

/**
 * Created by PedroDavidLP on 19/9/16.
 */
public interface ViewList<T> extends View<T> {
    void loadList(List<T> list);
}
