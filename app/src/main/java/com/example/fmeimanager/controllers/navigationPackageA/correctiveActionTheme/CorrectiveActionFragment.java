package com.example.fmeimanager.controllers.navigationPackageA.correctiveActionTheme;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.database.CorrectiveAction;
import com.example.fmeimanager.database.Risk;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.utils.Utils;
import com.example.fmeimanager.viewmodels.RiskViewModel;

import org.joda.time.DateTime;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CorrectiveActionFragment extends Fragment {

    private static final String BUNDLE_RISK_ID = "BUNDLE_RISK_ID";
    private static final String BUNDLE_PROCESSUS_STEP = "BUNDLE_PROCESSUS_STEP";

    private View mView;
    private RiskViewModel mRiskViewModel;
    private long mRiskId;
    private int mProcessusStepInteger;
    private CorrectiveAction mCorrectiveAction;

    public CorrectiveActionFragment() {}

    public static CorrectiveActionFragment newInstance(long riskId, int processusStep){
        CorrectiveActionFragment correctiveActionFragment = new CorrectiveActionFragment();
        Bundle bundle = new Bundle(2);
        bundle.putInt(BUNDLE_PROCESSUS_STEP, processusStep);
        bundle.putLong(BUNDLE_RISK_ID, riskId);
        correctiveActionFragment.setArguments(bundle);
        return correctiveActionFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_corrective_action, container, false);
        ButterKnife.bind(this, mView);
        mRiskId = getArguments().getLong(BUNDLE_RISK_ID, 100);
        mProcessusStepInteger = getArguments().getInt(BUNDLE_PROCESSUS_STEP, 0);

        this.configureViewModel();
        this.getAdministrator(1);
        this.getCorrectiveAction(mRiskId);

        return mView;
    }

    @OnClick(R.id.corrective_action_first_link)
    public void correctiveAction_To_riskFile(){
        mCallback.correctiveAction_To_riskFile(mView, mRiskId, mProcessusStepInteger);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface CorrectiveActionItemClickedListener{
        void correctiveAction_To_riskFile(View view, long riskId, int processusStep);
    }

    //callback for button clicked
    private CorrectiveActionItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (CorrectiveActionItemClickedListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }

    /**
     *  DATAS
     */

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(getContext());
        this.mRiskViewModel = ViewModelProviders.of(this, viewModelFactory).get(RiskViewModel.class);
        this.mRiskViewModel.init(1);
    }

    private void getAdministrator(long id){
        this.mRiskViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    //GET corrective action
    private void getCorrectiveAction(long riskId){
        this.mRiskViewModel.getCorrectiveActionsListForRisk(riskId).observe(this, this::updateCorrectiveAction);
    }

    /**
     *  UI
     */

    private void updateAdministrator(Participant participant){
        Toast.makeText(getContext(), participant.getForname() + " " + participant.getName(), Toast.LENGTH_SHORT).show();
    }

    //LOAD corrective action (!ifExist : create a new one)
    private void updateCorrectiveAction(CorrectiveAction correctiveAction) {
        if (correctiveAction != null){
            mCorrectiveAction = correctiveAction;
        }else {
            DateTime dateTime = new DateTime();
            mCorrectiveAction = new CorrectiveAction(Utils.EMPTY, dateTime.toString("dd/MM/yyyy"), Utils.EMPTY, Utils.EMPTY, Utils.EMPTY, mRiskId, 1);
        }
    }

}
