package com.example.fmeimanager.usecase;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.example.fmeimanager.database.Fmei;
import com.example.fmeimanager.database.Processus;
import com.example.fmeimanager.models.FmeiPanel;
import com.example.fmeimanager.models.FmeiPanelCreator;
import com.example.fmeimanager.repositories.CorrectiveActionDataRepository;
import com.example.fmeimanager.repositories.FmeiDataRepository;
import com.example.fmeimanager.repositories.ParticipantDataRepository;
import com.example.fmeimanager.repositories.ProcessusDataRepository;
import com.example.fmeimanager.repositories.RiskDataRepository;
import com.example.fmeimanager.repositories.TeamFmeiDataRepository;

import java.util.List;

public class FmeaCardLiveData {

    //REPOSITORIES
    private final CorrectiveActionDataRepository mCorrectiveActionDataRepository;
    private final FmeiDataRepository mFmeiDataRepository;
    private final ParticipantDataRepository mParticipantDataRepository;
    private final ProcessusDataRepository mProcessusDataRepository;
    private final RiskDataRepository mRiskDataRepository;
    private final TeamFmeiDataRepository mTeamFmeiDataRepository;

    private FmeiPanelCreator mFmeiPanelCreator;

    public MediatorLiveData combineLiveData = new MediatorLiveData();


    public FmeaCardLiveData(CorrectiveActionDataRepository correctiveActionDataRepository, FmeiDataRepository fmeiDataRepository,
                            ParticipantDataRepository participantDataRepository, ProcessusDataRepository processusDataRepository,
                            RiskDataRepository riskDataRepository, TeamFmeiDataRepository teamFmeiDataRepository) {
        mCorrectiveActionDataRepository = correctiveActionDataRepository;
        mFmeiDataRepository = fmeiDataRepository;
        mParticipantDataRepository = participantDataRepository;
        mProcessusDataRepository = processusDataRepository;
        mRiskDataRepository = riskDataRepository;
        mTeamFmeiDataRepository = teamFmeiDataRepository;
    }

/*
    public MediatorLiveData<FmeiPanelCreator> getFmeaToPanel(){

        LiveData<List<Fmei>> fmeiListLiveData = mFmeiDataRepository.getAllFmei();
        LiveData<List<Processus>> processusListLiveData = mProcessusDataRepository.getAllProcessus();

        final MediatorLiveData<FmeiPanelCreator> customSource = new MediatorLiveData<>();
        customSource.addSource(fmeiListLiveData, new Observer<List<Fmei>>() {
            @Override
            public void onChanged(@Nullable List<Fmei> fmeiList) {
                FmeiPanelCreator fmeiPanelCreator = new FmeiPanelCreator(1,150,200);
                fmeiPanelCreator.setFmeiList(fmeiList);
                customSource.val(fmeiPanelCreator);
            }
        });


        return customSource;
    }

    private FmeiPanelCreator combineLatestData(LiveData<List<Fmei>> fmeiIncube, LiveData<List<Processus>> processusIncube){

    }



*/



}
