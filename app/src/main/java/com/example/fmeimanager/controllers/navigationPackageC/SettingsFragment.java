package com.example.fmeimanager.controllers.navigationPackageC;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.fmeimanager.R;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.utils.BitmapStorage;
import com.example.fmeimanager.viewmodels.ParticipantViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_ACTIVE_USER;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_BUSINESS_NAME;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_HIGH_VALUE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_LOW_VALUE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_MEDIUM_VALUE;
import static com.example.fmeimanager.MainActivity.DEFAULT_USER_ID;
import static com.example.fmeimanager.MainActivity.SHARED_BUSINESS_NAME;
import static com.example.fmeimanager.MainActivity.SHARED_HIGH_SCORE;
import static com.example.fmeimanager.MainActivity.SHARED_LOW_SCORE;
import static com.example.fmeimanager.MainActivity.SHARED_MAIN_PROFILE_ID;
import static com.example.fmeimanager.MainActivity.SHARED_MEDIUM_SCORE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private View mView;
    private int mLowInt, mMediumInt, mHighInt;
    private ParticipantViewModel mParticipantViewModel;
    public static final String BUNDLE_KEY_SETTINGS_DEFINITION = "BUNDLE_KEY_SETTINGS_DEFINITION";
    private static final int REQUEST_CODE_NEW_LOGO = 286;
    private static final int REQUEST_CODE_NEW_BUSINESS_NAME = 2810;
    private static final int REQUEST_CODE_NEW_LOW_SCORE = 2820;
    private static final int REQUEST_CODE_NEW_MEDIUM_SCORE = 2830;
    private static final int REQUEST_CODE_NEW_HIGH_SCORE = 2840;

    @BindView(R.id.frament_settings_logo) ImageView mLogo;
    @BindView(R.id.fragment_settings_business_name) TextView mBusinessName;
    @BindView(R.id.fragment_settings_low_score) TextView mLowScore;
    @BindView(R.id.fragment_settings_medium_score) TextView mMediumScore;
    @BindView(R.id.fragment_settings_high_score) TextView mHighScore;

    public SettingsFragment() {}

    public static SettingsFragment newInstance(){
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, mView);

        this.configureViewModel();
        this.getAdministrator(getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID));

        mBusinessName.setText(getActivity().getSharedPreferences(SHARED_BUSINESS_NAME, MODE_PRIVATE).getString(BUNDLE_KEY_BUSINESS_NAME, ""));

        mLowInt = getActivity().getSharedPreferences(SHARED_LOW_SCORE, MODE_PRIVATE).getInt(BUNDLE_KEY_LOW_VALUE, 100);
        mLowScore.setText(String.valueOf(mLowInt));

        mMediumInt = getActivity().getSharedPreferences(SHARED_MEDIUM_SCORE, MODE_PRIVATE).getInt(BUNDLE_KEY_MEDIUM_VALUE, 150);
        mMediumScore.setText(String.valueOf(mMediumInt));

        mHighInt = getActivity().getSharedPreferences(SHARED_HIGH_SCORE, MODE_PRIVATE).getInt(BUNDLE_KEY_HIGH_VALUE, 200);
        mHighScore.setText(String.valueOf(mHighInt));

        this.loadLogo();

        return mView;
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface SettingsListener{
        void updateSettingsNavHeader(Participant participant);
    }

    //callback for button clicked
    private SettingsListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (SettingsListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }

    /**
     *  DATAS
     */

    //CONFIGURE viewmodel
    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(getContext());
        this.mParticipantViewModel = ViewModelProviders.of(this, viewModelFactory).get(ParticipantViewModel.class);
        this.mParticipantViewModel.init(getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID));
    }

    //GET observer administrator
    private void getAdministrator(long id){
        this.mParticipantViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    /**
     *  UI
     */

    //GET administrator identification
    private void updateAdministrator(Participant participant){
        mCallback.updateSettingsNavHeader(participant);
    }

    /**
     *  ACTION
     */

    //UPDATE logo
    private void loadLogo(){
        if (BitmapStorage.isFileExist(getContext(), "LOGO")){
            mLogo.setImageBitmap(BitmapStorage.loadImage(getContext(), "LOGO"));
        }

        mLogo.setOnClickListener(v -> startActivityForResult(new Intent(getActivity(), PhotoLogoActivity.class), REQUEST_CODE_NEW_LOGO));
    }

    @OnClick(R.id.fragment_settings_business_name)
    public void changebusinessName(){
        Intent intent = new Intent(getActivity(), WriteFromSettingsActivity.class);
        intent.putExtra(BUNDLE_KEY_SETTINGS_DEFINITION, mBusinessName.getText().toString());
        startActivityForResult(intent, REQUEST_CODE_NEW_BUSINESS_NAME);
    }

    @OnClick(R.id.fragment_settings_low_score)
    public void changeLowScore(){
        Intent intent = new Intent(getActivity(), WriteFromSettingsActivity.class);
        intent.putExtra(BUNDLE_KEY_SETTINGS_DEFINITION, mLowScore.getText().toString());
        startActivityForResult(intent, REQUEST_CODE_NEW_LOW_SCORE);
    }

    @OnClick(R.id.fragment_settings_medium_score)
    public void chnageMediumScore(){
        Intent intent = new Intent(getActivity(), WriteFromSettingsActivity.class);
        intent.putExtra(BUNDLE_KEY_SETTINGS_DEFINITION, mMediumScore.getText().toString());
        startActivityForResult(intent, REQUEST_CODE_NEW_MEDIUM_SCORE);
    }

    @OnClick(R.id.fragment_settings_high_score)
    public void chanegHighScore(){
        Intent intent = new Intent(getActivity(), WriteFromSettingsActivity.class);
        intent.putExtra(BUNDLE_KEY_SETTINGS_DEFINITION, mHighScore.getText().toString());
        startActivityForResult(intent, REQUEST_CODE_NEW_HIGH_SCORE);
    }

    /**
     *  ACTIVITY FOR RESULT
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE_NEW_LOGO == requestCode && RESULT_OK == resultCode) {
            if (BitmapStorage.isFileExist(getContext(), "LOGO")) {
                mLogo.setImageBitmap(BitmapStorage.loadImage(getContext(), "LOGO"));
            }
        }
        if (REQUEST_CODE_NEW_BUSINESS_NAME == requestCode && RESULT_OK == resultCode){
            if (data.getStringExtra(WriteFromSettingsActivity.BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION) != null) {
                mBusinessName.setText(data.getStringExtra(WriteFromSettingsActivity.BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION));
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_BUSINESS_NAME, MODE_PRIVATE);
                sharedPreferences.edit().putString(BUNDLE_KEY_BUSINESS_NAME, data.getStringExtra(WriteFromSettingsActivity.BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION)).apply();
            }
        }
        if (REQUEST_CODE_NEW_LOW_SCORE == requestCode && RESULT_OK == resultCode){
            if (data.getStringExtra(WriteFromSettingsActivity.BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION) != null) {
                if (BusinessSettings.scoreComparaison(Integer.valueOf(data.getStringExtra(WriteFromSettingsActivity.BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION)), mMediumInt, mHighInt)) {
                    mLowScore.setText(data.getStringExtra(WriteFromSettingsActivity.BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION));
                    mLowInt = Integer.valueOf(data.getStringExtra(WriteFromSettingsActivity.BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION));
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_LOW_SCORE, MODE_PRIVATE);
                    sharedPreferences.edit().putInt(BUNDLE_KEY_LOW_VALUE, mLowInt).apply();
                }
            }
        }
        if (REQUEST_CODE_NEW_MEDIUM_SCORE == requestCode && RESULT_OK == resultCode){
            if (data.getStringExtra(WriteFromSettingsActivity.BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION) != null) {
                if (BusinessSettings.scoreComparaison(mLowInt, Integer.valueOf(data.getStringExtra(WriteFromSettingsActivity.BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION)), mHighInt)){
                    mMediumScore.setText(data.getStringExtra(WriteFromSettingsActivity.BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION));
                    mMediumInt = Integer.valueOf(data.getStringExtra(WriteFromSettingsActivity.BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION));
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_MEDIUM_SCORE, MODE_PRIVATE);
                    sharedPreferences.edit().putInt(BUNDLE_KEY_MEDIUM_VALUE, mMediumInt).apply();
                }
            }
        }
        if (REQUEST_CODE_NEW_HIGH_SCORE == requestCode && RESULT_OK == resultCode){
            if (data.getStringExtra(WriteFromSettingsActivity.BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION) != null) {
                if (BusinessSettings.scoreComparaison(mLowInt, mMediumInt, Integer.valueOf(data.getStringExtra(WriteFromSettingsActivity.BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION)))) {
                    mHighScore.setText(data.getStringExtra(WriteFromSettingsActivity.BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION));
                    mHighInt = Integer.valueOf(data.getStringExtra(WriteFromSettingsActivity.BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION));
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_HIGH_SCORE, MODE_PRIVATE);
                    sharedPreferences.edit().putInt(BUNDLE_KEY_HIGH_VALUE, mHighInt).apply();
                }
            }
        }
    }


}
