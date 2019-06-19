package com.FMEA.fmeimanager.controllers.navigationPackageF;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.controllers.navigationPackageF.adapter.TeamFmeiAdapter;
import com.FMEA.fmeimanager.database.Participant;
import com.FMEA.fmeimanager.injection.Injection;
import com.FMEA.fmeimanager.injection.ViewModelFactory;
import com.FMEA.fmeimanager.models.TeamPanel;
import com.FMEA.fmeimanager.utils.RecyclerItemClickSupport;
import com.FMEA.fmeimanager.viewmodels.TeamViewModel;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import static android.content.Context.MODE_PRIVATE;
import static com.FMEA.fmeimanager.MainActivity.BUNDLE_KEY_ACTIVE_USER;
import static com.FMEA.fmeimanager.MainActivity.DEFAULT_USER_ID;
import static com.FMEA.fmeimanager.MainActivity.SHARED_MAIN_PROFILE_ID;


public class TeamFmeiFragment extends Fragment {

    private View mView;
    private TeamViewModel mTeamViewModel;

    @BindView(R.id.fragment_team_fmei_recycler_view) RecyclerView mRecyclerView;

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
         this.getAllFmea();

        return mView;
    }

    //Configure recyclerView
    private void configureRecyclerView(List<TeamPanel> teamPanels){
        TeamFmeiAdapter adapter = new TeamFmeiAdapter(teamPanels);
        this.mRecyclerView.setAdapter(adapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //itemView click from RecyclerView
    private void configureOnClickRecyclerView(List<TeamPanel> teamPanels){
        RecyclerItemClickSupport.addTo(mRecyclerView, R.layout.fragment_fmei_recyclerview_item)
                .setOnItemClickListener((recyclerView, position, v) -> mCallback.teamFmeiDashboard_To_teamFmeiBuilder(mView, position+1,
                        teamPanels.get(position).getParticipantList(), teamPanels.get(position).getTeamLeaderId()));
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

    //GET all TeamPanels
    private void getAllFmea(){
        this.mTeamViewModel.theFirstTeamLiveData().observe(this, this::updateTeamPanels);
    }

    /**
     *  UI
     */

    //configure nav header with administrator id
    private void updateAdministrator(Participant participant){
        mCallback.updateTeamFmeiNavHeader(participant);
    }

    private void updateTeamPanels(List<TeamPanel> teamPanels){
        this.configureRecyclerView(teamPanels);
        this.configureOnClickRecyclerView(teamPanels);
    }

}
