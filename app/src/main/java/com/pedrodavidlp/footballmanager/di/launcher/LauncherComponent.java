package com.pedrodavidlp.footballmanager.di.launcher;

import dagger.Subcomponent;

@LauncherScope
@Subcomponent(modules = LauncherModule.class)
public interface LauncherComponent {
    LauncherActivityComponent plus(LauncherActivityModule launcherActivityModule);
}
