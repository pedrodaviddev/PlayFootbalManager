package com.pedrodavidlp.footballmanager.view.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.data.GroupRepository;
import com.pedrodavidlp.footballmanager.domain.interactor.CreateGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.GetCurrentPlayerUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.JoinGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.interactor.SearchGroupUseCase;
import com.pedrodavidlp.footballmanager.domain.model.Group;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.GroupRepo;
import com.pedrodavidlp.footballmanager.presenter.GroupPresenter;
import com.pedrodavidlp.footballmanager.view.ViewQuery;
import com.pedrodavidlp.footballmanager.view.executor.MainThreadImp;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;
import com.tonilopezmr.interactorexecutor.ThreadExecutor;

import java.util.ArrayList;

public class JoinGroupActivity extends AppCompatActivity implements ViewQuery<Group> {
    private Button buttonCreate;
    private Button buttonJoin;
    private LinearLayout containerInputs;
    private TextInputEditText nameGroup;
    private TextInputEditText passGroup;
    private TextInputEditText confirmpassGroup;
    private GroupPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);

        Executor executor = new ThreadExecutor();
        MainThread mainThread = new MainThreadImp();
        GroupRepo repository = new GroupRepository();
        CreateGroupUseCase createGroupUseCase = new CreateGroupUseCase(mainThread,executor,repository);
        JoinGroupUseCase joinGroupUseCase = new JoinGroupUseCase(mainThread,executor,repository);
        SearchGroupUseCase searchGroupUseCase = new SearchGroupUseCase(mainThread,executor);
        GetCurrentPlayerUseCase getCurrentPlayerUseCase = new GetCurrentPlayerUseCase(mainThread,executor);
        presenter = new GroupPresenter(joinGroupUseCase,searchGroupUseCase,createGroupUseCase,getCurrentPlayerUseCase);

        presenter.setView(this);
        presenter.init();
    }

    @Override
    public void initUi() {
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
                   presenter.createGroup(new Group(new ArrayList<Player>(),nameGroup.getText().toString(),passGroup.getText().toString()));
                }
            }
        });
        buttonCreate.setText("Crear!");
    }

    private boolean checkNameAndPasswords() {
        return true;
    }

    @Override
    public void successfulAccess() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    @Override
    public void successfulSearch(Group group) {

    }


    @Override
    public void error(Exception e) {

    }
}
