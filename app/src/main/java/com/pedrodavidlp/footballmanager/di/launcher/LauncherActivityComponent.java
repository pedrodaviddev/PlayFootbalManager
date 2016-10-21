package com.pedrodavidlp.footballmanager.di.launcher;

import com.pedrodavidlp.footballmanager.view.activity.LauncherActivity;

import dagger.Subcomponent;

@LauncherScope
@Subcomponent(modules = LauncherActivityModule.class)
public interface LauncherActivityComponent {
    LauncherActivity inject(LauncherActivity launcherActivity);
}
