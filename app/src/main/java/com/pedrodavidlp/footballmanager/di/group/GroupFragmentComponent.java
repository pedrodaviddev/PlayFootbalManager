package com.pedrodavidlp.footballmanager.di.group;

import com.pedrodavidlp.footballmanager.di.ActivityScope;
import com.pedrodavidlp.footballmanager.view.fragment.CreateGroupFragment;
import com.pedrodavidlp.footballmanager.view.fragment.InsertNickFragment;
import com.pedrodavidlp.footballmanager.view.fragment.JoinGroupFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = GroupFragmentModule.class)
public interface GroupFragmentComponent {
    JoinGroupFragment inject(JoinGroupFragment joinGroupFragment);
    InsertNickFragment inject(InsertNickFragment insertNickFragment);
    CreateGroupFragment inject(CreateGroupFragment createGroupFragment);
}
