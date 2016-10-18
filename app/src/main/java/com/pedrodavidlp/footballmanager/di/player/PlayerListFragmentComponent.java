package com.pedrodavidlp.footballmanager.di.player;

import com.pedrodavidlp.footballmanager.di.ActivityScope;
import com.pedrodavidlp.footballmanager.view.fragment.ListPlayersFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = PlayerListFragmentModule.class)
public interface PlayerListFragmentComponent {
    ListPlayersFragment inject(ListPlayersFragment listPlayersFragment);
}
