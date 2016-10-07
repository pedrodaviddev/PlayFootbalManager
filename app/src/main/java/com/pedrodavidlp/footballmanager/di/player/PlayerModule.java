package com.pedrodavidlp.footballmanager.di.player;

import com.pedrodavidlp.footballmanager.data.PlayersRepository;
import com.pedrodavidlp.footballmanager.domain.repository.PlayersRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayerModule {

    @Provides
    @PlayerScope
    public PlayersRepo providePlayerRepository(){
        return new PlayersRepository();
    }
}
