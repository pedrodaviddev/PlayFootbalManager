package com.pedrodavidlp.footballmanager;

import android.app.Application;
import android.content.Context;

import com.pedrodavidlp.footballmanager.di.AppComponent;
import com.pedrodavidlp.footballmanager.di.AppModule;
import com.pedrodavidlp.footballmanager.di.DaggerAppComponent;
import com.pedrodavidlp.footballmanager.di.player.PlayerComponent;
import com.pedrodavidlp.footballmanager.di.player.PlayerModule;

public class FootballApplication extends Application {
    private AppComponent appComponent;
    private PlayerComponent playerComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

    public PlayerComponent getPlayerComponent(){
        if(playerComponent == null){
            playerComponent = appComponent.plus(new PlayerModule());
        }
        return playerComponent;
    }

    public void releasePlayerComponent(){
        playerComponent = null;
    }

    public static FootballApplication get(Context context){
        return (FootballApplication) context;
    }
}
