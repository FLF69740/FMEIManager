package com.example.fmeimanager.controllers.navigationPackageA.correctiveActionTheme;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
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

import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.navigationPackageA.riskTheme.BusinessRiskTheme;
import com.example.fmeimanager.controllers.navigationPackageA.riskTheme.IntKeyboardActivity;
import com.example.fmeimanager.controllers.navigationPackageA.riskTheme.PhotoRiskActivity;
import com.example.fmeimanager.controllers.navigationPackageA.riskTheme.RiskManagerChoiceActivity;
import com.example.fmeimanager.controllers.navigationPackageA.riskTheme.RiskPhotoViewPagerActivity;
import com.example.fmeimanager.database.CorrectiveAction;
import com.example.fmeimanager.database.Risk;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.utils.BitmapStorage;
import com.example.fmeimanager.utils.Utils;
import com.example.fmeimanager.viewmodels.RiskViewModel;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CorrectiveActionFragment extends Fragment {

    @BindView(R.id.corrective_file_textview_manager) TextView mManager;
    @BindView(R.id.corrective_action_first_link) Button mGoActionCorrectiveBtn;
    @BindView(R.id.fragment_corrective_action_traceability_name) EditText mIdentification;
    @BindView(R.id.fragment_corrective_action_parts_name) EditText mPart;
    @BindView(R.id.fragment_corrective_action_corrective_action_title) EditText mCorrectiveTitle;
    @BindView(R.id.fragment_corrective_action_description) EditText mDescription;
    @BindView(R.id.fragment_corrective_action_deadline_btn) TextView mDeadLine;
    @BindView(R.id.fragment_corrective_action_realisation_btn) TextView mRealisationDate;
    @BindView(R.id.fragment_corrective_photo_case_1) ImageView mImageView1;
    @BindView(R.id.fragment_corrective_photo_case_2) ImageView mImageView2;
    @BindView(R.id.fragment_corrective_photo_case_3) ImageView mImageView3;
    @BindView(R.id.fragment_corrective_photo_case_plus) ImageView mImageViewPlusLogo;
    @BindView(R.id.fragment_corrective_ipr_score) TextView mIprScore;
    @BindView(R.id.fragment_corrective_severity_value) TextView mSeverityScore;
    @BindView(R.id.fragment_corrective_probability_score) TextView mProbabilityScore;
    @BindView(R.id.fragment_corrective_detection_score) TextView mDetectionScore;
    @BindView(R.id.fragment_corrective_ask_btn) Button mAsk;

    private static final String BUNDLE_RISK_ID = "BUNDLE_RISK_ID";
    private static final String BUNDLE_PROCESSUS_STEP = "BUNDLE_PROCESSUS_STEP";
    public static final String BUNDLE_KEY_CORRECTIVE_CRITERIA_SCORE = "BUNDLE_KEY_CRITERIA_SCORE";
    private static final String SEVERITY = "SEVERITY";
    private static final String PROBABILITY = "PROBABILITY";
    private static final String DETECTION = "DETECTION";
    public static final String BUNDLE_KEY_CORRECTIVE_MANAGER = "BUNDLE_KEY_RISK_MANAGER";
    public static final String BUNDLE_KEY_CORRECTIVE_LIST_MANAGER_ID = "BUNDLE_KEY_RISK_LIST_MANAGER_ID";
    public static final String BUNDLE_KEY_CORRECTIVE_LIST_MANAGER_NAME = "BUNDLE_KEY_RISK_LIST_MANAGER_NAME";
    public static final String BUNDLE_KEY_CORRECTIVE_LIST_MANAGER_FORNAME = "BUNDLE_KEY_RISK_LIST_MANAGER_FORNAME";
    public static final String BUNDLE_PHOTO_CORRECTIVE_ID = "BUNDLE_PHOTO_RISK_ID";
    public static final String BUNDLE_PHOTO_LIST_CORRECTIVE = "BUNDLE_PHOTO_LIST_RISK";
    private static final int CORRECTIVE_MANAGER_REQUEST_CODE = 6654;
    private static final int CRITERIA_CORRECTIVE_SCORE_REQUEST_CODE = 9987;
    private static final int RC_VIEWPAGER_UPLOAD = 10151;
    private static final int RC_PHOTO_UPLOAD = 10010;

    private View mView;
    private RiskViewModel mRiskViewModel;
    private long mRiskId;
    private long mCorrectiveId = 0;
    private int mProcessusStepInteger;
    private CorrectiveAction mCorrectiveAction;
    private Participant mParticipant;
    private List<Participant> mParticipantList;

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

        this.configureViewModel();
        this.getAdministrator(1);

        if (savedInstanceState != null){
            mCorrectiveAction = savedInstanceState.getParcelable(BUNDLE_KEY_CORRECTIVE_PARCEL);
            updateCorrectiveAction(mCorrectiveAction);
            mRiskId = savedInstanceState.getLong(BUNDLE_RISK_ID_PARCEL);
            mCorrectiveId = savedInstanceState.getLong(BUNDLE_KEY_CORRECTIVE_TEMP_ID_PARCEL);
        }else {
            mRiskId = getArguments().getLong(BUNDLE_RISK_ID, 100);
            mProcessusStepInteger = getArguments().getInt(BUNDLE_PROCESSUS_STEP, 0);
            this.getCorrectiveAction(mRiskId);
        }

        return mView;
    }

    private static final String BUNDLE_KEY_CORRECTIVE_PARCEL = "BUNDLE_KEY_CORRECTIVE_PARCEL";
    private static final String BUNDLE_KEY_CORRECTIVE_TEMP_ID_PARCEL = "BUNDLE_KEY_CORRECTIVE_TEMP_ID_PARCEL";
    private static final String BUNDLE_RISK_ID_PARCEL = "BUNDLE_RISK_ID_PARCEL";

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_KEY_CORRECTIVE_PARCEL, mCorrectiveAction);
        outState.putLong(BUNDLE_KEY_CORRECTIVE_TEMP_ID_PARCEL, mCorrectiveId);
        outState.putLong(BUNDLE_RISK_ID_PARCEL, mRiskId);
    }

    //BUILD IHM with corrective action information
    private void updateInformationCorrectivePanel(CorrectiveAction correctiveAction) {
        if (!correctiveAction.getIdentification().equals(Utils.EMPTY)){
            mIdentification.setText(correctiveAction.getIdentification());
        }
        if (!correctiveAction.getParts().equals(Utils.EMPTY)) {
            mPart.setText(correctiveAction.getParts());
        }
        if (!correctiveAction.getCorrectiveAction().equals(Utils.EMPTY)) {
            mCorrectiveTitle.setText(correctiveAction.getCorrectiveAction());
        }
        if (!correctiveAction.getCorrectiveDescription().equals(Utils.EMPTY)) {
            mDescription.setText(correctiveAction.getCorrectiveDescription());
        }
        mDeadLine.setText(correctiveAction.getDeadLineDate());
        if (!correctiveAction.getRealisationDate().equals(Utils.EMPTY)) {
            mRealisationDate.setText(correctiveAction.getRealisationDate());
        }
        this.updatePhotoAeara();
        mIprScore.setText(String.valueOf(correctiveAction.getNewGravity()* correctiveAction.getNewFrequencies()* correctiveAction.getNewDetectability()));
        mSeverityScore.setText(String.valueOf(correctiveAction.getNewGravity()));
        mProbabilityScore.setText(String.valueOf(correctiveAction.getNewFrequencies()));
        mDetectionScore.setText(String.valueOf(correctiveAction.getNewDetectability()));
    }

    //Upadte photo aera
    private void updatePhotoAeara(){
        int visibility1 = BusinessRiskTheme.getAeraState(mCorrectiveAction.getUrlPictures()) > 0 ? View.VISIBLE : View.INVISIBLE;
        mImageView1.setVisibility(visibility1);
        if (BusinessRiskTheme.getAeraState(mCorrectiveAction.getUrlPictures()) > 0) {
            Log.i(Utils.INFORMATION_LOG, BusinessRiskTheme.getPhotoList(mCorrectiveAction.getUrlPictures()).get(0));
            mImageView1.setImageBitmap(BitmapStorage.loadImage(getContext(), BusinessRiskTheme.getPhotoList(mCorrectiveAction.getUrlPictures()).get(0)));
        }
        int visibility2 = BusinessRiskTheme.getAeraState(mCorrectiveAction.getUrlPictures()) > 1 ? View.VISIBLE : View.INVISIBLE;
        mImageView2.setVisibility(visibility2);
        if (BusinessRiskTheme.getAeraState(mCorrectiveAction.getUrlPictures()) > 1) {
            Log.i(Utils.INFORMATION_LOG, BusinessRiskTheme.getPhotoList(mCorrectiveAction.getUrlPictures()).get(1));
            mImageView2.setImageBitmap(BitmapStorage.loadImage(getContext(), BusinessRiskTheme.getPhotoList(mCorrectiveAction.getUrlPictures()).get(1)));
        }
        int visibility3 = BusinessRiskTheme.getAeraState(mCorrectiveAction.getUrlPictures()) > 2 ? View.VISIBLE : View.INVISIBLE;
        mImageView3.setVisibility(visibility3);
        if (BusinessRiskTheme.getAeraState(mCorrectiveAction.getUrlPictures()) > 2) {
            Log.i(Utils.INFORMATION_LOG, BusinessRiskTheme.getPhotoList(mCorrectiveAction.getUrlPictures()).get(2));
            mImageView3.setImageBitmap(BitmapStorage.loadImage(getContext(), BusinessRiskTheme.getPhotoList(mCorrectiveAction.getUrlPictures()).get(2)));
        }
        int visibilityNext = BusinessRiskTheme.getAeraState(mCorrectiveAction.getUrlPictures()) > 3 ? View.VISIBLE : View.INVISIBLE;
        mImageViewPlusLogo.setVisibility(visibilityNext);
    }

    /**
     *  ACTION
     */

    //CHANGE Participant
    @OnClick(R.id.corrective_file_textview_manager)
    public void changeParticipant(){
        Log.i(Utils.INFORMATION_LOG, "change participant , id : " + mParticipant.getId());
        Intent intent = new Intent(getActivity(), RiskManagerChoiceActivity.class);
        intent.putExtra(BUNDLE_KEY_CORRECTIVE_MANAGER, mParticipant.getId());
        ArrayList<String> participantListId = new ArrayList<>();
        ArrayList<String> participantNameListString = new ArrayList<>();
        ArrayList<String> participantFornameListString = new ArrayList<>();
        for (int i = 0 ; i < mParticipantList.size(); i++){
            participantListId.add(String.valueOf(mParticipantList.get(i).getId()));
            participantNameListString.add(mParticipantList.get(i).getName());
            participantFornameListString.add(mParticipantList.get(i).getForname());
        }
        intent.putExtra(BUNDLE_KEY_CORRECTIVE_LIST_MANAGER_ID, participantListId);
        intent.putExtra(BUNDLE_KEY_CORRECTIVE_LIST_MANAGER_NAME, participantNameListString);
        intent.putExtra(BUNDLE_KEY_CORRECTIVE_LIST_MANAGER_FORNAME, participantFornameListString);
        startActivityForResult(intent, CORRECTIVE_MANAGER_REQUEST_CODE);
    }

    //CHANGE severity value
    @OnClick(R.id.fragment_corrective_severity_value)
    public void changeSeverityValue(){
        Intent intent = new Intent(getActivity(), IntKeyboardActivity.class);
        intent.putExtra(BUNDLE_KEY_CORRECTIVE_CRITERIA_SCORE, SEVERITY);
        startActivityForResult(intent, CRITERIA_CORRECTIVE_SCORE_REQUEST_CODE);
    }

    //CHANGE probability value
    @OnClick(R.id.fragment_corrective_probability_score)
    public void changeProbabilityValue(){
        Intent intent = new Intent(getActivity(), IntKeyboardActivity.class);
        intent.putExtra(BUNDLE_KEY_CORRECTIVE_CRITERIA_SCORE, PROBABILITY);
        startActivityForResult(intent, CRITERIA_CORRECTIVE_SCORE_REQUEST_CODE);
    }

    //CHANGE detection value
    @OnClick(R.id.fragment_corrective_detection_score)
    public void changeDetectionValue(){
        Intent intent = new Intent(getActivity(), IntKeyboardActivity.class);
        intent.putExtra(BUNDLE_KEY_CORRECTIVE_CRITERIA_SCORE, DETECTION);
        startActivityForResult(intent, CRITERIA_CORRECTIVE_SCORE_REQUEST_CODE);
    }



    //GO to PhotoViewPager
    @OnClick(R.id.fragment_corrective_photo_case_1)
    public void goToPhotoViewPager1(){
        launchPhotoViewPager();
    }

    //GO to PhotoViewPager
    @OnClick(R.id.fragment_corrective_photo_case_2)
    public void goToPhotoViewPager2(){
        launchPhotoViewPager();
    }

    //GO to PhotoViewPager
    @OnClick(R.id.fragment_corrective_photo_case_3)
    public void goToPhotoViewPager3(){
        launchPhotoViewPager();
    }

    //LAUNCH viewpager
    private void launchPhotoViewPager(){
        this.updateCorrectiveModel();
        Intent intent = new Intent(getActivity(), RiskPhotoViewPagerActivity.class);
        intent.putExtra(BUNDLE_PHOTO_LIST_CORRECTIVE, mCorrectiveAction.getUrlPictures());
        startActivityForResult(intent, RC_VIEWPAGER_UPLOAD);
    }

    //GO TO photo activity
    public void launchCorrectivePhotoActivity() {
        if (mCorrectiveId != 0) {
            this.updateCorrectiveModel();
            Intent intent = new Intent(getActivity(), PhotoCorrectiveActivity.class);
            intent.putExtra(BUNDLE_PHOTO_CORRECTIVE_ID, mCorrectiveId);
            intent.putStringArrayListExtra(BUNDLE_PHOTO_LIST_CORRECTIVE, BusinessRiskTheme.getPhotoList(mCorrectiveAction.getUrlPictures()));
            startActivityForResult(intent, RC_PHOTO_UPLOAD);
        }else {
            Toast.makeText(getContext(), getString(R.string.Risk_file_corrective_advertising_photo), Toast.LENGTH_LONG).show();
        }
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (CRITERIA_CORRECTIVE_SCORE_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            String sourceCriteria = data.getStringExtra(IntKeyboardActivity.BUNDLE_KEYBOARD_SOURCE);
            int newScore = data.getIntExtra(IntKeyboardActivity.BUNDLE_KEYBOARD_SCORE, 10);
            switch (sourceCriteria){
                case SEVERITY:
                    mSeverityScore.setText(String.valueOf(newScore));
                    mCorrectiveAction.setNewGravity(newScore);
                    break;
                case PROBABILITY:
                    mProbabilityScore.setText(String.valueOf(newScore));
                    mCorrectiveAction.setNewFrequencies(newScore);
                    break;
                case DETECTION:
                    mDetectionScore.setText(String.valueOf(newScore));
                    mCorrectiveAction.setNewDetectability(newScore);
                    break;
            }
            mIprScore.setText(String.valueOf(Integer.valueOf(mSeverityScore.getText().toString())*
                    Integer.valueOf(mProbabilityScore.getText().toString())*
                    Integer.valueOf(mDetectionScore.getText().toString())));
        }
        if (CORRECTIVE_MANAGER_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
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
            String uri = data.getStringExtra(PhotoCorrectiveActivity.BUNDLE_CORRECTIVE_PHOTO_UPDATE);
            Log.i(Utils.INFORMATION_LOG, "Photo import to fragment : " + uri);
            mCorrectiveAction.setUrlPictures(BusinessRiskTheme.getStringUrl(mCorrectiveAction.getUrlPictures(), uri));
            Log.i(Utils.INFORMATION_LOG, "Corrective UrlPictures : " + mCorrectiveAction.getUrlPictures());
            updateInformationCorrectivePanel(mCorrectiveAction);
        }
        if (RC_VIEWPAGER_UPLOAD == requestCode && resultCode == RESULT_OK){
            mCorrectiveAction.setUrlPictures(data.getStringExtra(RiskPhotoViewPagerActivity.BUNDLE_LIST_TRANSFERT));
            if (mCorrectiveAction.getUrlPictures().equals("")){
                mCorrectiveAction.setUrlPictures(Utils.EMPTY);
            }
            updateInformationCorrectivePanel(mCorrectiveAction);
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

    //GET all participant
    private void getAllParticipant(){
        this.mRiskViewModel.getAllParticipant().observe(this, this::updateAllParticipant);
    }

    //GET participant attached to selected risk
    private void getCorrectiveParticipant(long id){
        this.mRiskViewModel.getParticipant(id).observe(this, this::updateParticipant);
    }

    //SAVE risk
    public void saveCorrectiveActionSelected(){
        mCorrectiveAction.setParticipantId(mParticipant.getId());
        this.updateCorrectiveModel();
        if (mCorrectiveId != 0) {
            BitmapStorage.purgePhotosInternalMemory(getContext(), mCorrectiveAction.getUrlPictures(), mCorrectiveId, true);
            this.mRiskViewModel.updateCorrectiveAction(mCorrectiveAction);
        }else {
            this.mRiskViewModel.createCorrectiveAction(mCorrectiveAction);
            this.getCorrectiveAction(mRiskId);
        }
        Snackbar.make(mView, mView.getContext().getString(R.string.Risk_file_corrective_save), Snackbar.LENGTH_SHORT).show();
    }

    //UPADE risk model
    private void updateCorrectiveModel(){
        mCorrectiveAction.setIdentification(mIdentification.getText().toString());
        mCorrectiveAction.setParts(mPart.getText().toString());
        mCorrectiveAction.setCorrectiveAction(mCorrectiveTitle.getText().toString());
        mCorrectiveAction.setCorrectiveDescription(mDescription.getText().toString());
        mCorrectiveAction.setDeadLineDate(mDeadLine.getText().toString());
        if (mRealisationDate.getText().toString().equals(getString(R.string.Risk_file_corrective_realisation_default))){
            mCorrectiveAction.setRealisationDate(Utils.EMPTY);
        }else {
            mCorrectiveAction.setRealisationDate(mRealisationDate.getText().toString());
        }
        this.updatePhotoAeara();
        mCorrectiveAction.setNewGravity(Integer.valueOf(mSeverityScore.getText().toString()));
        mCorrectiveAction.setNewFrequencies(Integer.valueOf(mProbabilityScore.getText().toString()));
        mCorrectiveAction.setNewDetectability(Integer.valueOf(mDetectionScore.getText().toString()));
    }

    /**
     *  UI
     */

    private void updateAdministrator(Participant participant){
     //   Toast.makeText(getContext(), participant.getForname() + " " + participant.getName(), Toast.LENGTH_SHORT).show();
    }

    //LOAD corrective action (!ifExist : create a new one)
    private void updateCorrectiveAction(CorrectiveAction correctiveAction) {
        if (correctiveAction != null){
            mCorrectiveAction = correctiveAction;
            mCorrectiveId = correctiveAction.getId();
            getCorrectiveParticipant(correctiveAction.getParticipantId());
        }else {
            DateTime dateTime = new DateTime();
            mCorrectiveAction = new CorrectiveAction(Utils.EMPTY, dateTime.toString("dd/MM/yyyy"), Utils.EMPTY, Utils.EMPTY, dateTime.toString("dd/MM/yyyy"), mRiskId, 1);
            mCorrectiveId = 0;
            getCorrectiveParticipant(1);
        }

        this.editCalendars();
        this.updateInformationCorrectivePanel(mCorrectiveAction);
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
        if (participants != null){
            mParticipantList = participants;
        }
    }

    /**
     *  DATE PICKER DEFINITION
     */

    private DateTime mCalendarDeadline = new DateTime();
    private DateTime mCalendarRealisation = new DateTime();
    private String mStringDeadLine = "", mStringRealisation = "";

    DatePickerDialog.OnDateSetListener datePickerDeadLine = (view, year, month, dayOfMonth) -> {
        DateTime calendar = new DateTime();
        calendar = calendar.year().setCopy(year);
        calendar = calendar.monthOfYear().setCopy(month+1);
        calendar = calendar.dayOfMonth().setCopy(dayOfMonth);
        mStringDeadLine = calendar.toString(Utils.DATE_FORMAT);
        mDeadLine.setText(mStringDeadLine);
        mCalendarDeadline = calendar;
        mCorrectiveAction.setDeadLineDate(mStringDeadLine);
    };

    DatePickerDialog.OnDateSetListener datePickerRealisation = (view, year, month, dayOfMonth) -> {
        DateTime calendar = new DateTime();
        calendar = calendar.year().setCopy(year);
        calendar = calendar.monthOfYear().setCopy(month+1);
        calendar = calendar.dayOfMonth().setCopy(dayOfMonth);
        mStringRealisation = calendar.toString(Utils.DATE_FORMAT);
        mRealisationDate.setText(mStringRealisation);
        mCalendarRealisation = calendar;
        mCorrectiveAction.setRealisationDate(mStringRealisation);
    };

    private void editCalendars(){
        mCalendarDeadline = BusinessRiskTheme.getDateMemory(mCorrectiveAction.getDeadLineDate());
        mDeadLine.setText(mCalendarDeadline.toString(Utils.DATE_FORMAT));
        mDeadLine.setOnClickListener(v -> new DatePickerDialog(getContext(), datePickerDeadLine,
                mCalendarDeadline.getYear(), mCalendarDeadline.getMonthOfYear()-1, mCalendarDeadline.getDayOfMonth()).show());

        if (!mCorrectiveAction.getRealisationDate().equals(Utils.EMPTY)){
            mCalendarRealisation = BusinessRiskTheme.getDateMemory(mCorrectiveAction.getRealisationDate());
            mRealisationDate.setText(mCalendarRealisation.toString(Utils.DATE_FORMAT));
        }
        mRealisationDate.setOnClickListener(v -> new DatePickerDialog(getContext(), datePickerRealisation,
                mCalendarRealisation.getYear(), mCalendarRealisation.getMonthOfYear()-1, mCalendarRealisation.getDayOfMonth()).show());

    }

}
