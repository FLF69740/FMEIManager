package com.example.fmeimanager.controllers.navigationPackageG;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.navigationPackageG.adapter.ProfileListAdapter;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.utils.RecyclerItemClickSupport;
import com.example.fmeimanager.viewmodels.ParticipantViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_ACTIVE_USER;
import static com.example.fmeimanager.MainActivity.DEFAULT_USER_ID;
import static com.example.fmeimanager.MainActivity.SHARED_MAIN_PROFILE_ID;

public class ProfileSectionFragment extends Fragment {

    private View mView;
    private ParticipantViewModel mParticipantViewModel;
    private List<Participant> mParticipantList;
    private ProfileListAdapter mAdapter;

    @BindView(R.id.fragment_profile_section_recycler_view) RecyclerView mRecyclerView;

    public ProfileSectionFragment() {}

    public static ProfileSectionFragment newInstance(){
        return new ProfileSectionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_profile_section, container, false);
        ButterKnife.bind(this, mView);

        this.configureViewModel();

        this.getAdministrator(getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID));
        this.getAllParticipant();

        return mView;
    }

    //configure recyclerView
    private void configureRecyclerView(){
        this.mAdapter = new ProfileListAdapter(this.mParticipantList);
        this.mRecyclerView.setAdapter(mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.notifyDataSetChanged();
    }

    /**
     *  ACTION
     */

    public void createProfile() {
        Participant participant = new Participant(getString(R.string.profile_section_default_name) + " (" + mParticipantList.size() + ")",
                getString(R.string.profile_section_default_forname));
        this.mParticipantViewModel.createParticipant(participant);
    }

    //itemView click from RecyclerView
    private void configureOnClickRecyclerView(){
        RecyclerItemClickSupport.addTo(mRecyclerView, R.layout.fragment_profile_section_recyclerview_item)
                .setOnItemClickListener((recyclerView, position, v) -> mCallback.ProfileSection_To_Profile(mView, position+1));
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface ProfileSectionItemClickedListener{
        void ProfileSection_To_Profile(View view, long position);
        void updateNavHeader(Participant participant);
    }

    //callback for button clicked
    private ProfileSectionItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ProfileSectionItemClickedListener) getActivity();
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

    //GET all participant
    private void getAllParticipant(){
        this.mParticipantViewModel.getAllParticipant().observe(this, this::updateParticipantList);
    }

    /**
     *  UI
     */

    private void updateAdministrator(Participant participant){
        mCallback.updateNavHeader(participant);
    }

    //RECORD all participant information INTO panel
    private void updateParticipantList(List<Participant> participants) {
        mParticipantList = participants;
        configureRecyclerView();
        this.configureOnClickRecyclerView();
    }

}
