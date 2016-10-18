package com.pedrodavidlp.footballmanager.di.group;

import dagger.Subcomponent;

@GroupScope
@Subcomponent(modules = GroupModule.class)
public interface GroupComponent {
    GroupFragmentComponent plus(GroupFragmentModule activityModule);
}
