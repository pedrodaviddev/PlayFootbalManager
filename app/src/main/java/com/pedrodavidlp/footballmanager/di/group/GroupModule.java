package com.pedrodavidlp.footballmanager.di.group;

import android.content.Context;

import com.pedrodavidlp.footballmanager.data.GroupRepository;
import com.pedrodavidlp.footballmanager.data.UserRepository;
import com.pedrodavidlp.footballmanager.domain.repository.GroupRepo;
import com.pedrodavidlp.footballmanager.domain.repository.UserRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class GroupModule {

    @Provides
    @GroupScope
    public GroupRepo provideGroupRepository(Context context){
        return new GroupRepository(context);
    }

    @Provides
    @GroupScope
    public UserRepo provideUserRepository(Context context){
        return new UserRepository(context);
    }
}
