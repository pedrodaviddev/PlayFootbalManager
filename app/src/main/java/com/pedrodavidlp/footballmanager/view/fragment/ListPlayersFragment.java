package com.pedrodavidlp.footballmanager.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrodavidlp.footballmanager.R;

/**
 * Created by PedroDavidLP on 14/9/16.
 */
public class ListPlayersFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_list,container,false);

        return rootView;
    }
}
