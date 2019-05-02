package com.example.fmeimanager.controllers.navigationPackageA.riskTheme;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fmeimanager.R;
import com.example.fmeimanager.database.CorrectiveAction;
import com.example.fmeimanager.database.TeamFmei;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.Risk;
import com.example.fmeimanager.utils.BitmapStorage;
import com.example.fmeimanager.utils.Utils;
import com.example.fmeimanager.viewmodels.RiskViewModel;

import java.util.ArrayList;
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

    private static final String BUNDLE_RISK_ID = "BUNDLE_RISK_ID";
    private static final String BUNDLE_FMEI_ID = "BUNDLE_FMEI_ID";
    private static final String BUNDLE_PROCESSUS_STEP = "BUNDLE_PROCESSUS_STEP";
    private static final String BUNDLE_KEY_RISK = "BUNDLE_KEY_RISK";
    private static final String BUNDLE_KEY_FMEI = "BUNDLE_KEY_FMEI";
    private static final String BUNDLE_KEY_RISK_STEP = "BUNDLE_KEY_RISK_STEP";
    public static final String BUNDLE_KEY_CRITERIA_SCORE = "BUNDLE_KEY_CRITERIA_SCORE";
    public static final String BUNDLE_KEY_RISK_MANAGER = "BUNDLE_KEY_RISK_MANAGER";
    public static final String BUNDLE_KEY_RISK_LIST_MANAGER_NAME = "BUNDLE_KEY_RISK_LIST_MANAGER_NAME";
    public static final String BUNDLE_KEY_RISK_LIST_MANAGER_FORNAME = "BUNDLE_KEY_RISK_LIST_MANAGER_FORNAME";
    public static final String BUNDLE_KEY_RISK_LIST_MANAGER_ID = "BUNDLE_KEY_RISK_LIST_MANAGER_ID";
    public static final String BUNDLE_PHOTO_LIST_RISK = "BUNDLE_PHOTO_LIST_RISK";
    public static final String BUNDLE_PHOTO_RISK_ID = "BUNDLE_PHOTO_RISK_ID";
    private static final int RC_PHOTO_UPLOAD = 10;
    private static final int RC_VIEWPAGER_UPLOAD = 151;
    private static final int CRITERIA_SCORE_REQUEST_CODE = 987;
    private static final int RISK_MANAGER_REQUEST_CODE = 654;
    private static final String SEVERITY = "SEVERITY";
    private static final String PROBABILITY = "PROBABILITY";
    private static final String DETECTION = "DETECTION";

    private View mView;
    private RiskViewModel mRiskViewModel;
    private long mRiskId;
    private long mFmeiId;
    private List<Long> mTeamsParticipantId;
    private int mProcessusStepInteger;
    private Participant mParticipant;
    private List<Participant> mParticipantList;
    private Risk mRisk;

    public RiskFileDescriptionFragment() {}

    public static RiskFileDescriptionFragment newInstance(long riskId, int processusStep, long fmeiId){
        RiskFileDescriptionFragment riskFileDescriptionFragment = new RiskFileDescriptionFragment();
        Bundle bundle = new Bundle(3);
        bundle.putLong(BUNDLE_RISK_ID, riskId);
        bundle.putInt(BUNDLE_PROCESSUS_STEP, processusStep);
        bundle.putLong(BUNDLE_FMEI_ID, fmeiId);
        riskFileDescriptionFragment.setArguments(bundle);
        return riskFileDescriptionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_risk_file_description, container, false);
        ButterKnife.bind(this, mView);

        this.configureViewModel();

        if (savedInstanceState != null) {
            mRisk = savedInstanceState.getParcelable(BUNDLE_KEY_RISK);
            mFmeiId = savedInstanceState.getLong(BUNDLE_KEY_FMEI);
            updateRisk(mRisk);
            mProcessusStepInteger = savedInstanceState.getInt(BUNDLE_KEY_RISK_STEP);
        }else {
            mRiskId = getArguments().getLong(BUNDLE_RISK_ID);
            mFmeiId = getArguments().getLong(BUNDLE_FMEI_ID);
            this.getRiskSelected(mRiskId);
            mProcessusStepInteger = getArguments().getInt(BUNDLE_PROCESSUS_STEP);
        }
        String processusStepTitle = mView.getContext().getString(R.string.Risk_file_corrective_processus_step) + " " + mProcessusStepInteger;
        mStepProcessus.setText(processusStepTitle);

        return mView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_KEY_RISK, mRisk);
        outState.putInt(BUNDLE_KEY_RISK_STEP, mProcessusStepInteger);
        outState.putLong(BUNDLE_KEY_FMEI, mFmeiId);
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
        this.updatePhotoAeara();
        mIprScore.setText(String.valueOf(risk.getGravity()* risk.getFrequencies()* risk.getDetectability()));
        mSeverityScore.setText(String.valueOf(risk.getGravity()));
        mProbabilityScore.setText(String.valueOf(risk.getFrequencies()));
        mDetectionScore.setText(String.valueOf(risk.getDetectability()));
    }

    //Upadte photo aera
    private void updatePhotoAeara(){
        int visibility1 = BusinessRiskTheme.getAeraState(mRisk.getUrlPictures()) > 0 ? View.VISIBLE : View.INVISIBLE;
        mImageView1.setVisibility(visibility1);
        if (BusinessRiskTheme.getAeraState(mRisk.getUrlPictures()) > 0) {
            Log.i(Utils.INFORMATION_LOG, BusinessRiskTheme.getPhotoList(mRisk.getUrlPictures()).get(0));
            mImageView1.setImageBitmap(BitmapStorage.loadImage(getContext(), BusinessRiskTheme.getPhotoList(mRisk.getUrlPictures()).get(0)));
        }
        int visibility2 = BusinessRiskTheme.getAeraState(mRisk.getUrlPictures()) > 1 ? View.VISIBLE : View.INVISIBLE;
        mImageView2.setVisibility(visibility2);
        if (BusinessRiskTheme.getAeraState(mRisk.getUrlPictures()) > 1) {
            Log.i(Utils.INFORMATION_LOG, BusinessRiskTheme.getPhotoList(mRisk.getUrlPictures()).get(1));
            mImageView2.setImageBitmap(BitmapStorage.loadImage(getContext(), BusinessRiskTheme.getPhotoList(mRisk.getUrlPictures()).get(1)));
        }
        int visibility3 = BusinessRiskTheme.getAeraState(mRisk.getUrlPictures()) > 2 ? View.VISIBLE : View.INVISIBLE;
        mImageView3.setVisibility(visibility3);
        if (BusinessRiskTheme.getAeraState(mRisk.getUrlPictures()) > 2) {
            Log.i(Utils.INFORMATION_LOG, BusinessRiskTheme.getPhotoList(mRisk.getUrlPictures()).get(2));
            mImageView3.setImageBitmap(BitmapStorage.loadImage(getContext(), BusinessRiskTheme.getPhotoList(mRisk.getUrlPictures()).get(2)));
        }
        int visibilityNext = BusinessRiskTheme.getAeraState(mRisk.getUrlPictures()) > 3 ? View.VISIBLE : View.INVISIBLE;
        mImageViewPlusLogo.setVisibility(visibilityNext);
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
        Intent intent = new Intent(getActivity(), RiskManagerChoiceActivity.class);
        intent.putExtra(BUNDLE_KEY_RISK_MANAGER, mParticipant.getId());
        ArrayList<String> participantListId = new ArrayList<>();
        ArrayList<String> participantNameListString = new ArrayList<>();
        ArrayList<String> participantFornameListString = new ArrayList<>();
        for (int i = 0 ; i < mParticipantList.size(); i++){
            participantListId.add(String.valueOf(mParticipantList.get(i).getId()));
            participantNameListString.add(mParticipantList.get(i).getName());
            participantFornameListString.add(mParticipantList.get(i).getForname());
        }
        intent.putExtra(BUNDLE_KEY_RISK_LIST_MANAGER_ID, participantListId);
        intent.putExtra(BUNDLE_KEY_RISK_LIST_MANAGER_NAME, participantNameListString);
        intent.putExtra(BUNDLE_KEY_RISK_LIST_MANAGER_FORNAME, participantFornameListString);
        startActivityForResult(intent, RISK_MANAGER_REQUEST_CODE);
    }

    //CHANGE severity value
    @OnClick(R.id.fragment_risk_severity_value)
    public void changeSeverityValue(){
        Intent intent = new Intent(getActivity(), IntKeyboardActivity.class);
        intent.putExtra(BUNDLE_KEY_CRITERIA_SCORE, SEVERITY);
        startActivityForResult(intent, CRITERIA_SCORE_REQUEST_CODE);
    }

    //CHANGE probability value
    @OnClick(R.id.fragment_risk_probability_score)
    public void changeProbabilityValue(){
        Intent intent = new Intent(getActivity(), IntKeyboardActivity.class);
        intent.putExtra(BUNDLE_KEY_CRITERIA_SCORE, PROBABILITY);
        startActivityForResult(intent, CRITERIA_SCORE_REQUEST_CODE);
    }

    //CHANGE detection value
    @OnClick(R.id.fragment_risk_detection_score)
    public void changeDetectionValue(){
        Intent intent = new Intent(getActivity(), IntKeyboardActivity.class);
        intent.putExtra(BUNDLE_KEY_CRITERIA_SCORE, DETECTION);
        startActivityForResult(intent, CRITERIA_SCORE_REQUEST_CODE);
    }

    //GO to PhotoViewPager
    @OnClick(R.id.fragment_risk_photo_case_1)
    public void goToPhotoViewPager1(){
        launchPhotoViewPager();
    }

    //GO to PhotoViewPager
    @OnClick(R.id.fragment_risk_photo_case_2)
    public void goToPhotoViewPager2(){
        launchPhotoViewPager();
    }

    //GO to PhotoViewPager
    @OnClick(R.id.fragment_risk_photo_case_3)
    public void goToPhotoViewPager3(){
        launchPhotoViewPager();
    }

    //LAUNCH viewpager
    private void launchPhotoViewPager(){
        this.updateRiskModel();
        Intent intent = new Intent(getActivity(), RiskPhotoViewPagerActivity.class);
        intent.putExtra(BUNDLE_PHOTO_LIST_RISK, mRisk.getUrlPictures());
        startActivityForResult(intent, RC_VIEWPAGER_UPLOAD);
    }

    //GO TO photo activity
    public void launchRiskPhotoActivity() {
        this.updateRiskModel();
        Intent intent = new Intent(getActivity(), PhotoRiskActivity.class);
        intent.putExtra(BUNDLE_PHOTO_RISK_ID, mRiskId);
        intent.putStringArrayListExtra(BUNDLE_PHOTO_LIST_RISK, BusinessRiskTheme.getPhotoList(mRisk.getUrlPictures()));
        startActivityForResult(intent, RC_PHOTO_UPLOAD);
    }

    //GO TO Corrective action IHM
    @OnClick(R.id.risk_file_first_link)
    public void riskFile_To_CorrectiveAction(){
        mCallback.riskFile_To_CorrectiveAction(mView, mRiskId, mProcessusStepInteger, mFmeiId, mRisk.getRisk());
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface RiskFileItemClickedListener{
        void riskFile_To_CorrectiveAction(View view, long riskId, int processusStepInteger, long fmeiId, String riskName);
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
                    mRisk.setGravity(newScore);
                    break;
                case PROBABILITY:
                    mProbabilityScore.setText(String.valueOf(newScore));
                    mRisk.setFrequencies(newScore);
                    break;
                case DETECTION:
                    mDetectionScore.setText(String.valueOf(newScore));
                    mRisk.setDetectability(newScore);
                    break;
            }
            mIprScore.setText(String.valueOf(Integer.valueOf(mSeverityScore.getText().toString())*
                    Integer.valueOf(mProbabilityScore.getText().toString())*
                    Integer.valueOf(mDetectionScore.getText().toString())));
        }
        if (RISK_MANAGER_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            long newId = data.getLongExtra(RiskManagerChoiceActivity.BUNDLE_NEW_MANAGER_ID, 1);
            String newForname = data.getStringExtra(RiskManagerChoiceActivity.BUNDLE_NEW_MANAGER_FORNAME);
            String newName = data.getStringExtra(RiskManagerChoiceActivity.BUNDLE_NEW_MANAGER_NAME);
            String newCompleteName = newForname + " " + newName;
            mManager.setText(newCompleteName);
            Participant participant = new Participant(newName, newForname);
            participant.setId(newId);
            this.updateParticipant(participant);
        }
        if (RC_PHOTO_UPLOAD == requestCode && resultCode == RESULT_OK){
            String uri = data.getStringExtra(PhotoRiskActivity.BUNDLE_PHOTO_UPDATE);
            Log.i(Utils.INFORMATION_LOG, "Photo import to fragment : " + uri);
            mRisk.setUrlPictures(BusinessRiskTheme.getStringUrl(mRisk.getUrlPictures(), uri));
            Log.i(Utils.INFORMATION_LOG, "Risk UrlPictures : " + mRisk.getUrlPictures());
            updateInformationsRiskPanel(mRisk);
        }
        if (RC_VIEWPAGER_UPLOAD == requestCode && resultCode == RESULT_OK){
            mRisk.setUrlPictures(data.getStringExtra(RiskPhotoViewPagerActivity.BUNDLE_LIST_TRANSFERT));
            if (mRisk.getUrlPictures().equals("")){
                mRisk.setUrlPictures(Utils.EMPTY);
            }
            updateInformationsRiskPanel(mRisk);
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

    //GET risk selected
    private void getRiskSelected(long riskId){
        this.mRiskViewModel.getRisk(riskId).observe(this, this::updateRisk);
    }

    //GET all participant
    private void getAllParticipant(){
        this.mRiskViewModel.getAllParticipant().observe(this, this::updateAllParticipant);
    }

    //GET participant attached to selected risk
    private void getRiskParticipant(long id){
        this.mRiskViewModel.getParticipant(id).observe(this, this::updateParticipant);
    }

    //GET team fmei attached to the fmei Id
    private void getTeamFmei(long fmeiId){
        this.mRiskViewModel.getTeamsWithLinkFmei(fmeiId).observe(this, this::updateTeamFmei);
    }

    //SAVE risk
    public void saveRiskSelected(){
        mRisk.setParticipantId(mParticipant.getId());
        this.updateRiskModel();
        BitmapStorage.purgePhotosInternalMemory(getContext(), mRisk.getUrlPictures(), mRiskId, false);
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
        this.updatePhotoAeara();
        mRisk.setGravity(Integer.valueOf(mSeverityScore.getText().toString()));
        mRisk.setFrequencies(Integer.valueOf(mProbabilityScore.getText().toString()));
        mRisk.setDetectability(Integer.valueOf(mDetectionScore.getText().toString()));
    }


    /**
     *  UI
     */

    //RECORD risk
    private void updateRisk(Risk risk) {
        if (risk != null) {
            mRisk = risk;
            this.updateInformationsRiskPanel(risk);
            getRiskParticipant(risk.getParticipantId());
            getTeamFmei(mFmeiId);
        }
    }

    //LOAD TEAM FMEI
    private void updateTeamFmei(List<TeamFmei> teamFmeiList){
        mTeamsParticipantId = new ArrayList<>();
        for (TeamFmei teamFmei : teamFmeiList){
            mTeamsParticipantId.add(teamFmei.getParticipantId());
        }
        getAllParticipant();
    }

    //RECORD participant
    private void updateParticipant(Participant participant){
        if (participant != null){
            mParticipant = participant;
            String completeName = "   " + participant.getForname() + " " + participant.getName();
            mManager.setText(completeName);
        }
    }

    //RECORD all participant
    private void updateAllParticipant(List<Participant> participants){
        mParticipantList = new ArrayList<>();
        for (Participant participant : participants){
            for (long teamParticipantId : mTeamsParticipantId){
                if (participant.getId() == teamParticipantId){
                    mParticipantList.add(participant);
                }
            }
        }
    }


}
