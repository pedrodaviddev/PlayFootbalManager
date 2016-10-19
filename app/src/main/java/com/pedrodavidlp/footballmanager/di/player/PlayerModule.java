package com.pedrodavidlp.footballmanager.di.player;

import android.content.Context;

import com.pedrodavidlp.footballmanager.data.MatchRepository;
import com.pedrodavidlp.footballmanager.data.PlayersRepository;
import com.pedrodavidlp.footballmanager.domain.repository.MatchRepo;
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

    @Provides
    @PlayerScope
    public MatchRepo provideMatchRepository(Context context){
        return new MatchRepository(context);
    }

}
