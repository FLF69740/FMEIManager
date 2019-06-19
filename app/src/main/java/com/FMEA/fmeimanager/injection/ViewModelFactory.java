package com.FMEA.fmeimanager.injection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.FMEA.fmeimanager.repositories.CorrectiveActionDataRepository;
import com.FMEA.fmeimanager.repositories.FmeiDataRepository;
import com.FMEA.fmeimanager.repositories.ParticipantDataRepository;
import com.FMEA.fmeimanager.repositories.ProcessusDataRepository;
import com.FMEA.fmeimanager.repositories.RiskDataRepository;
import com.FMEA.fmeimanager.repositories.TeamFmeiDataRepository;
import com.FMEA.fmeimanager.viewmodels.GeneralViewModel;
import com.FMEA.fmeimanager.viewmodels.ParticipantViewModel;
import com.FMEA.fmeimanager.viewmodels.ProcessusViewModel;
import com.FMEA.fmeimanager.viewmodels.RiskViewModel;
import com.FMEA.fmeimanager.viewmodels.TeamViewModel;

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
        if (modelClass.isAssignableFrom(GeneralViewModel.class)) {
            return (T) new GeneralViewModel(mCorrectiveActionDataRepository, mFmeiDataRepository, mParticipantDataRepository,
                    mProcessusDataRepository, mRiskDataRepository, mTeamFmeiDataRepository, mExecutor);
        }
        if (modelClass.isAssignableFrom(ParticipantViewModel.class)) {
            return (T) new ParticipantViewModel(mParticipantDataRepository, mExecutor);
        }
        if (modelClass.isAssignableFrom(ProcessusViewModel.class)) {
            return (T) new ProcessusViewModel(mCorrectiveActionDataRepository, mParticipantDataRepository, mProcessusDataRepository, mRiskDataRepository, mExecutor);
        }
        if (modelClass.isAssignableFrom(RiskViewModel.class)) {
            return (T) new RiskViewModel(mRiskDataRepository, mCorrectiveActionDataRepository, mParticipantDataRepository, mTeamFmeiDataRepository, mExecutor);
        }
        if (modelClass.isAssignableFrom(TeamViewModel.class)) {
            return (T) new TeamViewModel(mFmeiDataRepository, mParticipantDataRepository, mTeamFmeiDataRepository, mExecutor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
