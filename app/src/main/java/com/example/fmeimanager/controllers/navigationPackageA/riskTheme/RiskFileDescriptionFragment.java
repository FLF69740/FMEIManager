package com.example.fmeimanager.controllers.navigationPackageA.riskTheme;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.database.CorrectiveAction;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.Risk;
import com.example.fmeimanager.utils.Utils;
import com.example.fmeimanager.viewmodels.RiskViewModel;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class RiskFileDescriptionFragment extends Fragment {

    @BindView(R.id.risk_file_textview_manager) TextView mManager;
    @BindView(R.id.risk_file_first_link) Button mGoActionCorrectiveBtn;
    @BindView(R.id.fragment_risk_risk_id) TextView mRiskIdTextView;
    @BindView(R.id.fragment_risk_processus_step) TextView mStepProcessus;
    @BindView(R.id.fragment_risk_traceability_name) EditText mIdentification;
    @BindView(R.id.fragment_risk_creation_date) TextView mCreationDate;
    @BindView(R.id.fragment_risk_part_title) EditText mPart;
    @BindView(R.id.fragment_risk_failing_title) EditText mRiskTitle;
    @BindView(R.id.fragment_risk_failing_effect) EditText mEffect;
    @BindView(R.id.fragment_risk_verification_tools) EditText mDetectionTool;
    @BindView(R.id.fragment_risk_root_cause) EditText mRootCause;
    @BindView(R.id.fragment_risk_photo_case_1) ImageView mImageView1;
    @BindView(R.id.fragment_risk_photo_case_2) ImageView mImageView2;
    @BindView(R.id.fragment_risk_photo_case_3) ImageView mImageView3;
    @BindView(R.id.fragment_risk_photo_case_plus) ImageView mImageViewPlusLogo;
    @BindView(R.id.fragment_risk_ipr_score) TextView mIprScore;
    @BindView(R.id.fragment_risk_severity_value) TextView mSeverityScore;
    @BindView(R.id.fragment_risk_probability_score) TextView mProbabilityScore;
    @BindView(R.id.fragment_risk_detection_score) TextView mDetectionScore;
    @BindView(R.id.fragment_risk_ask_btn) Button mAsk;

    private static final String BUNDLE_RISK_ID = "BUNDLE_RISK_ID";
    private static final String BUNDLE_PROCESSUS_STEP = "BUNDLE_PROCESSUS_STEP";
    public static final String BUNDLE_KEY_CRITERIA_SCORE = "BUNDLE_KEY_CRITERIA_SCORE";
    private static final int CRITERIA_SCORE_REQUEST_CODE = 987;
    private static final String SEVERITY = "SEVERITY";
    private static final String PROBABILITY = "PROBABILITY";
    private static final String DETECTION = "DETECTION";

    private View mView;
    private RiskViewModel mRiskViewModel;
    private long mRiskId;
    private int mProcessusStepInteger;
    private Participant mParticipant;
    private Risk mRisk;

    public RiskFileDescriptionFragment() {}

    public static RiskFileDescriptionFragment newInstance(long riskId, int processusStep){
        RiskFileDescriptionFragment riskFileDescriptionFragment = new RiskFileDescriptionFragment();
        Bundle bundle = new Bundle(2);
        bundle.putLong(BUNDLE_RISK_ID, riskId);
        bundle.putInt(BUNDLE_PROCESSUS_STEP, processusStep);
        riskFileDescriptionFragment.setArguments(bundle);
        return riskFileDescriptionFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_risk_file_description, container, false);
        ButterKnife.bind(this, mView);
        mRiskId = getArguments().getLong(BUNDLE_RISK_ID);
        mProcessusStepInteger = getArguments().getInt(BUNDLE_PROCESSUS_STEP);
        String processusStepTitle = mView.getContext().getString(R.string.Risk_file_corrective_processus_step) + " " + mProcessusStepInteger;
        mStepProcessus.setText(processusStepTitle);

        this.configureViewModel();
        this.getAdministrator(1);
        this.getRiskSelected(mRiskId);

        return mView;
    }

    //BUILD IHM with risk information
    private void updateInformationsRiskPanel(Risk risk){
        mRiskIdTextView.setText(String.valueOf(risk.getId()));
        if (!risk.getIdentification().equals(Utils.EMPTY)){
            mIdentification.setText(risk.getIdentification());
        }
        mCreationDate.setText(risk.getCreationDate());
        if (!risk.getParts().equals(Utils.EMPTY)) {
            mPart.setText(risk.getParts());
        }
        if (!risk.getRisk().equals(Utils.EMPTY)) {
            mRiskTitle.setText(risk.getRisk());
        }
        if (!risk.getRiskEffect().equals(Utils.EMPTY)) {
            mEffect.setText(risk.getRiskEffect());
        }
        if (!risk.getVerification().equals(Utils.EMPTY)) {
            mDetectionTool.setText(risk.getVerification());
        }
        if (!risk.getPotentialCause().equals(Utils.EMPTY)) {
            mRootCause.setText(risk.getPotentialCause());
        }
/*
        mImageView1
                mImageView2
        mImageView3
                mImageViewPlusLogo
*/
        mIprScore.setText(String.valueOf(risk.getGravity()* risk.getFrequencies()* risk.getDetectability()));
        mSeverityScore.setText(String.valueOf(risk.getGravity()));
        mProbabilityScore.setText(String.valueOf(risk.getFrequencies()));
        mDetectionScore.setText(String.valueOf(risk.getDetectability()));
    }

    /**
     *  ACTION
     */

    //DELETE Risk
    @OnClick(R.id.fragment_risk_delete_btn)
    public void deleteRisk(){
        mRiskViewModel.deleteRisk(mRiskId);
        mCallback.riskFileDelete(mView);
    }

    //CHANGE Participant
    @OnClick(R.id.risk_file_textview_manager)
    public void changeParticipant(){
        Log.i(Utils.INFORMATION_LOG, "change participant , id : " + mParticipant.getId());
    }

    @OnClick(R.id.fragment_risk_severity_value)
    public void changeSeverityValue(){
        Intent intent = new Intent(getActivity(), IntKeyboardActivity.class);
        intent.putExtra(BUNDLE_KEY_CRITERIA_SCORE, SEVERITY);
        startActivityForResult(intent, CRITERIA_SCORE_REQUEST_CODE);
    }

    @OnClick(R.id.fragment_risk_probability_score)
    public void changeProbabilityValue(){
        Intent intent = new Intent(getActivity(), IntKeyboardActivity.class);
        intent.putExtra(BUNDLE_KEY_CRITERIA_SCORE, PROBABILITY);
        startActivityForResult(intent, CRITERIA_SCORE_REQUEST_CODE);
    }

    @OnClick(R.id.fragment_risk_detection_score)
    public void changeDetectionValue(){
        Intent intent = new Intent(getActivity(), IntKeyboardActivity.class);
        intent.putExtra(BUNDLE_KEY_CRITERIA_SCORE, DETECTION);
        startActivityForResult(intent, CRITERIA_SCORE_REQUEST_CODE);
    }

    //GO TO Corrective action IHM
    @OnClick(R.id.risk_file_first_link)
    public void riskFile_To_CorrectiveAction(){
        mCallback.riskFile_To_CorrectiveAction(mView, mRiskId, mProcessusStepInteger);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface RiskFileItemClickedListener{
        void riskFile_To_CorrectiveAction(View view, long riskId, int processusStepInteger);
        void riskFileDelete(View view);
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
     *  On activity result
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (CRITERIA_SCORE_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            String sourceCriteria = data.getStringExtra(IntKeyboardActivity.BUNDLE_KEYBOARD_SOURCE);
            int newScore = data.getIntExtra(IntKeyboardActivity.BUNDLE_KEYBOARD_SCORE, 10);
            switch (sourceCriteria){
                case SEVERITY:
                    mSeverityScore.setText(String.valueOf(newScore));
                    break;
                case PROBABILITY:
                    mProbabilityScore.setText(String.valueOf(newScore));
                    break;
                case DETECTION:
                    mDetectionScore.setText(String.valueOf(newScore));
                    break;
            }
            mIprScore.setText(String.valueOf(Integer.valueOf(mSeverityScore.getText().toString())*
                    Integer.valueOf(mProbabilityScore.getText().toString())*
                    Integer.valueOf(mDetectionScore.getText().toString())));
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

    //SAVE risk
    public void saveRiskSelected(){
        mRisk.setParticipantId(mParticipant.getId());
        this.updateRiskModel();
        this.mRiskViewModel.updateRisk(mRisk);
        Snackbar.make(mView, mView.getContext().getString(R.string.Risk_file_corrective_save), Snackbar.LENGTH_SHORT).show();
    }

    //UPADE risk model
    private void updateRiskModel(){
        mRisk.setIdentification(mIdentification.getText().toString());
        mRisk.setParts(mPart.getText().toString());
        mRisk.setRisk(mRiskTitle.getText().toString());
        mRisk.setRiskEffect(mEffect.getText().toString());
        mRisk.setVerification(mDetectionTool.getText().toString());
        mRisk.setPotentialCause(mRootCause.getText().toString());
/*
        mImageView1
                mImageView2
        mImageView3
                mImageViewPlusLogo
*/
        mRisk.setGravity(Integer.valueOf(mSeverityScore.getText().toString()));
        mRisk.setFrequencies(Integer.valueOf(mProbabilityScore.getText().toString()));
        mRisk.setDetectability(Integer.valueOf(mDetectionScore.getText().toString()));
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
            this.updateInformationsRiskPanel(risk);
            getRiskParticipant(risk.getParticipantId());
            getRiskCorrectiveAction();
        }
    }

    //RECORD participant
    private void updateParticipant(Participant participant){
        if (participant != null){
            mParticipant = participant;
            String completeName = "   " + participant.getForname() + " " + participant.getName();
            mManager.setText(completeName);
        }
    }

    //RECORD corrective action
    private void updateCorrectiveAction(List<CorrectiveAction> correctiveActionList){
        if (correctiveActionList != null){
        //    for (int)

        }
    }


}
