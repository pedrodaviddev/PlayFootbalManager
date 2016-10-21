package com.pedrodavidlp.footballmanager.domain.interactor;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedrodavidlp.footballmanager.R;
import com.pedrodavidlp.footballmanager.data.GroupRepository;
import com.pedrodavidlp.footballmanager.domain.model.Player;
import com.pedrodavidlp.footballmanager.domain.repository.PlayersRepo;
import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.Interactor;
import com.tonilopezmr.interactorexecutor.MainThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PedroDavidLP on 15/9/16.
 */
public class GetListUseCase extends UseCase<GetListUseCase.Callback>{
    public interface Callback{
        void onListLoaded(List<Player> list);
        void onError(Exception e);
    }

    private PlayersRepo repository;

    public GetListUseCase(MainThread mainThread, Executor executor, PlayersRepo repository) {
        super(mainThread,executor);
        this.repository = repository;
    }

    @Override
    public void run() {
      repository.loadList(callback);
    }


}
