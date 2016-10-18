package com.pedrodavidlp.footballmanager.di.group;

import com.pedrodavidlp.footballmanager.di.ActivityScope;
import com.pedrodavidlp.footballmanager.view.fragment.JoinGroupFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = JoinGroupFragmentModule.class)
public interface JoinGroupFragmentComponent {
    JoinGroupFragment inject(JoinGroupFragment joinGroupFragment);
}
