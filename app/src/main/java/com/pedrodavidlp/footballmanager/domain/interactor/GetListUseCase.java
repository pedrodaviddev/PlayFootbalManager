package com.pedrodavidlp.footballmanager.domain.interactor;

import com.pedrodavidlp.footballmanager.domain.model.Player;

import java.util.List;

/**
 * Created by PedroDavidLP on 15/9/16.
 */
public class GetListUseCase {
    public interface Callback{
        void onListLoaded(List<Player> list);
        void onError(Exception e);
    }

}
