package com.pedrodavidlp.footballmanager.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.view.activity.JoinGroupActivity;

public class SelectJoinOrCreateFragment extends Fragment {
    AppCompatButton join;
    AppCompatButton create;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_or_create,container,false);

        join = (AppCompatButton) view.findViewById(R.id.joinGroupButton);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((JoinGroupActivity) getActivity()).setFragment(2);
            }
        });
        create = (AppCompatButton) view.findViewById(R.id.createGroupButton);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((JoinGroupActivity) getActivity()).setFragment(3);
            }
        });

        return view;
    }
}
