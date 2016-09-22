package com.pedrodavidlp.footballmanager.view;

import com.pedrodavidlp.footballmanager.domain.model.Group;

public interface ViewQuery<T> extends View<T> {
   void successfulAccess();
   void successfulSearch(T t);
}
