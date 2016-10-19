package com.pedrodavidlp.footballmanager.di.player;

import com.pedrodavidlp.footballmanager.di.FragmentScope;
import com.pedrodavidlp.footballmanager.view.fragment.ListPlayersFragment;
import com.pedrodavidlp.footballmanager.view.fragment.MatchFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = PlayerFragmentModule.class)
public interface PlayerFragmentComponent {
    ListPlayersFragment inject(ListPlayersFragment listPlayersFragment);
    MatchFragment inject(MatchFragment matchFragment);
}
