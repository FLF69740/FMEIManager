package com.FMEA.fmeimanager.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import com.FMEA.fmeimanager.database.CorrectiveAction;
import com.FMEA.fmeimanager.database.Fmei;
import com.FMEA.fmeimanager.database.Participant;
import com.FMEA.fmeimanager.database.Processus;
import com.FMEA.fmeimanager.database.Risk;
import com.FMEA.fmeimanager.database.TeamFmei;
import com.FMEA.fmeimanager.models.FmeiPanel;
import com.FMEA.fmeimanager.models.FmeiPanelCreator;
import com.FMEA.fmeimanager.repositories.CorrectiveActionDataRepository;
import com.FMEA.fmeimanager.repositories.FmeiDataRepository;
import com.FMEA.fmeimanager.repositories.ParticipantDataRepository;
import com.FMEA.fmeimanager.repositories.ProcessusDataRepository;
import com.FMEA.fmeimanager.repositories.RiskDataRepository;
import com.FMEA.fmeimanager.repositories.TeamFmeiDataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class GeneralViewModel extends ViewModel {
/*
    public MediatorLiveData<List<FmeiPanel>> getMediator_fmea(int high, int medium, int low){

        List<FmeiPanel> panels = new ArrayList<>();

        final MediatorLiveData<List<FmeiPanel>> customSource = new MediatorLiveData<>();
        customSource.addSource(mFmeiDataRepository.getAllFmei(), fmeiList -> customSource.setValue(FmeiPanelCreator.incubeFmeaIntoPanel(panels, fmeiList)));
        customSource.addSource(mProcessusDataRepository.getAllProcessus(), processusList -> customSource.setValue(FmeiPanelCreator.incubeProcessusIntoPanel(panels, processusList)));
        customSource.addSource(mRiskDataRepository.getAllRisk(), risks -> customSource.setValue(FmeiPanelCreator.incubeRiskIntoPanel(panels, risks, high, medium, low)));
        customSource.addSource(mTeamFmeiDataRepository.getAllTeamFmei(), teamFmeiList -> customSource.setValue(FmeiPanelCreator.incubeTeamFmeaIntoPanel(panels, teamFmeiList)));
        customSource.addSource(mParticipantDataRepository.getAllParticipant(), participants -> customSource.setValue(FmeiPanelCreator.incubeParticipantIntoPanel(panels, participants)));


        customSource.addSource(processusListLiveData, new Observer<List<Processus>>() {
            @Override
            public void onChanged(@Nullable List<Processus> processusList) {
                if (processusList != null) {
                    for (int i = 0; i < panels.size(); i++) {
                        List<Processus> listProcessusBuild = new ArrayList<>();
                        for (int j = 0; j < processusList.size(); j++) {
                            if (processusList.get(j).getFmeiId() == panels.get(i).getFmeiId() && processusList.get(j).isVisible()) {
                                listProcessusBuild.add(processusList.get(j));
                            }
                        }
                        panels.get(i).setProcessusList(listProcessusBuild);
                    }
                }
                customSource.setValue(panels);
            }
        });

        customSource.addSource(riskListLiveData, new Observer<List<Risk>>() {
            @Override
            public void onChanged(@Nullable List<Risk> risks) {
                if (risks != null) {
                    for (int i = 0; i < panels.size(); i++) {
                        List<Integer> riskAverageDatas = new ArrayList<>();
                        int qty = 0;
                        for (int j = 0; j < panels.get(i).getProcessusList().size(); j++) {
                            for (int k = 0; k < risks.size(); k++) {
                                if (panels.get(i).getProcessusList().get(j).getId() == risks.get(k).getProcessusId()) {
                                    qty++;
                                    riskAverageDatas.add(risks.get(k).getGravity() * risks.get(k).getFrequencies() * risks.get(k).getDetectability());
                                    panels.get(i).setIPRMax(BusinessFmeaTheme.getIprMax(panels.get(i).getIPRMax(),
                                            risks.get(k).getGravity() * risks.get(k).getFrequencies() * risks.get(k).getDetectability()));
                                }
                            }
                        }
                        panels.get(i).setRiskAmount(qty);
                        panels.get(i).setRiskRateAverage(BusinessFmeaTheme.getRiskAverageIPR(riskAverageDatas));
                        panels.get(i).setRiskAmountHighLevel(BusinessFmeaTheme.getHighRiskLevelAmount(riskAverageDatas, high));
                        panels.get(i).setRiskAmountMiddleLevel(BusinessFmeaTheme.getMediumRiskLevelAmount(riskAverageDatas, high, medium));
                        panels.get(i).setRiskAmountLowLevel(BusinessFmeaTheme.getLowRiskLevelAmount(riskAverageDatas, medium, low));
                    }
                }
                customSource.setValue(panels);
            }
        });


        customSource.addSource(teamFmeaLiveData, new Observer<List<TeamFmei>>() {
            @Override
            public void onChanged(@Nullable List<TeamFmei> teams) {
                if (teams != null) {
                    for (int i = 0; i < panels.size(); i++) {
                        int qty = 0;
                        for (int j = 0; j < teams.size(); j++) {
                            if (teams.get(j).getFmeiId() == panels.get(i).getFmeiId()) {
                                qty++;
                            }
                        }
                        panels.get(i).setParticipantNumber(qty);
                    }
                }
                customSource.setValue(panels);
            }
        });

        customSource.addSource(participantLiveDate, new Observer<List<Participant>>() {
            @Override
            public void onChanged(@Nullable List<Participant> participants) {
                if (participants != null){
                    for (int i = 0 ; i < panels.size() ; i++){
                        for (int j = 0 ; j < participants.size() ; j++){
                            if (panels.get(i).getTeamLeaderId() == participants.get(j).getId()){
                                panels.get(i).setTeamLeader(participants.get(j).getForname() + " " + participants.get(j).getName());
                            }
                        }
                    }
                }
                customSource.setValue(panels);
            }
        });

        return customSource;
    }
*/
    //REPOSITORIES
    private final CorrectiveActionDataRepository mCorrectiveActionDataRepository;
    private final FmeiDataRepository mFmeiDataRepository;
    private final ParticipantDataRepository mParticipantDataRepository;
    private final ProcessusDataRepository mProcessusDataRepository;
    private final RiskDataRepository mRiskDataRepository;
    private final TeamFmeiDataRepository mTeamFmeiDataRepository;
    private final Executor mExecutor;

    //DATA
    @Nullable private LiveData<Participant> administrator;

    public GeneralViewModel(CorrectiveActionDataRepository correctiveActionDataRepository, FmeiDataRepository fmeiDataRepository,
                            ParticipantDataRepository participantDataRepository, ProcessusDataRepository processusDataRepository,
                            RiskDataRepository riskDataRepository, TeamFmeiDataRepository teamFmeiDataRepository, Executor executor) {
        mCorrectiveActionDataRepository = correctiveActionDataRepository;
        mFmeiDataRepository = fmeiDataRepository;
        mParticipantDataRepository = participantDataRepository;
        mProcessusDataRepository = processusDataRepository;
        mRiskDataRepository = riskDataRepository;
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
     *  FMEA PANELS
     */

    public MediatorLiveData<List<FmeiPanel>> getMediator_fmea(int high, int medium, int low){
        List<FmeiPanel> panels = new ArrayList<>();
        final MediatorLiveData<List<FmeiPanel>> customSource = new MediatorLiveData<>();
        customSource.addSource(mFmeiDataRepository.getAllFmei(), fmeiList -> customSource.setValue(FmeiPanelCreator.incubeFmeaIntoPanel(panels, fmeiList)));
        customSource.addSource(mProcessusDataRepository.getAllProcessus(), processusList -> customSource.setValue(FmeiPanelCreator.incubeProcessusIntoPanel(panels, processusList)));
        customSource.addSource(mRiskDataRepository.getAllRisk(), risks -> customSource.setValue(FmeiPanelCreator.incubeRiskIntoPanel(panels, risks, high, medium, low)));
        customSource.addSource(mTeamFmeiDataRepository.getAllTeamFmei(), teamFmeiList -> customSource.setValue(FmeiPanelCreator.incubeTeamFmeaIntoPanel(panels, teamFmeiList)));
        customSource.addSource(mParticipantDataRepository.getAllParticipant(), participants -> customSource.setValue(FmeiPanelCreator.incubeParticipantIntoPanel(panels, participants)));
        return customSource;
    }




    /**
     *  CORRECTIVE ACTION
     */

    public LiveData<List<CorrectiveAction>> getAllCorrectiveAction() {
        return mCorrectiveActionDataRepository.getAllCorrectiveAction();
    }

    public LiveData<CorrectiveAction> getCorrectiveAction(long id) {
        return mCorrectiveActionDataRepository.getCorrectiveAction(id);
    }

    /**
     *  FMEI
     */

    public LiveData<List<Fmei>> getAllFmei() {return mFmeiDataRepository.getAllFmei();}
    public LiveData<Fmei> getFmei(long id) {return mFmeiDataRepository.getFmei(id);}

    public void createFmei(Fmei Fmei, TeamFmei teamFmei){
        mExecutor.execute(()->mFmeiDataRepository.createFmei(Fmei));
        mExecutor.execute(()->mTeamFmeiDataRepository.createTeamFmei(teamFmei));
    }

    /**
     *  PARTICIPANT
     */

    public LiveData<List<Participant>> getAllParticipant() {
        return mParticipantDataRepository.getAllParticipant();
    }

    public LiveData<Participant> getParticipant(long id) {
        return mParticipantDataRepository.getParticipant(id);
    }

    public void updateParticipant(Participant Participant){
        mExecutor.execute(()->mParticipantDataRepository.updateParticipant(Participant));
    }

    /**
     *  PROCESSUS
     */

    public LiveData<List<Processus>> getProcessussListForFmei(long fmeaId) {
        return mProcessusDataRepository.getProcessussListForFmei(fmeaId);
    }
    
    /**
     *  RISK
     */

    public LiveData<List<Risk>> getAllRisk() {
        return mRiskDataRepository.getAllRisk();
    }

    public LiveData<Risk> getRisk(long id) {
        return mRiskDataRepository.getRisk(id);
    }

    /**
     *  TEAM FMEI
     */

    public LiveData<List<TeamFmei>> getTeamsWithLinkParticipant(long participantId) {
        return mTeamFmeiDataRepository.getTeamsWithLinkParticipant(participantId);
    }

    public LiveData<List<TeamFmei>> getTeamsWithLinkFmei(long riskId) {
        return mTeamFmeiDataRepository.getTeamsWithLinkFmei(riskId);
    }

}
