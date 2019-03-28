package com.example.fmeimanager.controllers.navigationPackageA.riskTheme;

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
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.Risk;
import com.example.fmeimanager.viewmodels.RiskViewModel;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RiskFileDescriptionFragment extends Fragment {

    private static final String BUNDLE_RISK_ID = "BUNDLE_RISK_ID";

    private View mView;
    private RiskViewModel mRiskViewModel;
    private long mRiskId;
    private Participant mParticipant;
    private Risk mRisk;

    public RiskFileDescriptionFragment() {}

    public static RiskFileDescriptionFragment newInstance(long processusId){
        RiskFileDescriptionFragment riskFileDescriptionFragment = new RiskFileDescriptionFragment();
        Bundle bundle = new Bundle(1);
        bundle.putLong(BUNDLE_RISK_ID, processusId);
        riskFileDescriptionFragment.setArguments(bundle);
        return riskFileDescriptionFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_risk_file_description, container, false);
        ButterKnife.bind(this, mView);
        mRiskId = getArguments().getLong(BUNDLE_RISK_ID);

        this.configureViewModel();
        this.getAdministrator(1);
        this.getRiskSelected(mRiskId);

        Toast.makeText(getContext(), "RISK  : " + String.valueOf(mRiskId), Toast.LENGTH_SHORT).show();

        return mView;
    }

    @OnClick(R.id.risk_file_first_link)
    public void riskFile_To_CorrectiveAction(){
        mCallback.riskFile_To_CorrectiveAction(mView, mRiskId);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface RiskFileItemClickedListener{
        void riskFile_To_CorrectiveAction(View view, long riskId);
    }

    //callback for button clicked
    private RiskFileItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (RiskFileItemClickedListener) getActivity();
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

    //GET risk selected
    private void getRiskSelected(long riskId){
        this.mRiskViewModel.getRisk(riskId).observe(this, this::updateRisk);
    }

    //GET participant attached to selected risk
    private void getRiskParticipant(long id){
        this.mRiskViewModel.getParticipant(id).observe(this, this::updateParticipant);
    }

    //GET corrective action attached to selected risk
    private void getRiskCorrectiveAction(){
        this.mRiskViewModel.getAllCorrectiveAction().observe(this, this::updateCorrectiveAction);
    }

    //UPDATE risk
    private void SaveRiskSelected(Risk risk){
        this.mRiskViewModel.updateRisk(risk);
    }


    /**
     *  UI
     */

    private void updateAdministrator(Participant participant){
        //    Toast.makeText(this, participant.getForname() + " " + participant.getName(), Toast.LENGTH_SHORT).show();
    }

    //RECORD risk
    private void updateRisk(Risk risk) {
        if (risk != null) {
            mRisk = risk;
            getRiskParticipant(risk.getParticipantId());
            getRiskCorrectiveAction();
        }
    }

    //RECORD participant
    private void updateParticipant(Participant participant){
        if (participant != null){
            mParticipant = participant;

        }
    }

    //RECORD corrective action
    private void updateCorrectiveAction(List<CorrectiveAction> correctiveActionList){
        if (correctiveActionList != null){
        //    for (int)

        }
    }

    //BUILD IHM
    private void updateInformations(){

    }


}
