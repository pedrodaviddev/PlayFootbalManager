package com.pedrodavidlp.footballmanager.di.group;

import android.content.Context;

import com.pedrodavidlp.footballmanager.data.GroupRepository;
import com.pedrodavidlp.footballmanager.data.PlayersRepository;
import com.pedrodavidlp.footballmanager.di.ActivityScope;
import com.pedrodavidlp.footballmanager.di.player.PlayerScope;
import com.pedrodavidlp.footballmanager.domain.interactor.JoinUserUseCase;
import com.pedrodavidlp.footballmanager.domain.repository.GroupRepo;
import com.pedrodavidlp.footballmanager.domain.repository.PlayersRepo;
import com.pedrodavidlp.footballmanager.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class GroupModule {
    @Provides
    @GroupScope
    public GroupRepo provideGroupRepository(Context context){
        return new GroupRepository(context);
    }
}
