package com.example.fmeimanager.controllers.navigationPackageF;


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
import com.example.fmeimanager.viewmodels.TeamViewModel;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_ACTIVE_USER;
import static com.example.fmeimanager.MainActivity.DEFAULT_USER_ID;
import static com.example.fmeimanager.MainActivity.SHARED_MAIN_PROFILE_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFmeiFragment extends Fragment {

    private View mView;
    private TeamViewModel mTeamViewModel;


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

        return mView;
    }

    @OnClick(R.id.team_fmei_dashboard_first_link)
    public void teamFmeiDashboard_To_teamFmeiBuilder(){
        mCallback.teamFmeiDashboard_To_teamFmeiBuilder(mView);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface TeamFmeiItemClickedListener{
        void teamFmeiDashboard_To_teamFmeiBuilder(View view);
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

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(getContext());
        this.mTeamViewModel = ViewModelProviders.of(this, viewModelFactory).get(TeamViewModel.class);
        this.mTeamViewModel.init(getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID));
    }

    private void getAdministrator(long id){
        this.mTeamViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    /**
     *  UI
     */

    private void updateAdministrator(Participant participant){
        Toast.makeText(getContext(), participant.getForname() + "/" + participant.getName(), Toast.LENGTH_SHORT).show();
        mCallback.updateTeamFmeiNavHeader(participant);
    }

}
