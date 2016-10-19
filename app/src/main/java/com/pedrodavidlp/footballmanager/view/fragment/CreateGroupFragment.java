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

public class CreateGroupFragment extends Fragment implements ViewLogin{
    private TextInputLayout groupNameLayout;
    private TextInputEditText groupName;
    private TextInputLayout groupPassLayout;
    private TextInputEditText groupPass;
    private TextInputLayout confirmPassLayout;
    private TextInputEditText confirmPass;

    @Inject GroupPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_group,container,false);

        initDagger();

        groupNameLayout = (TextInputLayout) view.findViewById(R.id.groupNameLayout);
        groupName = (TextInputEditText) view.findViewById(R.id.groupName);
        groupPassLayout = (TextInputLayout) view.findViewById(R.id.passGroupLayout);
        groupPass = (TextInputEditText) view.findViewById(R.id.passGroup);
        confirmPassLayout = (TextInputLayout) view.findViewById(R.id.confirmPassLayout);
        confirmPass = (TextInputEditText) view.findViewById(R.id.confirmPass);

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
    public void success() {
        startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
        getActivity().finish();
    }

    @Override
    public void initUi() {
        ((JoinGroupActivity) getActivity()).stopAnimationFab();
        ((JoinGroupActivity) getActivity()).setListenerToFab(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPass()) {
                    ((JoinGroupActivity) getActivity()).startAnimationFab();
                    presenter.createGroup(new Group(groupName.getText().toString(),groupPass.getText().toString())
                            ,new Player(UserRepository.currentNickname,0,true,true));
                } else {
                    passNotMatch();
                }
            }
        });
    }

    private void passNotMatch() {
        groupPass.getText().clear();
        confirmPass.getText().clear();
        groupPassLayout.setError("Las contraseñas no coinciden");
        groupPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                groupPass.setError(null);
                groupPass.removeTextChangedListener(this);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean checkPass() {
        return groupPass.getText().toString().equals(confirmPass.getText().toString());
    }

    @Override
    public void error(Exception e) {

    }

    @Override
    public void wrongId() {
        groupNameLayout.setError("El nombre del grupo ya existe");
    }

    @Override
    public void wrongPass() {

    }
}
