package com.pedrodavidlp.footballmanager.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pedrodavidlp.footballmanager.R;

public class LaucherActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laucher);

        SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        if (!prefs.getBoolean("Logged",false)){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("Logged",false);
            editor.commit();
            intent = new Intent(getApplicationContext(),LoginActivity.class);
        } else {
            intent = new Intent(getApplicationContext(), MainActivity.class);
        }

        startActivity(intent);
        finish();

    }
}
