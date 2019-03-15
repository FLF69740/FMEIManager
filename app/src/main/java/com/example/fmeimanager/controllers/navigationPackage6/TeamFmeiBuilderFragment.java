package com.example.fmeimanager.controllers.navigationPackage6;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fmeimanager.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFmeiBuilderFragment extends Fragment {

    private View mView;

    public TeamFmeiBuilderFragment() {}

    public static TeamFmeiBuilderFragment newInstance(){
        return new  TeamFmeiBuilderFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_team_fmei_builder, container, false);
        ButterKnife.bind(this, mView);
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

}
