package com.pedrodavidlp.footballmanager.di.launcher;

import android.content.Context;

import com.pedrodavidlp.footballmanager.data.UserRepository;
import com.pedrodavidlp.footballmanager.domain.repository.UserRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class LauncherModule {
    @Provides
    @LauncherScope
    UserRepo provideUserRepo(Context context){
        return new UserRepository(context);
    }
}
