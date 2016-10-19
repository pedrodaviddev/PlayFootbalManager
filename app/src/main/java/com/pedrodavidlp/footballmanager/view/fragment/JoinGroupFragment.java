package com.pedrodavidlp.footballmanager.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrodavidlp.footballmanager.FootballApplication;
import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.data.UserRepository;
import com.pedrodavidlp.footballmanager.di.group.GroupFragmentModule;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.presenter.GroupPresenter;
import com.pedrodavidlp.footballmanager.view.ViewLogin;
import com.pedrodavidlp.footballmanager.view.activity.JoinGroupActivity;
import com.pedrodavidlp.footballmanager.view.activity.MainActivity;

import javax.inject.Inject;

public class JoinGroupFragment extends Fragment implements ViewLogin{
    private TextInputLayout nameGroupLayout;
    private TextInputLayout passGroupLayout;
    private TextInputEditText nameGroup;
    private TextInputEditText passGroup;

    @Inject GroupPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_group,container,false);

        initDagger();

        nameGroup = (TextInputEditText) view.findViewById(R.id.nameGroupToJoin);
        passGroup = (TextInputEditText) view.findViewById(R.id.passGroupToJoin);
        nameGroupLayout = (TextInputLayout) view.findViewById(R.id.nameGroupToJoinLayout);
        passGroupLayout = (TextInputLayout) view.findViewById(R.id.passGroupToJoinLayout);

        presenter.setView(this);
        presenter.init();
        return view;
    }

    private void initDagger() {
        FootballApplication.get(getContext().getApplicationContext())
                .getGroupComponent()
                .plus(new GroupFragmentModule())
                .inject(this);
    }


    @Override
    public void initUi() {
        ((JoinGroupActivity) getActivity()).stopAnimationFab();
        ((JoinGroupActivity) getActivity()).setListenerToFab(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((JoinGroupActivity) getActivity()).startAnimationFab();
                presenter.joinGroup(new Group(nameGroup.getText().toString(),passGroup.getText().toString()),
                        new Player(UserRepository.currentNickname,0,false,false));
            }
        });
    }

    @Override
    public void error(Exception e) {

    }

    @Override
    public void success() {
        startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
        getActivity().finish();
    }

    @Override
    public void wrongId() {
        nameGroupLayout.setError("El grupo no existe");
        nameGroup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nameGroupLayout.setError(null);
                nameGroup.removeTextChangedListener(this);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void wrongPass() {
        passGroupLayout.setError("La contraseña es incorrecta");
        passGroup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passGroupLayout.setError(null);
                passGroup.removeTextChangedListener(this);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
