package com.pedrodavidlp.footballmanager.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.view.ViewList;

import java.util.List;

/**
 * Created by PedroDavidLP on 14/9/16.
 */
public class MatchFragment extends Fragment implements ViewList<Player>{
    private RecyclerView playersOnMatch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_match,container,false);
        playersOnMatch = (RecyclerView) rootView.findViewById(R.id.playersOnMatchRecView);

        return rootView;

    }

    @Override
    public void loadList(List<Player> list) {

    }

    @Override
    public void initUi() {

    }

    @Override
    public void error(Exception e) {

    }
}
