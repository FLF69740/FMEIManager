package com.FMEA.fmeimanager.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.FMEA.fmeimanager.database.Participant;
import com.FMEA.fmeimanager.repositories.ParticipantDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class ParticipantViewModel extends ViewModel {

    //REPOSITORIES
    private final ParticipantDataRepository mParticipantDataRepository;
    private final Executor mExecutor;

    //DATA
    @Nullable
    private LiveData<Participant> administrator;

    public ParticipantViewModel(ParticipantDataRepository participantDataRepository, Executor executor) {
        mParticipantDataRepository = participantDataRepository;
        mExecutor = executor;
    }

    public void init(long administratorId){
        if (this.administrator != null){
            return;
        }
        administrator = mParticipantDataRepository.getParticipant(administratorId);
    }

    /**
     *  PARTICIPANT
     */

    public LiveData<List<Participant>> getAllParticipant() {return mParticipantDataRepository.getAllParticipant();}
    public LiveData<Participant> getParticipant(long id) {return mParticipantDataRepository.getParticipant(id);}

    public void createParticipant(Participant Participant){
        mExecutor.execute(()->mParticipantDataRepository.createParticipant(Participant));
    }

    public void updateParticipant(Participant Participant){
        mExecutor.execute(()->mParticipantDataRepository.updateParticipant(Participant));
    }
}
