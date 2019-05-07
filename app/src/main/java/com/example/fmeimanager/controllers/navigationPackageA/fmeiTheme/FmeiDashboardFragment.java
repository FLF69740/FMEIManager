package com.example.fmeimanager.controllers.navigationPackageA.fmeiTheme;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.navigationPackageA.fmeiTheme.adapters.FmeiListAdapter;
import com.example.fmeimanager.database.TeamFmei;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.database.CorrectiveAction;
import com.example.fmeimanager.database.Fmei;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.Processus;
import com.example.fmeimanager.database.Risk;
import com.example.fmeimanager.models.FmeiPanel;
import com.example.fmeimanager.models.FmeiPanelCreator;
import com.example.fmeimanager.utils.RecyclerItemClickSupport;
import com.example.fmeimanager.utils.Utils;
import com.example.fmeimanager.viewmodels.GeneralViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_ACTIVE_USER;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_HIGH_VALUE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_LOW_VALUE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_MEDIUM_VALUE;
import static com.example.fmeimanager.MainActivity.DEFAULT_USER_ID;
import static com.example.fmeimanager.MainActivity.SHARED_HIGH_SCORE;
import static com.example.fmeimanager.MainActivity.SHARED_LOW_SCORE;
import static com.example.fmeimanager.MainActivity.SHARED_MAIN_PROFILE_ID;
import static com.example.fmeimanager.MainActivity.SHARED_MEDIUM_SCORE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FmeiDashboardFragment extends Fragment {

    private View mView;
    private GeneralViewModel mGeneralViewModel;
    private long mAdministratorId;
    private FmeiPanelCreator mFmeiPanelCreator;
    private FmeiListAdapter mAdapter;

    @BindView(R.id.fragment_fmei_recycler_view) RecyclerView mRecyclerView;

    public FmeiDashboardFragment() {}

    public static FmeiDashboardFragment newInstance(){
        return new FmeiDashboardFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_fmei_dashboard, container, false);
        ButterKnife.bind(this, mView);
        mFmeiPanelCreator = new FmeiPanelCreator(getActivity().getSharedPreferences(SHARED_HIGH_SCORE, MODE_PRIVATE).getInt(BUNDLE_KEY_HIGH_VALUE, 200),
                getActivity().getSharedPreferences(SHARED_MEDIUM_SCORE, MODE_PRIVATE).getInt(BUNDLE_KEY_MEDIUM_VALUE, 150),
                getActivity().getSharedPreferences(SHARED_LOW_SCORE, MODE_PRIVATE).getInt(BUNDLE_KEY_LOW_VALUE, 100));
        this.configureRecyclerView();
        this.configureOnClickRecyclerView();
        this.configureViewModel();
        this.getAdministrator(getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID));
        this.getAllFmei();
        return mView;
    }

    //configure recyclerView
    private void configureRecyclerView(){
        this.mAdapter = new FmeiListAdapter(this.mFmeiPanelCreator.getFmeiPanels());
        this.mRecyclerView.setAdapter(mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //update recyclerView after other thread finalisation
    private void updateRecycler(List<FmeiPanel> fmeiPanelList){
        mAdapter.setFmeiList(fmeiPanelList);
        mAdapter.notifyDataSetChanged();
    }

    //itemView click from RecyclerView
    private void configureOnClickRecyclerView(){
        RecyclerItemClickSupport.addTo(mRecyclerView, R.layout.fragment_fmei_recyclerview_item)
                .setOnItemClickListener((recyclerView, position, v) -> mCallback.fmeiDashBoard_To_ProcessDashboard(mView, position+1));
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface ItemClickedListener{
        void fmeiDashBoard_To_ProcessDashboard(View view, long fmeiId);
        void updateNavHeader(Participant participant);
    }

    //callback for button clicked
    private ItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ItemClickedListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }

    /**
     *  DATAS
     */

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(getContext());
        this.mGeneralViewModel = ViewModelProviders.of(this, viewModelFactory).get(GeneralViewModel.class);
        this.mGeneralViewModel.init(getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID));
    }

    //GET user hardware
    private void getAdministrator(long id){
        this.mGeneralViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    //GET all fmei
    private void getAllFmei(){
        this.mGeneralViewModel.getAllFmei().observe(this, this::updateFmeiList);
    }

    //GET all processus
    private void getAllProcessus(){
        this.mGeneralViewModel.getAllProcessus().observe(this, this::updateProcessusList);
    }

    //GET all risk
    private void getAllRisk(){
        this.mGeneralViewModel.getAllRisk().observe(this, this::updateRiskList);
    }

    //GET all team fmei
    private void getAllTeamFmei(){
        this.mGeneralViewModel.getAllTeamFmei().observe(this, this::updateTeamFmeiList);
    }

    //GET all participant
    private void getAllParticipant(){
        this.mGeneralViewModel.getAllParticipant().observe(this, this::updateParticipantList);
    }

    //CREATE FMEI
    public void createFmei(){
        Fmei fmei = new Fmei(getContext().getString(R.string.profile_section_fmea) + " " + String.valueOf(mFmeiPanelCreator.getFmeiListSize() + 1), mAdministratorId);
        this.mGeneralViewModel.createFmei(fmei);
        this.mGeneralViewModel.createTeamFmei(new TeamFmei((mFmeiPanelCreator.getFmeiListSize() + 1), mAdministratorId));
        Snackbar.make(mView, getContext().getString(R.string.Fmei_dashboard_snacbar_create) + fmei.getName(), Snackbar.LENGTH_SHORT).show();
    }

    /**
     *  CLASS DEFINITION
     */

    //SHOW user hardware
    private void updateAdministrator(Participant participant){
        mAdministratorId = participant.getId();
        mCallback.updateNavHeader(participant);
    }

    //RECORD all fmei information INTO panel
    private void updateFmeiList(List<Fmei> fmeis) {
        if (fmeis != null && fmeis.size() != 0) {
            mFmeiPanelCreator.clear();
            mFmeiPanelCreator.setFmeiList(fmeis);
            this.getAllProcessus();
        }
    }

    //RECORD all processus information INTO panel
    private void updateProcessusList(List<Processus> processus) {
        if (processus != null) {
            mFmeiPanelCreator.updateProcessusList(processus);
            this.getAllRisk();
        }
    }

    //RECORD all risk information INTO panel
    private void updateRiskList(List<Risk> risks) {
        if (risks != null) {
            mFmeiPanelCreator.updateRiskList(risks);
            getAllTeamFmei();
        }
    }

    //RECORD all team fmei information INTO panel
    private void updateTeamFmeiList(List<TeamFmei> teams) {
        mFmeiPanelCreator.updateTeamFmeiList(teams);
        getAllParticipant();
    }

    //RECORD all participant information INTO panel
    private void updateParticipantList(List<Participant> participants) {
        mFmeiPanelCreator.updateParticipantList(participants);
        this.updateRecycler(mFmeiPanelCreator.getFmeiPanels());
    }

}
