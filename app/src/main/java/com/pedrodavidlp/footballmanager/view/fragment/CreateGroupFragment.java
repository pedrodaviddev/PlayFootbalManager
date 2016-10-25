package com.pedrodavidlp.footballmanager.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.ScrollView;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateGroupFragment extends Fragment implements ViewLogin{
    @BindView(R.id.groupNameLayout) TextInputLayout groupNameLayout;
    @BindView(R.id.groupName) TextInputEditText groupName;
    @BindView(R.id.passGroupLayout) TextInputLayout groupPassLayout;
    @BindView(R.id.passGroup) TextInputEditText groupPass;
    @BindView(R.id.confirmPassLayout) TextInputLayout confirmPassLayout;
    @BindView(R.id.confirmPass) TextInputEditText confirmPass;
    @BindView(R.id.selectNumberPlayers) NumberPicker numberPicker;
    @BindView(R.id.selectDateButton) AppCompatButton selectDateButton;
    @Inject GroupPresenter presenter;

    @OnClick(R.id.selectDateButton)
    public void selectDate(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_group,container,false);

        initDagger();

        ButterKnife.bind(this,view);

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
        ((JoinGroupActivity) getActivity()).setFabBehavior();
        numberPicker.setMaxValue(24);
        numberPicker.setMinValue(0);
        ((JoinGroupActivity) getActivity()).stopAnimationFab();
        ((JoinGroupActivity) getActivity()).setListenerToFab(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPass()) {
                    ((JoinGroupActivity) getActivity()).startAnimationFab();
                    presenter.createGroup(new Group(groupName.getText().toString(),groupPass.getText().toString(),numberPicker.getValue(),0)
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
