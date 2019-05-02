package com.example.fmeimanager.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.example.fmeimanager.database.CorrectiveAction;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.Risk;
import com.example.fmeimanager.database.TeamFmei;
import com.example.fmeimanager.repositories.CorrectiveActionDataRepository;
import com.example.fmeimanager.repositories.ParticipantDataRepository;
import com.example.fmeimanager.repositories.RiskDataRepository;
import com.example.fmeimanager.repositories.TeamFmeiDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class RiskViewModel extends ViewModel {

    private final RiskDataRepository mRiskDataRepository;
    private final CorrectiveActionDataRepository mCorrectiveActionDataRepository;
    private final ParticipantDataRepository mParticipantDataRepository;
    private final Executor mExecutor;
    private final TeamFmeiDataRepository mTeamFmeiDataRepository;

    //DATA
    @Nullable
    private LiveData<Participant> administrator;

    public RiskViewModel(RiskDataRepository riskDataRepository, CorrectiveActionDataRepository correctiveActionDataRepository,
                         ParticipantDataRepository participantDataRepository, TeamFmeiDataRepository teamFmeiDataRepository, Executor executor) {
        mRiskDataRepository = riskDataRepository;
        mCorrectiveActionDataRepository = correctiveActionDataRepository;
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
     *  CORRECTIVE ACTION
     */

    public LiveData<List<CorrectiveAction>> getAllCorrectiveAction() {return mCorrectiveActionDataRepository.getAllCorrectiveAction();}
    public LiveData<CorrectiveAction> getCorrectiveAction(long id) {return mCorrectiveActionDataRepository.getCorrectiveAction(id);}

    public LiveData<List<CorrectiveAction>> getCorrectiveActionsListForParticipant(long participantId) {
        return mCorrectiveActionDataRepository.getCorrectiveActionsListForParticipant(participantId);
    }

    public LiveData<CorrectiveAction> getCorrectiveActionsListForRisk(long riskId) {
        return mCorrectiveActionDataRepository.getCorrectiveActionsListForRisk(riskId);
    }

    public void createCorrectiveAction(CorrectiveAction correctiveAction){
        mExecutor.execute(()->mCorrectiveActionDataRepository.createCorrectiveAction(correctiveAction));
    }

    public void updateCorrectiveAction(CorrectiveAction correctiveAction){
        mExecutor.execute(()->mCorrectiveActionDataRepository.updateCorrectiveAction(correctiveAction));
    }

    public void deleteCorrectiveAction(long correctiveActionId){
        mExecutor.execute(()->mCorrectiveActionDataRepository.deleteCorrectiveAction(correctiveActionId));
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
     *  RISK
     */

    public LiveData<List<Risk>> getAllRisk() {return mRiskDataRepository.getAllRisk();}
    public LiveData<Risk> getRisk(long id) {return mRiskDataRepository.getRisk(id);}

    public LiveData<List<Risk>> getRisksListForParticipant(long participantId) {
        return mRiskDataRepository.getRisksListForParticipant(participantId);
    }

    public LiveData<List<Risk>> getRisksListForProcessus(long processusId) {
        return mRiskDataRepository.getRisksListForProcessus(processusId);
    }

    public void createRisk(Risk Risk){
        mExecutor.execute(()->mRiskDataRepository.createRisk(Risk));
    }

    public void updateRisk(Risk Risk){
        mExecutor.execute(()->mRiskDataRepository.updateRisk(Risk));
    }

    public void deleteRisk(long RiskId){
        mExecutor.execute(()->mRiskDataRepository.deleteRisk(RiskId));
    }

    /**
     *  TEAM FMEI
     */

    public LiveData<List<TeamFmei>> getAllTeamFmei() {return mTeamFmeiDataRepository.getAllTeamFmei();}
    public LiveData<TeamFmei> getTeamFmei(long id) {return mTeamFmeiDataRepository.getTeamFmei(id);}

    public LiveData<List<TeamFmei>> getTeamsWithLinkParticipant(long participantId) {
        return mTeamFmeiDataRepository.getTeamsWithLinkParticipant(participantId);
    }

    public LiveData<List<TeamFmei>> getTeamsWithLinkFmei(long fmeiId) {
        return mTeamFmeiDataRepository.getTeamsWithLinkFmei(fmeiId);
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
