package com.pedrodavidlp.footballmanager.view.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.pedrodavidlp.footballmanager.R;

public class JoinGroupActivity extends AppCompatActivity {
    private Button buttonCreate;
    private Button buttonJoin;
    private LinearLayout containerInputs;
    private TextInputEditText nameGroup;
    private TextInputEditText passGroup;
    private TextInputEditText confirmpassGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);

        buttonCreate = (Button) findViewById(R.id.createGroupButton);
        buttonJoin = (Button) findViewById(R.id.joinGroupButton);
        containerInputs = (LinearLayout) findViewById(R.id.containerInputsGroup);
        nameGroup = (TextInputEditText) findViewById(R.id.nameGroupInput);
        passGroup = (TextInputEditText) findViewById(R.id.passwordGroupInput);
        confirmpassGroup = (TextInputEditText) findViewById(R.id.confirmPasswordInput);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateUi();
            }
        });
        buttonJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJoinUi();
            }
        });

    }

    private void showJoinUi() {

    }

    private void showCreateUi() {
        containerInputs.setVisibility(View.VISIBLE);
        buttonJoin.setVisibility(View.GONE);
        nameGroup.setHint("Nombre de tu grupo");
        passGroup.setHint("Pon una contraseña");
        confirmpassGroup.setHint("Confirma tu contraseña");
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkNameAndPasswords()){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        });
        buttonCreate.setText("Crear!");
    }

    private boolean checkNameAndPasswords() {
        return true;
    }
}
