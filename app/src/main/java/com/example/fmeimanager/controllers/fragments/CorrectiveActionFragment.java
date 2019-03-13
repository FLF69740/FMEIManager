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
public class CorrectiveActionFragment extends Fragment {

    private View mView;

    public CorrectiveActionFragment() {}

    public static CorrectiveActionFragment newInstance(){
        return new CorrectiveActionFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_corrective_action, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @OnClick(R.id.corrective_action_first_link)
    public void correctiveAction_To_riskFile(){
        mCallback.correctiveAction_To_riskFile(mView);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface CorrectiveActionItemClickedListener{
        void correctiveAction_To_riskFile(View view);
    }

    //callback for button clicked
    private CorrectiveActionItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (CorrectiveActionItemClickedListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }

}
