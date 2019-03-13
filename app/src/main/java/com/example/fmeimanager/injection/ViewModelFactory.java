package com.example.fmeimanager.injection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.fmeimanager.repositories.CorrectiveActionDataRepository;
import com.example.fmeimanager.repositories.FmeiDataRepository;
import com.example.fmeimanager.repositories.ParticipantDataRepository;
import com.example.fmeimanager.repositories.ProcessusDataRepository;
import com.example.fmeimanager.repositories.RiskDataRepository;
import com.example.fmeimanager.repositories.TeamFmeiDataRepository;
import com.example.fmeimanager.viewmodels.GeneralViewModel;
import com.example.fmeimanager.viewmodels.ParticipantViewModel;
import com.example.fmeimanager.viewmodels.ProcessusViewModel;
import com.example.fmeimanager.viewmodels.RiskViewModel;
import com.example.fmeimanager.viewmodels.TeamViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final CorrectiveActionDataRepository mCorrectiveActionDataRepository;
    private final FmeiDataRepository mFmeiDataRepository;
    private final ParticipantDataRepository mParticipantDataRepository;
    private final ProcessusDataRepository mProcessusDataRepository;
    private final RiskDataRepository mRiskDataRepository;
    private final TeamFmeiDataRepository mTeamFmeiDataRepository;
    private final Executor mExecutor;

    public ViewModelFactory(CorrectiveActionDataRepository correctiveActionDataRepository, FmeiDataRepository fmeiDataRepository,
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

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GeneralViewModel.class)){
            return (T) new GeneralViewModel(mCorrectiveActionDataRepository, mFmeiDataRepository, mParticipantDataRepository,
                    mProcessusDataRepository, mRiskDataRepository, mTeamFmeiDataRepository, mExecutor);
        }
        if (modelClass.isAssignableFrom(ParticipantViewModel.class)){
            return (T) new ParticipantViewModel(mParticipantDataRepository, mExecutor);
        }
        if (modelClass.isAssignableFrom(ProcessusViewModel.class)){
            return (T) new ProcessusViewModel(mCorrectiveActionDataRepository, mParticipantDataRepository, mProcessusDataRepository, mRiskDataRepository, mExecutor);
        }
        if (modelClass.isAssignableFrom(RiskViewModel.class)){
            return (T) new RiskViewModel(mRiskDataRepository, mCorrectiveActionDataRepository, mParticipantDataRepository, mExecutor);
        }
        if (modelClass.isAssignableFrom(TeamViewModel.class)){
            return (T) new TeamViewModel(mFmeiDataRepository, mParticipantDataRepository, mTeamFmeiDataRepository, mExecutor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
