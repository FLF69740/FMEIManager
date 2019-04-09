package com.example.fmeimanager.controllers.navigationPackageC;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.viewmodels.ParticipantViewModel;

import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_ACTIVE_USER;
import static com.example.fmeimanager.MainActivity.DEFAULT_USER_ID;
import static com.example.fmeimanager.MainActivity.SHARED_MAIN_PROFILE_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private View mView;
    private ParticipantViewModel mParticipantViewModel;

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

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(getContext());
        this.mParticipantViewModel = ViewModelProviders.of(this, viewModelFactory).get(ParticipantViewModel.class);
        this.mParticipantViewModel.init(getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID));
    }

    private void getAdministrator(long id){
        this.mParticipantViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    /**
     *  UI
     */

    private void updateAdministrator(Participant participant){
        Toast.makeText(getContext(), participant.getForname() + " " + participant.getName() + "/SettingsActivity", Toast.LENGTH_SHORT).show();
        mCallback.updateSettingsNavHeader(participant);
    }

}
