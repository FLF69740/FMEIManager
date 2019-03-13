package com.example.fmeimanager.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.example.fmeimanager.models.Fmei;
import com.example.fmeimanager.models.Participant;
import com.example.fmeimanager.models.TeamFmei;
import com.example.fmeimanager.repositories.FmeiDataRepository;
import com.example.fmeimanager.repositories.ParticipantDataRepository;
import com.example.fmeimanager.repositories.TeamFmeiDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TeamViewModel extends ViewModel {

    //REPOSITORIES
    private final FmeiDataRepository mFmeiDataRepository;
    private final ParticipantDataRepository mParticipantDataRepository;
    private final TeamFmeiDataRepository mTeamFmeiDataRepository;
    private final Executor mExecutor;

    //DATA
    @Nullable
    private LiveData<Participant> administrator;

    public TeamViewModel(FmeiDataRepository fmeiDataRepository, ParticipantDataRepository participantDataRepository, TeamFmeiDataRepository teamFmeiDataRepository, Executor executor) {
        mFmeiDataRepository = fmeiDataRepository;
        mParticipantDataRepository = participantDataRepository;
        mTeamFmeiDataRepository = teamFmeiDataRepository;
        mExecutor = executor;
    }

    public void init(long administratorId){
        if (this.administrator != null){
            return;
        }
        administrator = mParticipantDataRepository.getParticipant(administratorId);
    }

    /**
     *  FMEI
     */

    public LiveData<List<Fmei>> getAllFmei() {return mFmeiDataRepository.getAllFmei();}
    public LiveData<Fmei> getFmei(long id) {return mFmeiDataRepository.getFmei(id);}

    public void createFmei(Fmei Fmei){
        mExecutor.execute(()->mFmeiDataRepository.createFmei(Fmei));
    }

    public void updateFmei(Fmei Fmei){
        mExecutor.execute(()->mFmeiDataRepository.updateFmei(Fmei));
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

    /**
     *  TEAM FMEI
     */

    public LiveData<List<TeamFmei>> getAllTeamFmei() {return mTeamFmeiDataRepository.getAllTeamFmei();}
    public LiveData<TeamFmei> getTeamFmei(long id) {return mTeamFmeiDataRepository.getTeamFmei(id);}

    public LiveData<List<TeamFmei>> getTeamsWithLinkParticipant(long participantId) {
        return mTeamFmeiDataRepository.getTeamsWithLinkParticipant(participantId);
    }

    public LiveData<List<TeamFmei>> getTeamsWithLinkFmei(long riskId) {
        return mTeamFmeiDataRepository.getTeamsWithLinkFmei(riskId);
    }

    public void createTeamFmei(TeamFmei TeamFmei){
        mExecutor.execute(()->mTeamFmeiDataRepository.createTeamFmei(TeamFmei));
    }

    public void updateTeamFmei(TeamFmei TeamFmei){
        mExecutor.execute(()->mTeamFmeiDataRepository.updateTeamFmei(TeamFmei));
    }

    public void deleteTeamFmei(long TeamFmeiId){
        mExecutor.execute(()->mTeamFmeiDataRepository.deleteTeamFmei(TeamFmeiId));
    }
}
