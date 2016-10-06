package com.pedrodavidlp.footballmanager.di.components;

import android.content.Context;

import com.pedrodavidlp.footballmanager.di.Activity;
import com.pedrodavidlp.footballmanager.di.modules.ActivityModule;
import com.pedrodavidlp.footballmanager.di.AppComponent;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Context context();
}
