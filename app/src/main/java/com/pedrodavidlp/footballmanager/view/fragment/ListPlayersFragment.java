package com.pedrodavidlp.footballmanager.view.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.domain.interactor.GetListUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.presenter.ListPlayersPresenter;
import com.pedrodavidlp.footballmanager.view.ViewList;
import com.pedrodavidlp.footballmanager.view.activity.MainActivity;
import com.pedrodavidlp.footballmanager.view.adapter.ListPlayersAdapter;
import com.pedrodavidlp.footballmanager.view.executor.MainThreadImp;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;
import com.tonilopezmr.interactorexecutor.ThreadExecutor;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

/**
 * Created by PedroDavidLP on 14/9/16.
 */
public class ListPlayersFragment extends Fragment implements ViewList<Player>,ListPlayersAdapter.OnItemLongClickListener{
    private RecyclerView listPlayers;
    private GetListUseCase useCase;
    private ListPlayersAdapter adapter;
    private ListPlayersPresenter presenter;
    private AVLoadingIndicatorView loading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_list,container,false);
        listPlayers = (RecyclerView) rootView.findViewById(R.id.playersRecView);
        loading = (AVLoadingIndicatorView) rootView.findViewById(R.id.loadingListPlayers);
        Executor executor = new ThreadExecutor();
        MainThread mainThread = new MainThreadImp();
        useCase = new GetListUseCase(mainThread,executor,getContext());
        presenter = new ListPlayersPresenter(useCase);
        presenter.setView(this);
        presenter.init();


        return rootView;
    }

    @Override
    public void loadList(List<Player> list) {
        adapter.setData(list);
        listPlayers.setVisibility(View.VISIBLE);
        loading.hide();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initUi() {
        adapter = new ListPlayersAdapter(this);
        listPlayers.setAdapter(adapter);
        listPlayers.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void error(Exception e) {
        Snackbar.make(getView(),"Se ha producido un error",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        loading.show();
        presenter.loadList();
    }

    @Override
    public boolean onItemLongClicked(View v) {
        ((MainActivity) getActivity()).setFabMenu();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue_normal));
        }else {
            v.setBackgroundColor(getResources().getColor(R.color.blue_normal));
        }
        return false;
    }
}
