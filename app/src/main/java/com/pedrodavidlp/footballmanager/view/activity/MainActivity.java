package com.pedrodavidlp.footballmanager.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.clans.fab.FloatingActionMenu;
import com.pedrodavidlp.footballmanager.FootballApplication;
import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.data.GroupRepository;
import com.pedrodavidlp.footballmanager.view.fragment.ListPlayersFragment;
import com.pedrodavidlp.footballmanager.view.fragment.MatchFragment;
import com.pedrodavidlp.footballmanager.view.fragment.PayFragment;
import com.pedrodavidlp.footballmanager.view.fragment.TeamFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bottomBar) BottomBar bottomBar;
    @BindView(R.id.fabOptions) FloatingActionMenu fabOptions;
    @BindView(R.id.fabPlay) com.github.clans.fab.FloatingActionButton fabPlay;
    @BindView(R.id.rootActivity) RelativeLayout rootView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(GroupRepository.currentGroup.getId());

        ButterKnife.bind(this);

        if(getIntent().getBooleanExtra("admin",false)){
            bottomBar.getTabAtPosition(3).setVisibility(View.VISIBLE);
        } else {
            bottomBar.getTabAtPosition(3).setVisibility(View.GONE);
        }


        fabPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchFragment matchFragment =(MatchFragment) getSupportFragmentManager().findFragmentByTag("FRAGMENT_MATCH");
                matchFragment.changeStateMatch();
            }
        });



        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tab_match:
                        setFragment(0);
                        if  (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
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
                        fabOptions.hideMenuButton(true);
                        break;
                    case R.id.tab_pay:
                        setFragment(3);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            rootView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundPayTab));
                        }else {
                            rootView.setBackgroundColor(getResources().getColor(R.color.colorPayTab));
                        }
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
                transaction.replace(R.id.fragment_container,matchFragment,"FRAGMENT_MATCH");
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

    public void setFabMenu(){
        fabOptions.showMenuButton(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FootballApplication app = FootballApplication.get(getApplicationContext());
        app.releasePlayerComponent();
    }
}
