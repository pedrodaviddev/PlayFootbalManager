package com.pedrodavidlp.footballmanager.view.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.IdRes;
import android.widget.RelativeLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.clans.fab.FloatingActionMenu;
import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.presenter.ListPlayersPresenter;
import com.pedrodavidlp.footballmanager.view.fragment.ListPlayersFragment;
import com.pedrodavidlp.footballmanager.view.fragment.MatchFragment;
import com.pedrodavidlp.footballmanager.view.fragment.PayFragment;
import com.pedrodavidlp.footballmanager.view.fragment.TeamFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    private BottomBar bottomBar;
    private FloatingActionMenu fabOptions;
    private com.github.clans.fab.FloatingActionButton fabPlay;
    private com.github.clans.fab.FloatingActionButton addActionMenu;
    private RelativeLayout rootView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fabPlay=(com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabPlay);
        fabOptions=(FloatingActionMenu) findViewById(R.id.fabOptions);
        rootView = (RelativeLayout) findViewById(R.id.rootActivity);
        bottomBar=(BottomBar)findViewById(R.id.bottomBar);
        addActionMenu=(com.github.clans.fab.FloatingActionButton)findViewById(R.id.addActionMenu);
        addActionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddPlayerActivity.class));
                fabOptions.close(false);
            }
        });
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tab_match:
                        setFragment(0);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            rootView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundMatchTab));
                        }else {
                            rootView.setBackgroundColor(getResources().getColor(R.color.backgroundMatchTab));
                        }
                        fabOptions.hideMenuButton(true);
                        fabPlay.show(true);
                        break;
                    case R.id.tab_teams:
                        setFragment(1);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            rootView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundTeamTab));
                        }else {
                            rootView.setBackgroundColor(getResources().getColor(R.color.backgroundTeamTab));
                        }
                        fabOptions.hideMenuButton(true);
                        fabPlay.hide(true);
                        break;
                    case R.id.tab_list:
                        setFragment(2);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            rootView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundListTab));
                        }else {
                            rootView.setBackgroundColor(getResources().getColor(R.color.backgroundListTab));
                        }
                        fabPlay.hide(true);
                        fabOptions.showMenuButton(true);
                        break;
                    case 3:
                        setFragment(3);
                        fabPlay.hide(true);
                        fabOptions.hideMenuButton(true);
                        break;
                }
            }
        });
        setFragment(0);
    }

    private void setFragment(int pos) {
        FragmentManager manager;
        FragmentTransaction transaction;
        switch (pos){
            case 0:
                manager=getSupportFragmentManager();
                transaction=manager.beginTransaction();
                MatchFragment matchFragment=new MatchFragment();
                transaction.replace(R.id.fragment_container,matchFragment);
                transaction.commit();
                break;
            case 1:
                manager=getSupportFragmentManager();
                transaction=manager.beginTransaction();
                TeamFragment teamFragment=new TeamFragment();
                transaction.replace(R.id.fragment_container,teamFragment);
                transaction.commit();
                break;
            case 2:
                manager=getSupportFragmentManager();
                transaction=manager.beginTransaction();
                ListPlayersFragment listFragment=new ListPlayersFragment();
                transaction.replace(R.id.fragment_container,listFragment);
                transaction.commit();
                break;
            case 3:
                manager=getSupportFragmentManager();
                transaction=manager.beginTransaction();
                PayFragment payFragment=new PayFragment();
                transaction.replace(R.id.fragment_container,payFragment);
                transaction.commit();
                break;

        }
    }
}
