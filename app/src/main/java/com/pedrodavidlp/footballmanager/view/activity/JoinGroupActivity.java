package com.pedrodavidlp.footballmanager.view.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pedrodavidlp.footballmanager.FootballApplication;
import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.view.fragment.CreateGroupFragment;
import com.pedrodavidlp.footballmanager.view.fragment.InsertNickFragment;
import com.pedrodavidlp.footballmanager.view.fragment.JoinGroupFragment;
import com.pedrodavidlp.footballmanager.view.fragment.SelectJoinOrCreateFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.saeid.fabloading.LoadingView;

public class JoinGroupActivity extends AppCompatActivity{
    @BindView(R.id.joinGroupContainer) CoordinatorLayout container;
    private int currentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);
        ButterKnife.bind(this);
        setFragment(getIntent().getIntExtra("fragment",0));
    }

    public void setFragment(int pos) {
        currentFragment = pos;
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
                SelectJoinOrCreateFragment selectFragment = new SelectJoinOrCreateFragment();
                transaction.replace(R.id.register_container, selectFragment);
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
                CreateGroupFragment groupFragment = new CreateGroupFragment();
                transaction.replace(R.id.register_container, groupFragment);
                transaction.commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        switch (currentFragment){
            case 2:
                setFragment(1);
                break;
            case 3:
                setFragment(1);
                break;
            default:
                super.onBackPressed();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FootballApplication.get(getApplicationContext())
                .releaseGroupComponent();
    }

    public void setFabBehavior(){
    }
}
