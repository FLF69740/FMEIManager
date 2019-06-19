package com.FMEA.fmeimanager.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import com.FMEA.fmeimanager.database.Fmei;
import com.FMEA.fmeimanager.database.Participant;
import com.FMEA.fmeimanager.database.TeamFmei;
import com.FMEA.fmeimanager.models.TeamPanel;
import com.FMEA.fmeimanager.models.TeamPanelCreator;
import com.FMEA.fmeimanager.repositories.FmeiDataRepository;
import com.FMEA.fmeimanager.repositories.ParticipantDataRepository;
import com.FMEA.fmeimanager.repositories.TeamFmeiDataRepository;
import java.util.ArrayList;
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

    public LiveData<Fmei> getFmei(long id) {return mFmeiDataRepository.getFmei(id);}

    /**
     *  PARTICIPANT
     */

    public LiveData<List<Participant>> getAllParticipant() {return mParticipantDataRepository.getAllParticipant();}
    public LiveData<Participant> getParticipant(long id) {return mParticipantDataRepository.getParticipant(id);}

    public void updateParticipant(Participant Participant){
        mExecutor.execute(()->mParticipantDataRepository.updateParticipant(Participant));
    }

    /**
     *  TEAM FMEI
     */

    public LiveData<List<TeamFmei>> getTeamsWithLinkFmei(long fmeiId) {
        return mTeamFmeiDataRepository.getTeamsWithLinkFmei(fmeiId);
    }

    public void createTeamFmei(TeamFmei TeamFmei){
        mExecutor.execute(()->mTeamFmeiDataRepository.createTeamFmei(TeamFmei));
    }

    public void deleteTeamFmei(long TeamFmeiId){
        mExecutor.execute(()->mTeamFmeiDataRepository.deleteTeamFmei(TeamFmeiId));
    }

    /**
     *  TEAM PANEL
     */

    //GET list of fmea
    public LiveData<List<TeamPanel>> theFirstTeamLiveData(){
        return Transformations.switchMap(mFmeiDataRepository.getAllFmei(), input -> {
            List<TeamPanel> teamPanel = new ArrayList<>();
            return theSecondTeamLiveData(TeamPanelCreator.incubeFmeiIntoTeamPanel(teamPanel, input));
        });

    }

    //GET list of participant
    private LiveData<List<TeamPanel>> theSecondTeamLiveData(TeamPanelCreator panelCreator){
        return Transformations.switchMap(mParticipantDataRepository.getAllParticipant(), input ->
                getMediator_teamPanel(TeamPanelCreator.incubeParticipantIntoTeamPanel(panelCreator, input)));
    }

    //GET complete list TeamPanel
    private LiveData<List<TeamPanel>> getMediator_teamPanel(TeamPanelCreator panelCreator){
        return Transformations.map(mTeamFmeiDataRepository.getAllTeamFmei(), input -> TeamPanelCreator.incubeTeamFmeiIntoTeamPanel(input, panelCreator));
    }
}