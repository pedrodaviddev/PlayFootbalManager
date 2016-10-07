package com.pedrodavidlp.footballmanager.di.player;

import dagger.Subcomponent;

@PlayerScope
@Subcomponent(modules = PlayerModule.class)
public interface PlayerComponent {
    PlayerListActivityComponent plus(PlayerListActivityModule activityModule);
}
