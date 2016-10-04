package com.pedrodavidlp.footballmanager.view.activity;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.data.GroupRepository;
import com.pedrodavidlp.footballmanager.domain.interactor.CreateGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.JoinGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.GroupRepo;
import com.pedrodavidlp.footballmanager.presenter.GroupPresenter;
import com.pedrodavidlp.footballmanager.view.ViewQuery;
import com.pedrodavidlp.footballmanager.view.executor.MainThreadImp;
import com.pedrodavidlp.footballmanager.view.fragment.InsertGroupFragment;
import com.pedrodavidlp.footballmanager.view.fragment.InsertNickFragment;
import com.pedrodavidlp.footballmanager.view.fragment.JoinGroupFragment;
import com.pedrodavidlp.footballmanager.view.fragment.ListPlayersFragment;
import com.pedrodavidlp.footballmanager.view.fragment.MatchFragment;
import com.pedrodavidlp.footballmanager.view.fragment.PayFragment;
import com.pedrodavidlp.footballmanager.view.fragment.TeamFragment;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;
import com.tonilopezmr.interactorexecutor.ThreadExecutor;

import org.w3c.dom.Text;

public class JoinGroupActivity extends AppCompatActivity{
    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);
        fab = (FloatingActionButton) findViewById(R.id.registerFab);
        setFragment(0);
    }

    public void setFragment(int pos) {
        FragmentManager manager;
        FragmentTransaction transaction;
        switch (pos) {
            case 0:
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();
                InsertNickFragment nickFragment = new InsertNickFragment();
                transaction.replace(R.id.register_container, nickFragment, "FRAGMENT_MATCH");
                transaction.commit();
                break;
            case 1:
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();
                InsertGroupFragment groupFragment = new InsertGroupFragment();
                transaction.replace(R.id.register_container, groupFragment);
                transaction.commit();
                break;
            case 2:
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();
                JoinGroupFragment joinFragment = new JoinGroupFragment();
                transaction.replace(R.id.register_container, joinFragment);
                transaction.commit();
                break;
            case 3:
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();
                PayFragment payFragment = new PayFragment();
                transaction.replace(R.id.register_container, payFragment);
                transaction.commit();
                break;

        }
    }
    public void setListenerToFab(View.OnClickListener listener){
        fab.setOnClickListener(listener);
    }
}
