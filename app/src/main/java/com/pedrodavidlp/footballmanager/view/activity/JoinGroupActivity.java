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

import com.pedrodavidlp.footballmanager.FootballApplication;
import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.view.adapter.FabBehavior;
import com.pedrodavidlp.footballmanager.view.fragment.CreateGroupFragment;
import com.pedrodavidlp.footballmanager.view.fragment.InsertNickFragment;
import com.pedrodavidlp.footballmanager.view.fragment.JoinGroupFragment;
import com.pedrodavidlp.footballmanager.view.fragment.SelectJoinOrCreateFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.saeid.fabloading.LoadingView;

public class JoinGroupActivity extends AppCompatActivity{
    @BindView(R.id.registerFab) LoadingView fab;
    @BindView(R.id.joinGroupContainer) CoordinatorLayout container;
    private int currentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);
        ButterKnife.bind(this);
        setFragment(getIntent().getIntExtra("fragment",0));
        //TODO : ALL IMAGES IN VECTORS
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            fab.setImageResource(R.drawable.icon_next);
        } else {
            fab.setImageResource(R.drawable.image_next);
        }

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
        boolean isLollipop = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
        int fireball = isLollipop ? R.drawable.icon_fire_ball : R.drawable.image_fire_ball;
        int shorts = isLollipop ? R.drawable.icon_shorts : R.drawable.image_shorts;
        int gloves = isLollipop ? R.drawable.icon_gloves : R.drawable.image_gloves;
        int tactic = isLollipop ? R.drawable.icon_tactic : R.drawable.image_tactic;
        fab.addAnimation(Color.parseColor("#FFD200"), fireball,
                LoadingView.FROM_LEFT);
        fab.addAnimation(Color.parseColor("#2F5DA9"), shorts,
                LoadingView.FROM_TOP);
        fab.addAnimation(Color.parseColor("#FF4218"), gloves,
                LoadingView.FROM_RIGHT);
        fab.addAnimation(Color.parseColor("#C7E7FB"), tactic,
                LoadingView.FROM_BOTTOM);
        fab.setRepeat(Integer.MAX_VALUE);
        fab.resumeAnimation();
        fab.startAnimation();
    }
    public void stopAnimationFab(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            fab.setImageResource(R.drawable.icon_next);
        } else {
            fab.setImageResource(R.drawable.image_next);
        }
        fab.pauseAnimation();
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
