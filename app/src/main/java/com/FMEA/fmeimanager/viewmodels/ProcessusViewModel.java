package com.FMEA.fmeimanager.viewmodels;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import com.FMEA.fmeimanager.database.CorrectiveAction;
import com.FMEA.fmeimanager.database.Participant;
import com.FMEA.fmeimanager.database.Processus;
import com.FMEA.fmeimanager.database.Risk;
import com.FMEA.fmeimanager.models.ProcessusPanel;
import com.FMEA.fmeimanager.models.ProcessusPanelCreator;
import com.FMEA.fmeimanager.repositories.CorrectiveActionDataRepository;
import com.FMEA.fmeimanager.repositories.ParticipantDataRepository;
import com.FMEA.fmeimanager.repositories.ProcessusDataRepository;
import com.FMEA.fmeimanager.repositories.RiskDataRepository;
import java.util.ArrayList;
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

    public LiveData<CorrectiveAction> getCorrectiveAction(long id) {return mCorrectiveActionDataRepository.getCorrectiveAction(id);}

    /**
     *  PARTICIPANT
     */

    public LiveData<List<Participant>> getAllParticipant() {return mParticipantDataRepository.getAllParticipant();}

    public LiveData<Participant> getParticipant(long id) {return mParticipantDataRepository.getParticipant(id);}

    public void updateParticipant(Participant Participant){
        mExecutor.execute(()->mParticipantDataRepository.updateParticipant(Participant));
    }

    /**
     *  PROCESSUS
     */

    public LiveData<List<Processus>> getProcessussListForFmei(long fmeiId) {
        return mProcessusDataRepository.getProcessussListForFmei(fmeiId);
    }

    public void createProcessus(Processus Processus){
        mExecutor.execute(()->mProcessusDataRepository.createProcessus(Processus));
    }

    public void updateProcessus(Processus Processus){
        mExecutor.execute(()->mProcessusDataRepository.updateProcessus(Processus));
    }

    /**
     *  RISK
     */

    public LiveData<Risk> getRisk(long id) {return mRiskDataRepository.getRisk(id);}

    public void createRisk(Risk Risk){
        mExecutor.execute(()->mRiskDataRepository.createRisk(Risk));
    }

    /**
     *  PROCESSUS PANELS
     */

    //GET list of processus
    public LiveData<List<ProcessusPanel>> theFirstLiveData(long id) {
        return Transformations.switchMap(mProcessusDataRepository.getProcessussListForFmei(id), input ->
                theSecondPanelLiveData(ProcessusPanelCreator.createCreator(input, new ProcessusPanelCreator())));
    }

    //GET risk of designed processus
    private LiveData<List<ProcessusPanel>> theSecondPanelLiveData(ProcessusPanelCreator creator) {
        return Transformations.switchMap(mRiskDataRepository.getAllRisk(), new Function<List<Risk>, LiveData<List<ProcessusPanel>>>() {
            @Override
            public LiveData<List<ProcessusPanel>> apply(List<Risk> input) {
                List<ProcessusPanel> processusPanels = new ArrayList<>();
                return theThirdPanelLiveData(ProcessusPanelCreator.incubeRiskIntoPanel(processusPanels, creator.getProcessusList(), input));
            }
        });
    }

    //GET corrective action for each risk
    private LiveData<List<ProcessusPanel>> theThirdPanelLiveData(List<ProcessusPanel> processusPanels){
        return Transformations.switchMap(mCorrectiveActionDataRepository.getAllCorrectiveAction(), input ->
                getMediator_panel(ProcessusPanelCreator.incubeCorrectiveIntoPanel(processusPanels, input)));
    }

    //GET manager for each risk
    private LiveData<List<ProcessusPanel>> getMediator_panel(List<ProcessusPanel> processusPanels){
        return Transformations.map(mParticipantDataRepository.getAllParticipant(), input -> ProcessusPanelCreator.incubeParticipantIntoPanel(processusPanels, input));
    }
}
