package com.example.fmeimanager.controllers.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.activities.TeamFmei;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFmeiFragment extends Fragment {

    private View mView;

    public TeamFmeiFragment() {}

    public static TeamFmeiFragment newInstance(){
        return new TeamFmeiFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_team_fmei, container, false);
        ButterKnife.bind(this, mView);
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

}
