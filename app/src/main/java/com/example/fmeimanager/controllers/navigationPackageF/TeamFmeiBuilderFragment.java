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
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.viewmodels.TeamViewModel;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFmeiBuilderFragment extends Fragment {

    private View mView;
    private TeamViewModel mTeamViewModel;
    private static final String BUNDLE_TEAM_FMEI_ID = "BUNDLE_TEAM_FMEI_ID";

    public TeamFmeiBuilderFragment() {}

    public static TeamFmeiBuilderFragment newInstance(long position){
        TeamFmeiBuilderFragment teamFmeiBuilderFragment = new TeamFmeiBuilderFragment();
        Bundle bundle = new Bundle(1);
        bundle.putLong(BUNDLE_TEAM_FMEI_ID, position);
        teamFmeiBuilderFragment.setArguments(bundle);
        return teamFmeiBuilderFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_team_fmei_builder, container, false);
        ButterKnife.bind(this, mView);
        long teamFmeaId = getArguments().getLong(BUNDLE_TEAM_FMEI_ID, 1000);
        Toast.makeText(getContext(), ""+teamFmeaId, Toast.LENGTH_LONG).show();
        return mView;
    }

    @OnClick(R.id.team_fmei_builder_first_link)
    public void teamFmeiBuilder_To_teamFmeiDashboard(){
        mCallback.teamFmeiBuilder_To_teamFmeiDashboard(mView);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface TeamFmeiBuilderItemClickedListener{
        void teamFmeiBuilder_To_teamFmeiDashboard(View view);
    }

    //callback for button clicked
    private TeamFmeiBuilderItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (TeamFmeiBuilderItemClickedListener) getActivity();
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
        this.mTeamViewModel.init(1);
    }


    /**
     *  UI
     */

}
