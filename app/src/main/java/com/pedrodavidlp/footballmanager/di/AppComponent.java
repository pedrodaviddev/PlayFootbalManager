package com.pedrodavidlp.footballmanager.di;


import com.pedrodavidlp.footballmanager.di.group.GroupComponent;
import com.pedrodavidlp.footballmanager.di.group.GroupModule;
import com.pedrodavidlp.footballmanager.di.player.PlayerComponent;
import com.pedrodavidlp.footballmanager.di.player.PlayerModule;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    PlayerComponent plus(PlayerModule playerModule);
    GroupComponent plus(GroupModule groupModule);
}
