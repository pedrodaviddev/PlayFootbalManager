package com.pedrodavidlp.footballmanager.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrodavidlp.footballmanager.FootballApplication;
import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.di.player.PlayerFragmentModule;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.presenter.MatchPresenter;
import com.pedrodavidlp.footballmanager.view.ViewList;
import com.pedrodavidlp.footballmanager.view.adapter.PlayersOnMatchAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchFragment extends Fragment implements ViewList<Player> {
    private PlayersOnMatchAdapter adapter;
    @BindView(R.id.playersOnMatchRecView) RecyclerView playersOnMatch;

    @Inject MatchPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_match,container,false);

        initDagger();

        ButterKnife.bind(this,view);

        presenter.setView(this);
        presenter.init();
        return view;

    }

    private void initDagger() {
        FootballApplication.get(getActivity().getApplicationContext())
                .getPlayerComponent()
                .plus(new PlayerFragmentModule())
                .inject(this);
    }

    @Override
    public void loadList(List<Player> list) {
        adapter.setData(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initUi() {
        adapter = new PlayersOnMatchAdapter(getContext());
        playersOnMatch.setAdapter(adapter);
        playersOnMatch.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void error(Exception e) {

    }

    public void changeStateMatch() {
        presenter.changeMatch();
    }


}
