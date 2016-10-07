package com.pedrodavidlp.footballmanager.di.player;

import com.pedrodavidlp.footballmanager.di.ActivityScope;
import com.pedrodavidlp.footballmanager.view.fragment.ListPlayersFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = PlayerListActivityModule.class)
public interface PlayerListActivityComponent {
    ListPlayersFragment inject(ListPlayersFragment listPlayersFragment);
}
