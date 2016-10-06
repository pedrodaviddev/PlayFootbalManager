package com.pedrodavidlp.footballmanager.di.modules;

import android.content.Context;

import com.pedrodavidlp.footballmanager.di.Activity;

import dagger.Provides;

public class ActivityModule {
    private final Context mContext;

    public ActivityModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Activity
    Context provideActivityContext(){
        return mContext;
    }
}
