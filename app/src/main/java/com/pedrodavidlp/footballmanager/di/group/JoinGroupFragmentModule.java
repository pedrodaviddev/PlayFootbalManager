package com.pedrodavidlp.footballmanager.di.group;

import com.pedrodavidlp.footballmanager.di.ActivityScope;
import com.pedrodavidlp.footballmanager.domain.interactor.JoinGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.repository.GroupRepo;
import com.pedrodavidlp.footballmanager.presenter.GroupPresenter;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;

import dagger.Module;
import dagger.Provides;

@Module
public class JoinGroupFragmentModule {
    @Provides
    @ActivityScope
    @Group
    public JoinGroupUseCase provideJoinGroupUseCase(MainThread mainThread,
                                                    Executor executor,
                                                    GroupRepo repository){
        return new JoinGroupUseCase(mainThread,executor,repository);
    }

    @Provides
    @ActivityScope
    public GroupPresenter provideGroupPresenter(@Group JoinGroupUseCase joinGroupUseCase){
        return new GroupPresenter(null, joinGroupUseCase);
    }
}
