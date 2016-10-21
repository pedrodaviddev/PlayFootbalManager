package com.pedrodavidlp.footballmanager.di.launcher;

import com.pedrodavidlp.footballmanager.view.activity.LauncherActivity;

import dagger.Component;
import dagger.Subcomponent;

@LauncherScope
@Subcomponent(modules = LauncherModule.class)
public interface LauncherComponent {
    LauncherActivityComponent plus(LauncherActivityModule launcherActivityModule);
}
