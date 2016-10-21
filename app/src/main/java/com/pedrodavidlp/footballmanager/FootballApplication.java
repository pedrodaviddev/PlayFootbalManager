package com.pedrodavidlp.footballmanager;

import android.app.Application;
import android.content.Context;

import com.pedrodavidlp.footballmanager.di.AppComponent;
import com.pedrodavidlp.footballmanager.di.AppModule;
import com.pedrodavidlp.footballmanager.di.DaggerAppComponent;
import com.pedrodavidlp.footballmanager.di.group.GroupComponent;
import com.pedrodavidlp.footballmanager.di.group.GroupModule;
import com.pedrodavidlp.footballmanager.di.launcher.LauncherComponent;
import com.pedrodavidlp.footballmanager.di.launcher.LauncherModule;
import com.pedrodavidlp.footballmanager.di.player.PlayerComponent;
import com.pedrodavidlp.footballmanager.di.player.PlayerModule;

public class FootballApplication extends Application {
    private AppComponent appComponent;
    private PlayerComponent playerComponent;
    private GroupComponent groupComponent;
    private LauncherComponent launcherComponent;

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
    public GroupComponent getGroupComponent(){
        if(groupComponent == null){
           groupComponent = appComponent.plus(new GroupModule());
        }
        return groupComponent;
    }

    public LauncherComponent getLauncherComponent() {
        if(launcherComponent == null){
            launcherComponent = appComponent.plus(new LauncherModule());
        }
        return launcherComponent;
    }


    public void releasePlayerComponent(){
        playerComponent = null;
    }
    public void releaseGroupComponent(){
        groupComponent = null;
    }
    public void releaseLauncherComponent(){
        launcherComponent = null;
    }

    public static FootballApplication get(Context context){
        return (FootballApplication) context;
    }
}
