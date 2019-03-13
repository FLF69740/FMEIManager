package com.example.fmeimanager.injection;

import android.content.Context;

import com.example.fmeimanager.database.FmeiManagerDatabase;
import com.example.fmeimanager.repositories.CorrectiveActionDataRepository;
import com.example.fmeimanager.repositories.FmeiDataRepository;
import com.example.fmeimanager.repositories.ParticipantDataRepository;
import com.example.fmeimanager.repositories.ProcessusDataRepository;
import com.example.fmeimanager.repositories.RiskDataRepository;
import com.example.fmeimanager.repositories.TeamFmeiDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {
    
    public static CorrectiveActionDataRepository provideCorrectiveActionDataSource(Context context){
        FmeiManagerDatabase database = FmeiManagerDatabase.getInstance(context);
        return new CorrectiveActionDataRepository(database.mCorrectiveActionDao());
    }

    public static FmeiDataRepository provideFmeiDataSource(Context context){
        FmeiManagerDatabase database = FmeiManagerDatabase.getInstance(context);
        return new FmeiDataRepository(database.mFmeiDao());
    }

    public static ParticipantDataRepository provideParticipantDataSource(Context context){
        FmeiManagerDatabase database = FmeiManagerDatabase.getInstance(context);
        return new ParticipantDataRepository(database.mParticipantDao());
    }

    public static ProcessusDataRepository provideProcessusDataSource(Context context){
        FmeiManagerDatabase database = FmeiManagerDatabase.getInstance(context);
        return new ProcessusDataRepository(database.mProcessusDao());
    }

    public static RiskDataRepository provideRiskDataSource(Context context){
        FmeiManagerDatabase database = FmeiManagerDatabase.getInstance(context);
        return new RiskDataRepository(database.mRiskDao());
    }

    public static TeamFmeiDataRepository provideTeamFmeiDataSource(Context context){
        FmeiManagerDatabase database = FmeiManagerDatabase.getInstance(context);
        return new TeamFmeiDataRepository(database.mTeamFmeiDao());
    }

    public static Executor provideExecutor() {return Executors.newSingleThreadExecutor();}


    // VIEW MODEL FACTORY

    public static ViewModelFactory provideViewModelFactory(Context context){
        CorrectiveActionDataRepository correctiveActionDataRepository = provideCorrectiveActionDataSource(context);
        FmeiDataRepository fmeiDataRepository = provideFmeiDataSource(context);
        ParticipantDataRepository participantDataRepository = provideParticipantDataSource(context);
        ProcessusDataRepository processusDataRepository = provideProcessusDataSource(context);
        RiskDataRepository riskDataRepository = provideRiskDataSource(context);
        TeamFmeiDataRepository teamFmeiDataRepository = provideTeamFmeiDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(correctiveActionDataRepository, fmeiDataRepository, participantDataRepository, processusDataRepository,
                riskDataRepository, teamFmeiDataRepository, executor);
    }
    
}
