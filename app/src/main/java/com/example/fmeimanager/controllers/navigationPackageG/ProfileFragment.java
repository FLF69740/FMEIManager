package com.example.fmeimanager.controllers.navigationPackageG;

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
import butterknife.OnClick;

public class ProfileFragment extends Fragment {

    private View mView;
    private ParticipantViewModel mParticipantViewModel;
    private long mParticipantId;

    private static final String BUNDLE_PARTICIPANT_ID = "BUNDLE_PARTICIPANT_ID";

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

        mParticipantId = getArguments().getLong(BUNDLE_PARTICIPANT_ID);
        Toast.makeText(getContext(), ""+mParticipantId, Toast.LENGTH_LONG).show();

        return mView;
    }

    @OnClick(R.id.profile_first_link)
    public void profile_To_SectionProfile(){
        mCallback.profile_To_SectionProfile(mView);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface ProfileItemClickedListener{
        void profile_To_SectionProfile(View view);
    }

    //callback for button clicked
    private ProfileItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ProfileItemClickedListener) getActivity();
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
        this.mParticipantViewModel.init(1);
    }

    private void getAdministrator(long id){
        this.mParticipantViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    /**
     *  UI
     */

    private void updateAdministrator(Participant participant){

    }

}
