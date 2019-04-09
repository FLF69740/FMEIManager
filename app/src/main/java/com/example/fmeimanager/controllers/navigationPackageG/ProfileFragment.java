package com.example.fmeimanager.controllers.navigationPackageG;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.navigationPackageG.adapter.FmeaParticipantResumeAdapter;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.TeamFmei;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.utils.BitmapStorage;
import com.example.fmeimanager.utils.Utils;
import com.example.fmeimanager.viewmodels.GeneralViewModel;
import com.example.fmeimanager.viewmodels.ParticipantViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_ACTIVE_USER;
import static com.example.fmeimanager.MainActivity.DEFAULT_USER_ID;
import static com.example.fmeimanager.MainActivity.SHARED_MAIN_PROFILE_ID;

public class ProfileFragment extends Fragment {

    @BindView(R.id.fragment_profile_photo_aera) ImageView mPhotoProfile;
    @BindView(R.id.fragment_profile_desactivation) Button mDesactivation;
    @BindView(R.id.fragment_profile_function) TextView mProfileFunction;
    @BindView(R.id.fragment_profile_forname) TextView mProfileForname;
    @BindView(R.id.fragment_profile_name) TextView mProfileName;
    @BindView(R.id.fragment_profile_main_participant) Button mMainProfile;
    @BindView(R.id.fragment_profile_mail_information) TextView mProfileMail;
    @BindView(R.id.fragment_profile_tel_information) TextView mProfileTel;

    private View mView;
    private GeneralViewModel mGeneralViewModel;
    private long mParticipantId;
    private Participant mParticipant;

    private static final String BUNDLE_PARTICIPANT_ID = "BUNDLE_PARTICIPANT_ID";
    public static final String BUNDLE_KEY_DEFINITION = "BUNDLE_KEY_DEFINITION";
    public static final String BUNDLE_PHOTO_PROFILE_ID = "BUNDLE_PHOTO_PROFILE_ID";
    private static final int REQUEST_CODE_NEW_NAME = 181;
    private static final int REQUEST_CODE_NEW_FORNAME = 182;
    private static final int REQUEST_CODE_NEW_JOB = 183;
    private static final int REQUEST_CODE_NEW_MAIL = 184;
    private static final int REQUEST_CODE_NEW_TEL = 185;
    private static final int REQUEST_CODE_NEW_PHOTO = 186;

    public ProfileFragment() {}

    public static ProfileFragment newInstance(long participantId){
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle bundle = new Bundle(1);
        bundle.putLong(BUNDLE_PARTICIPANT_ID, participantId);
        profileFragment.setArguments(bundle);
        return profileFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, mView);
        this.configureViewModel();

        if (savedInstanceState != null){
            mParticipant = savedInstanceState.getParcelable(BUNDLE_KEY_PARTICIPANT_PARCEL);
            mParticipantId = savedInstanceState.getLong(BUNDLE_KEY_PARTICIPANT_ID_PARCEL);
            this.updateParticipant(mParticipant);
        }else {
            mParticipantId = getArguments().getLong(BUNDLE_PARTICIPANT_ID);
            getParticipant(mParticipantId);
        }

