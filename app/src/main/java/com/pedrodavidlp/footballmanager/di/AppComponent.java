package com.pedrodavidlp.footballmanager.di;


import com.pedrodavidlp.footballmanager.di.group.GroupComponent;
import com.pedrodavidlp.footballmanager.di.group.GroupModule;
import com.pedrodavidlp.footballmanager.di.launcher.LauncherComponent;
import com.pedrodavidlp.footballmanager.di.launcher.LauncherModule;
import com.pedrodavidlp.footballmanager.di.player.PlayerComponent;
import com.pedrodavidlp.footballmanager.di.player.PlayerModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    LauncherComponent plus(LauncherModule launcherModule);
    PlayerComponent plus(PlayerModule playerModule);
    GroupComponent plus(GroupModule groupModule);
}
