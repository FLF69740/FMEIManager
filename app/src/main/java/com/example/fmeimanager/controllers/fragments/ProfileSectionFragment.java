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
public class ProfileSectionFragment extends Fragment {

    private View mView;

    public ProfileSectionFragment() {}

    public static ProfileSectionFragment newInstance(){
        return new ProfileSectionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_profile_section, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @OnClick(R.id.profile_section_first_link)
    public void ProfileSection_To_Profile(){
        mCallback.ProfileSection_To_Profile(mView);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface ProfileSectionItemClickedListener{
        void ProfileSection_To_Profile(View view);
    }

    //callback for button clicked
    private ProfileSectionItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ProfileSectionItemClickedListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }

}
