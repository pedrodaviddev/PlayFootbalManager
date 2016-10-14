package com.pedrodavidlp.footballmanager.view.activity;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.view.fragment.CreateGroupFragment;
import com.pedrodavidlp.footballmanager.view.fragment.InsertNickFragment;
import com.pedrodavidlp.footballmanager.view.fragment.JoinGroupFragment;
import com.pedrodavidlp.footballmanager.view.fragment.SelectJoinOrCreateFragment;

import io.saeid.fabloading.LoadingView;

public class JoinGroupActivity extends AppCompatActivity{
    private LoadingView fab;
    private int currentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);
        fab = (LoadingView) findViewById(R.id.registerFab);
        setFragment(getIntent().getIntExtra("fragment",0));
        fab.setImageResource(R.drawable.icon_next);
    }

    public void setFragment(int pos) {
        currentFragment = pos;
        FragmentManager manager;
        FragmentTransaction transaction;
        fab.pauseAnimation();
        switch (pos) {
            case 0:
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();
                InsertNickFragment nickFragment = new InsertNickFragment();
                transaction.replace(R.id.register_container, nickFragment, "FRAGMENT_MATCH");
                transaction.commit();
                fab.show();
                break;
            case 1:
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();
                SelectJoinOrCreateFragment selectFragment = new SelectJoinOrCreateFragment();
                transaction.replace(R.id.register_container, selectFragment);
                transaction.commit();
                fab.hide();
                break;
            case 2:
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();
                JoinGroupFragment joinFragment = new JoinGroupFragment();
                transaction.replace(R.id.register_container, joinFragment);
                transaction.commit();
                fab.show();
                break;
            case 3:
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();
                CreateGroupFragment groupFragment = new CreateGroupFragment();
                transaction.replace(R.id.register_container, groupFragment);
                transaction.commit();
                fab.show();
                break;
        }
    }
    public void setListenerToFab(View.OnClickListener listener){
        fab.setOnClickListener(listener);
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

    public void startAnimationFab() {
        fab.addAnimation(Color.parseColor("#FFD200"), R.drawable.icon_fire_ball,
                LoadingView.FROM_LEFT);
        fab.addAnimation(Color.parseColor("#2F5DA9"), R.drawable.icon_shorts,
                LoadingView.FROM_TOP);
        fab.addAnimation(Color.parseColor("#FF4218"), R.drawable.icon_gloves,
                LoadingView.FROM_RIGHT);
        fab.addAnimation(Color.parseColor("#C7E7FB"), R.drawable.icon_tactic,
                LoadingView.FROM_BOTTOM);
        fab.setRepeat(Integer.MAX_VALUE);
        fab.resumeAnimation();
        fab.startAnimation();
    }
    public void stopAnimationFab(){
        fab.setImageResource(R.drawable.icon_next);
        fab.pauseAnimation();
    }
}
