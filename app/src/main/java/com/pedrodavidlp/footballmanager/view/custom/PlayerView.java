package com.pedrodavidlp.footballmanager.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pedrodavidlp.footballmanager.R;

/**
 * TODO: document your custom view class.
 */
public class PlayerView extends LinearLayout {
    View rootView;
    TextView namePlayer;
    ImageView imageView;


    public PlayerView(Context context) {
        super(context);
        init(context);
    }

    public PlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        rootView = inflate(context,R.layout.custom_player,this);
        namePlayer = (TextView) rootView.findViewById(R.id.nameOfPlayerInMatch);
        imageView = (ImageView) rootView.findViewById(R.id.imagePlayerInMatch);
    }

    public void setNamePlayer(String name){
        namePlayer.setText(name);
    }
    public void setColor(int color){
        switch (color){
            case 0:

        }
    }
}

