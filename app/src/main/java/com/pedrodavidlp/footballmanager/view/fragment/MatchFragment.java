package com.pedrodavidlp.footballmanager.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.data.MatchRepository;
import com.pedrodavidlp.footballmanager.domain.interactor.GetPlayersOnMatchUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.ChangeMatchUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.MatchRepo;
import com.pedrodavidlp.footballmanager.presenter.MatchPresenter;
import com.pedrodavidlp.footballmanager.view.ViewList;
import com.pedrodavidlp.footballmanager.view.adapter.PlayersOnMatchAdapter;
import com.pedrodavidlp.footballmanager.view.executor.MainThreadImp;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;
import com.tonilopezmr.interactorexecutor.ThreadExecutor;

import java.util.List;

public class MatchFragment extends Fragment implements ViewList<Player>{
    private RecyclerView playersOnMatch;
    private PlayersOnMatchAdapter adapter;
    private MatchPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_match,container,false);
        playersOnMatch = (RecyclerView) rootView.findViewById(R.id.playersOnMatchRecView);

        MainThread mainThread = new MainThreadImp();
        Executor executor = new ThreadExecutor();
        MatchRepo repository = new MatchRepository(getContext());
        GetPlayersOnMatchUseCase playersOnMatchUseCase = new GetPlayersOnMatchUseCase(mainThread,executor,getContext());
        ChangeMatchUseCase changeMatchUseCase = new ChangeMatchUseCase(mainThread,executor,repository);
        presenter = new MatchPresenter(playersOnMatchUseCase, changeMatchUseCase);
        presenter.setView(this);
        presenter.init();
        return rootView;

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
