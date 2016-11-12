package com.pedrodavidlp.footballmanager.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.view.activity.JoinGroupActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectJoinOrCreateFragment extends Fragment {
    @BindView(R.id.joinGroupBranch) AppCompatButton join;
    @BindView(R.id.createGroupBranch) AppCompatButton create;

    @OnClick(R.id.joinGroupBranch)
    public void setFragment2(){
        ((JoinGroupActivity) getActivity()).setFragment(2);
    }
    @OnClick(R.id.createGroupBranch)
    public void setFragment3(){
        ((JoinGroupActivity) getActivity()).setFragment(3);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_or_create,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
}
