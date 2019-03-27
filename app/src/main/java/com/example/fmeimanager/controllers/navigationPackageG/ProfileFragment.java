package com.example.fmeimanager.controllers.navigationPackageG;


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
public class ProfileFragment extends Fragment {

    private View mView;

    public ProfileFragment() {}

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @OnClick(R.id.profile_first_link)
    public void profile_To_SectionProfile(){
        mCallback.profile_To_SectionProfile(mView);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface ProfileItemClickedListener{
        void profile_To_SectionProfile(View view);
    }

    //callback for button clicked
    private ProfileItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ProfileItemClickedListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }

}
