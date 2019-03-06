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
public class RiskFileDescriptionFragment extends Fragment {

    private View mView;

    public RiskFileDescriptionFragment() {}

    public static RiskFileDescriptionFragment newInstance(){
        return new RiskFileDescriptionFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_risk_file_description, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @OnClick(R.id.risk_file_first_link)
    public void riskFile_To_CorrectiveAction(){
        mCallback.riskFile_To_CorrectiveAction(mView);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface RiskFileItemClickedListener{
        void riskFile_To_CorrectiveAction(View view);
    }

    //callback for button clicked
    private RiskFileItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (RiskFileItemClickedListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }

}
