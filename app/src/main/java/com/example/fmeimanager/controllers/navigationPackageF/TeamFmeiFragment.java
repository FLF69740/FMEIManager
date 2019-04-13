package com.example.fmeimanager.controllers.navigationPackageF;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.navigationPackageF.adapter.TeamFmeiAdapter;
import com.example.fmeimanager.database.Fmei;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.TeamFmei;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.models.TeamPanel;
import com.example.fmeimanager.models.TeamPanelCreator;
import com.example.fmeimanager.utils.RecyclerItemClickSupport;
import com.example.fmeimanager.utils.Utils;
import com.example.fmeimanager.viewmodels.TeamViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_ACTIVE_USER;
import static com.example.fmeimanager.MainActivity.DEFAULT_USER_ID;
import static com.example.fmeimanager.MainActivity.SHARED_MAIN_PROFILE_ID;


public class TeamFmeiFragment extends Fragment {

    private View mView;
    private TeamViewModel mTeamViewModel;
    private TeamPanelCreator mTeamPanelCreator;


    public TeamFmeiFragment() {}

    public static TeamFmeiFragment newInstance(){
        return new TeamFmeiFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_team_fmei, container, false);
        ButterKnife.bind(this, mView);

         this.configureViewModel();
         this.getAdministrator(getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID));
         mTeamPanelCreator = new TeamPanelCreator();
         this.getAllFmea();


        return mView;
    }

    private TeamFmeiAdapter mAdapter;
    @BindView(R.id.fragment_team_fmei_recycler_view) RecyclerView mRecyclerView;

    //Configure recyclerView
    private void configureRecyclerView(){
        this.mAdapter = new TeamFmeiAdapter(this.mTeamPanelCreator.getTeamPanels());
        this.mRecyclerView.setAdapter(mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //itemView click from RecyclerView
    private void configureOnClickRecyclerView(){
        RecyclerItemClickSupport.addTo(mRecyclerView, R.layout.fragment_fmei_recyclerview_item)
                .setOnItemClickListener((recyclerView, position, v) -> mCallback.teamFmeiDashboard_To_teamFmeiBuilder(mView, position+1,
                        mTeamPanelCreator.getTeamPanels().get(position).getParticipantList(), mTeamPanelCreator.getTeamPanels().get(position).getTeamLeaderId()));
    }

    public void updateLists(ArrayList<String> packageNewFmeiId, ArrayList<String> packageNewParticipantId, ArrayList<String> teamFmeiIdToDelete) {
        String messageFmeiId = BusinnessTeamFmei.convertToListString(packageNewFmeiId, "2 - NEW FMEI LIST : ");
        Log.i(Utils.INFORMATION_LOG, messageFmeiId);
        String messageParticipantId = BusinnessTeamFmei.convertToListString(packageNewParticipantId, "2 - NEW PARTICIPANT LIST : ");
        Log.i(Utils.INFORMATION_LOG, messageParticipantId);
        String messageTeamFmeiToDelete = BusinnessTeamFmei.convertToListString(teamFmeiIdToDelete, "2 - FMEI TO DELETE LIST : ");
        Log.i(Utils.INFORMATION_LOG, messageTeamFmeiToDelete);

        for (int i = 0 ; i < teamFmeiIdToDelete.size() ; i++){
            mTeamViewModel.deleteTeamFmei(Long.valueOf(teamFmeiIdToDelete.get(i)));
        }

        for (int i = 0 ; i < packageNewParticipantId.size() ; i++){
            mTeamViewModel.createTeamFmei(new TeamFmei(Long.valueOf(packageNewFmeiId.get(i)),Long.valueOf(packageNewParticipantId.get(i))));
        }
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface TeamFmeiItemClickedListener{
        void teamFmeiDashboard_To_teamFmeiBuilder(View view, int position, List<Participant> participantList, long teamLeaderId);
        void updateTeamFmeiNavHeader(Participant participant);
    }

    //callback for button clicked
    private TeamFmeiItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (TeamFmeiItemClickedListener) getActivity();
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
        this.mTeamViewModel = ViewModelProviders.of(this, viewModelFactory).get(TeamViewModel.class);
        this.mTeamViewModel.init(getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID));
    }

    //GET administartor id for nav header
    private void getAdministrator(long id){
        this.mTeamViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    //GET all fmea
    private void getAllFmea(){
        this.mTeamViewModel.getAllFmei().observe(this, this::updateAllFmea);
    }

    //GET all participant
    private void getAllParticipant(){
        this.mTeamViewModel.getAllParticipant().observe(this, this::updateAllParticipant);
    }

    //GET all team fmea
    private void getAllTeamFmea(){
        this.mTeamViewModel.getAllTeamFmei().observe(this, this::updateTeamFmea);
    }

    /**
     *  UI
     */

    //configure nav header with administrator id
    private void updateAdministrator(Participant participant){
        mCallback.updateTeamFmeiNavHeader(participant);
    }

    //RECORD all fmea into this fragment
    private void updateAllFmea(List<Fmei> fmeiList){
        mTeamPanelCreator.setFmeiList(fmeiList);
        getAllParticipant();
    }

    //RECORD all participant into this fragment
    private void updateAllParticipant(List<Participant> participantList){
        mTeamPanelCreator.setParticipantList(participantList);
        getAllTeamFmea();
    }

    //RECORD all team fmea
    private void updateTeamFmea(List<TeamFmei> teamFmeiList){
            mTeamPanelCreator.setTeamFmeaList(teamFmeiList);
            this.configureRecyclerView();
            this.configureOnClickRecyclerView();
    }

}
