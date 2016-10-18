package com.pedrodavidlp.footballmanager.di.group;

import com.pedrodavidlp.footballmanager.di.Activity;
import com.pedrodavidlp.footballmanager.di.ActivityScope;
import com.pedrodavidlp.footballmanager.domain.interactor.CreateGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.CreateUserUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.JoinGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.repository.GroupRepo;
import com.pedrodavidlp.footballmanager.domain.repository.UserRepo;
import com.pedrodavidlp.footballmanager.presenter.GroupPresenter;
import com.pedrodavidlp.footballmanager.presenter.UserPresenter;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;

import dagger.Module;
import dagger.Provides;

@Module
public class GroupFragmentModule {

    @Provides
    @ActivityScope
    public GroupPresenter provideGroupPresenter(@Group JoinGroupUseCase joinGroupUseCase){
        return new GroupPresenter(null, joinGroupUseCase);
    }

    @Provides
    @ActivityScope
    public UserPresenter provideUserPresenter(@Group CreateUserUseCase createUserUseCase ){
        return new UserPresenter(createUserUseCase);
    }


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
    @Group
    public CreateGroupUseCase provideCreateGroupUseCase(MainThread mainThread,
                                                      Executor executor,
                                                      GroupRepo repository){
        return new CreateGroupUseCase(mainThread,executor,repository);
    }

    @Provides
    @ActivityScope
    @Group
    public CreateUserUseCase provideCreateUserUseCase(MainThread mainThread,
                                                     Executor executor,
                                                     UserRepo repository){
        return new CreateUserUseCase(mainThread,executor,repository);
    }
}
