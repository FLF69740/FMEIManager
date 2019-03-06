package com.example.fmeimanager.controllers.fragments;


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
public class ProcessDashboardFragment extends Fragment {

    private View mView;

    public ProcessDashboardFragment() {}

    public static ProcessDashboardFragment newInstance(){
        return new ProcessDashboardFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_process_dashboard, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @OnClick(R.id.process_dashboard_first_link)
    public void processDashBoard_To_ProcessDashboard(){
        mCallback.processDashBoard_To_RiskFile(mView);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface ItemClickedListener{
        void processDashBoard_To_RiskFile(View view);
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

}
