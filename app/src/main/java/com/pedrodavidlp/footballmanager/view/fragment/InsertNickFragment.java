package com.pedrodavidlp.footballmanager.view.fragment;

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
import com.pedrodavidlp.footballmanager.di.group.GroupFragmentModule;
import com.pedrodavidlp.footballmanager.presenter.UserPresenter;
import com.pedrodavidlp.footballmanager.view.ViewTry;
import com.pedrodavidlp.footballmanager.view.activity.JoinGroupActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InsertNickFragment extends Fragment implements ViewTry {
    @BindView(R.id.insertNickName) TextInputEditText insertNick;
    @BindView(R.id.insertNickNameLayout) TextInputLayout insertNickLayout;

    @Inject UserPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_nickname,container,false);

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
    public void initUi() {
        ((JoinGroupActivity) getActivity()).setListenerToFab(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.insertNickname(insertNick.getText().toString());
                ((JoinGroupActivity) getActivity()).startAnimationFab();
            }
        });
    }

    @Override
    public void error(Exception e) {

    }

    @Override
    public void succeed() {
        ((JoinGroupActivity) getActivity()).setFragment(1);
    }

    @Override
    public void failed() {
        ((JoinGroupActivity) getActivity()).stopAnimationFab();
        insertNickLayout.setError("El nombre de usuario ya existe");
        insertNick.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                insertNickLayout.setError(null);
                insertNick.removeTextChangedListener(this);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
