package com.example.fmeimanager.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.example.fmeimanager.models.CorrectiveAction;
import com.example.fmeimanager.models.Participant;
import com.example.fmeimanager.models.Processus;
import com.example.fmeimanager.models.Risk;
import com.example.fmeimanager.repositories.CorrectiveActionDataRepository;
import com.example.fmeimanager.repositories.FmeiDataRepository;
import com.example.fmeimanager.repositories.ParticipantDataRepository;
import com.example.fmeimanager.repositories.ProcessusDataRepository;
import com.example.fmeimanager.repositories.RiskDataRepository;
import com.example.fmeimanager.repositories.TeamFmeiDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class ProcessusViewModel extends ViewModel {

    //REPOSITORIES
    private final CorrectiveActionDataRepository mCorrectiveActionDataRepository;
    private final ParticipantDataRepository mParticipantDataRepository;
    private final ProcessusDataRepository mProcessusDataRepository;
    private final RiskDataRepository mRiskDataRepository;
    private final Executor mExecutor;

    //DATA
    @Nullable
    private LiveData<Participant> administrator;

    public ProcessusViewModel(CorrectiveActionDataRepository correctiveActionDataRepository, ParticipantDataRepository participantDataRepository,
                              ProcessusDataRepository processusDataRepository, RiskDataRepository riskDataRepository, Executor executor) {
        mCorrectiveActionDataRepository = correctiveActionDataRepository;
        mParticipantDataRepository = participantDataRepository;
        mProcessusDataRepository = processusDataRepository;
        mRiskDataRepository = riskDataRepository;
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

    public LiveData<List<CorrectiveAction>> getCorrectiveActionsListForRisk(long riskId) {
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
     *  PROCESSUS
     */

    public LiveData<List<Processus>> getAllProcessus() {return mProcessusDataRepository.getAllProcessus();}
    public LiveData<Processus> getProcessus(long id) {return mProcessusDataRepository.getProcessus(id);}

    public LiveData<List<Processus>> getProcessussListForFmei(long participantId) {
        return mProcessusDataRepository.getProcessussListForFmei(participantId);
    }

    public void createProcessus(Processus Processus){
        mExecutor.execute(()->mProcessusDataRepository.createProcessus(Processus));
    }

    public void updateProcessus(Processus Processus){
        mExecutor.execute(()->mProcessusDataRepository.updateProcessus(Processus));
    }

    public void deleteProcessus(long ProcessusId){
        mExecutor.execute(()->mProcessusDataRepository.deleteProcessus(ProcessusId));
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
}