        return mView;
    }

    private static final String BUNDLE_KEY_PARTICIPANT_PARCEL = "BUNDLE_KEY_PARTICIPANT_PARCEL";
    private static final String BUNDLE_KEY_PARTICIPANT_ID_PARCEL = "BUNDLE_KEY_PARTICIPANT_ID_PARCEL";

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_KEY_PARTICIPANT_PARCEL, mParticipant);
        outState.putLong(BUNDLE_KEY_PARTICIPANT_ID_PARCEL, mParticipantId);
    }

    private FmeaParticipantResumeAdapter mAdapter;
    @BindView(R.id.fragment_profile_recycler_view_participation) RecyclerView mRecyclerView;

    //CONFIGURE recyclerView
    private void configureRecyclerView(List<String> listFmea, List<Boolean> listIsTeamLeader){
        this.mAdapter = new FmeaParticipantResumeAdapter(listFmea, listIsTeamLeader);
        this.mRecyclerView.setAdapter(mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.notifyDataSetChanged();
    }

    /**
     *  ACTION
     */

    // CREATE photo identity
    public void createPhotoIdentity() {
        Intent intent = new Intent(getActivity(), PhotoProfileActivity.class);
        intent.putExtra(BUNDLE_PHOTO_PROFILE_ID, mParticipantId);
        startActivityForResult(intent, REQUEST_CODE_NEW_PHOTO);
    }

    @OnClick(R.id.fragment_profile_desactivation)
    public void changeDesactivationProfile(){
        if (mParticipantId != getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID)){
            if (mParticipant.isActivated()) {
                mParticipant.setActivated(false);
                mDesactivation.setText(R.string.profile_section_profile_reactivation);
                mPhotoProfile.setVisibility(View.INVISIBLE);
            } else {
                mParticipant.setActivated(true);
                mDesactivation.setText(R.string.profile_section_profile_desactivation);
                mPhotoProfile.setVisibility(View.VISIBLE);
            }
        }else {
            Toast.makeText(getContext(), getString(R.string.profile_section_main_participant_advertising), Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.fragment_profile_main_participant)
    public void changeMainProfile(){
        if (mParticipantId != getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID) &&
        mParticipant.isActivated()) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE);
            sharedPreferences.edit().putLong(BUNDLE_KEY_ACTIVE_USER, mParticipantId).apply();
            Snackbar.make(mView, mParticipant.getForname() + " " + mParticipant.getName() + " " +
                    mView.getContext().getString(R.string.profile_section_new_main_user), Snackbar.LENGTH_SHORT).show();
            saveParticipant();
        }
    }

    @OnClick(R.id.fragment_profile_name)
    public void changeName(){
        Intent intent = new Intent(getActivity(), WriteFromProfileActivity.class);
        intent.putExtra(BUNDLE_KEY_DEFINITION, mProfileName.getText().toString());
        startActivityForResult(intent, REQUEST_CODE_NEW_NAME);
    }

    @OnClick(R.id.fragment_profile_forname)
    public void changeForame(){
        Intent intent = new Intent(getActivity(), WriteFromProfileActivity.class);
        intent.putExtra(BUNDLE_KEY_DEFINITION, mProfileForname.getText().toString());
        startActivityForResult(intent, REQUEST_CODE_NEW_FORNAME);
    }

    @OnClick(R.id.fragment_profile_function)
    public void changeFunction(){
        Intent intent = new Intent(getActivity(), WriteFromProfileActivity.class);
        intent.putExtra(BUNDLE_KEY_DEFINITION, mProfileFunction.getText().toString());
        startActivityForResult(intent, REQUEST_CODE_NEW_JOB);
    }

    @OnClick(R.id.fragment_profile_mail_information)
    public void changeMail(){
        Intent intent = new Intent(getActivity(), WriteFromProfileActivity.class);
        intent.putExtra(BUNDLE_KEY_DEFINITION, mProfileMail.getText().toString());
        startActivityForResult(intent, REQUEST_CODE_NEW_MAIL);
    }

    @OnClick(R.id.fragment_profile_tel_information)
    public void changeTel(){
        Intent intent = new Intent(getActivity(), WriteFromProfileActivity.class);
        intent.putExtra(BUNDLE_KEY_DEFINITION, mProfileTel.getText().toString());
        startActivityForResult(intent, REQUEST_CODE_NEW_TEL);
    }

    /**
     *  ACTIVITY FOR RESULT
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE_NEW_NAME == requestCode && RESULT_OK == resultCode){
            if (data.getStringExtra(WriteFromProfileActivity.BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION) != null){
                mProfileName.setText(data.getStringExtra(WriteFromProfileActivity.BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION));
                mParticipant.setName(data.getStringExtra(WriteFromProfileActivity.BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION));
            }
        }
        if (REQUEST_CODE_NEW_FORNAME == requestCode && RESULT_OK == resultCode){
            if (data.getStringExtra(WriteFromProfileActivity.BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION) != null){
                mProfileForname.setText(data.getStringExtra(WriteFromProfileActivity.BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION));
                mParticipant.setForname(data.getStringExtra(WriteFromProfileActivity.BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION));
            }
        }
        if (REQUEST_CODE_NEW_JOB == requestCode && RESULT_OK == resultCode){
            if (data.getStringExtra(WriteFromProfileActivity.BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION) != null){
                mProfileFunction.setText(data.getStringExtra(WriteFromProfileActivity.BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION));
                mParticipant.setFunction(data.getStringExtra(WriteFromProfileActivity.BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION));
            }
        }
        if (REQUEST_CODE_NEW_MAIL == requestCode && RESULT_OK == resultCode){
            if (data.getStringExtra(WriteFromProfileActivity.BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION) != null){
                mProfileMail.setText(data.getStringExtra(WriteFromProfileActivity.BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION));
                mParticipant.setMail(data.getStringExtra(WriteFromProfileActivity.BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION));
            }
        }
        if (REQUEST_CODE_NEW_TEL == requestCode && RESULT_OK == resultCode){
            if (data.getStringExtra(WriteFromProfileActivity.BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION) != null){
                mProfileTel.setText(data.getStringExtra(WriteFromProfileActivity.BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION));
                mParticipant.setTel(data.getStringExtra(WriteFromProfileActivity.BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION));
            }
        }
        if (REQUEST_CODE_NEW_PHOTO == requestCode && RESULT_OK == resultCode){
            if (data.getStringExtra(PhotoProfileActivity.BUNDLE_PROFILE_PHOTO_UPDATE) != null){
                mPhotoProfile.setImageBitmap(BitmapStorage.loadImage(getContext(), "P" + mParticipantId));
            }
        }
    }

    /**
     *  DATAS
     */

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(getContext());
        this.mGeneralViewModel = ViewModelProviders.of(this, viewModelFactory).get(GeneralViewModel.class);
        this.mGeneralViewModel.init(1);
    }

    //GET PARTICIPANT
    private void getParticipant(long id){
        this.mGeneralViewModel.getParticipant(id).observe(this, this::updateParticipant);
    }

    //GET fmea about the participant
    private void getFmeaParticipation(long participantId){
        this.mGeneralViewModel.getTeamsWithLinkParticipant(participantId).observe(this, this::updateFmeaList);
    }

    /**
     *  UI
     */

    //RECORD fmea list about the participant
    private void updateFmeaList(List<TeamFmei> teamFmeiList){
        List<String> fmeaListString = new ArrayList<>();
        List<Boolean> booleanTeamLeaderList = new ArrayList<>();
        if (teamFmeiList != null && teamFmeiList.size() != 0){
            for (int i = 0 ; i < teamFmeiList.size() ; i++){
                fmeaListString.add(getString(R.string.profile_section_fmea) + teamFmeiList.get(i).getFmeiId());
                if (mParticipantId == teamFmeiList.get(i).getParticipantId()){
                    booleanTeamLeaderList.add(true);
                }else {
                    booleanTeamLeaderList.add(false);
                }
            }
            configureRecyclerView(fmeaListString, booleanTeamLeaderList);
        }
    }

    //RECORD participant information into panel
    private void updateParticipant(Participant participant){
        mParticipant = participant;
        if (BitmapStorage.isFileExist(getContext(),"P" + mParticipantId)){
            mPhotoProfile.setImageBitmap(BitmapStorage.loadImage(getContext(), "P" + mParticipantId));
        }
        mProfileName.setText(participant.getName());
        mProfileForname.setText(participant.getForname());
        if (!participant.getFunction().equals(Utils.EMPTY)) {
            mProfileFunction.setText(participant.getFunction());
        }
        if (!participant.getMail().equals(Utils.EMPTY)){
            mProfileMail.setText(participant.getMail());
        }
        if (!participant.getTel().equals(Utils.EMPTY)){
            mProfileTel.setText(participant.getTel());
        }
        if (mParticipant.isActivated()){
            mDesactivation.setText(R.string.profile_section_profile_desactivation);
            mPhotoProfile.setVisibility(View.VISIBLE);

        } else {
            mDesactivation.setText(R.string.profile_section_profile_reactivation);
            mPhotoProfile.setVisibility(View.INVISIBLE);
        }
        getFmeaParticipation(mParticipant.getId());
    }

    //SAVE participant information into Database
    public void saveParticipant(){
        if (mProfileName.getText().toString().equals("")){
            mParticipant.setName(getString(R.string.Doe));
        }else {
            mParticipant.setName(mProfileName.getText().toString());
        }
        if (mProfileForname.getText().toString().equals("")){
            mParticipant.setForname(getString(R.string.John));
        }else {
            mParticipant.setForname(mProfileForname.getText().toString());
        }
        if (mProfileFunction.getText().toString().equals("")){
            mParticipant.setFunction(Utils.EMPTY);
        }else {
            mParticipant.setFunction(mProfileFunction.getText().toString());
        }
        if (mProfileMail.getText().toString().equals("")){
            mParticipant.setMail(Utils.EMPTY);
        }else {
            mParticipant.setMail(mProfileMail.getText().toString());
        }
        if (mProfileTel.getText().toString().equals("")){
            mParticipant.setTel(Utils.EMPTY);
        }else {
            mParticipant.setTel(mProfileTel.getText().toString());
        }

        mGeneralViewModel.updateParticipant(mParticipant);
        Snackbar.make(mView, getString(R.string.Risk_file_corrective_save), Snackbar.LENGTH_SHORT).show();
    }
}
